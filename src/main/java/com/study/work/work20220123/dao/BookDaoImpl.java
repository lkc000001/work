package com.study.work.work20220123.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.study.work.work20220123.exception.InsufficientAmount;
import com.study.work.work20220123.exception.InsufficientQuantity;
import com.study.work.work20220123.model.Book;
import com.study.work.work20220123.model.TransactionLog;

@Repository
public class BookDaoImpl implements BookDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer getPrice(Integer bid) {
		String sql = "select price from book where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getStockAmount(Integer bid) {
		String sql = "select amount from stock where bid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, bid);
	}

	@Override
	public Integer getWalletMoney(Integer wid) {
		String sql = "select money from wallet where wid=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, wid);
	}

	@Override
	public Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity {
		Integer new_amount = getStockAmount(bid);
		if(new_amount <= 0) {
			throw new InsufficientQuantity(String.format("此書號: %d 目前沒庫存,目前數量: %d", bid, amount));
		}else if(new_amount < amount) {
			throw new InsufficientQuantity(String.format("此書號: %d 目前庫存不足,目前數量: %d,購買數量: %d", bid, new_amount, amount));
		}
		String sql = "update stock set amount = amount - ? where bid=?";
		return jdbcTemplate.update(sql, amount, bid);
	}

	@Override
	public Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount {
		Integer new_money = getWalletMoney(wid);
		if(new_money <= 0) {
			throw new InsufficientAmount(String.format("錢包號碼: %d 目前沒餘額,目前餘額: %d", wid, new_money));
		}else if(new_money < money) {
			throw new InsufficientAmount(String.format("錢包號碼: %d 目前餘額不足,目前餘額: $%d,扣款金額: $%d", wid, new_money, money));
		}
		
		String sql = "update wallet set money = money - ? where wid=?";
		return jdbcTemplate.update(sql, money, wid);
	}

	@Override
	public Book getBook(Integer bid) {
		String sql = "select bid, bname, price from book where bid=?";
		return jdbcTemplate.queryForObject(sql, new Object[]{bid}, new BeanPropertyRowMapper<Book>(Book.class));	
	}
	
	@Override
	public Integer addTransactionLog(TransactionLog transactionLog) {	
		String sql = "insert into transactionlog(wid, bid, bname, qty, amount, buydate) " +
					 "values(?,?,?,?,?,?)";
		int res = jdbcTemplate.update(sql, transactionLog.getWid(), transactionLog.getBid(), 
				transactionLog.getBname(), transactionLog.getQty(), transactionLog.getAmount(),
				transactionLog.getBuydate());
		return res;
	}

	
}
