package com.study.work.work20220123.service;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.study.work.work20220123.dao.BookDao;
import com.study.work.work20220123.exception.InsufficientAmount;
import com.study.work.work20220123.exception.InsufficientQuantity;
import com.study.work.work20220123.model.Book;
import com.study.work.work20220123.model.TransactionLog;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void buyOne(Integer wid, Integer bid) throws InsufficientQuantity, InsufficientAmount {
		bookDao.updateStock(bid, 1);
		
		Integer price = bookDao.getPrice(bid);
		
		int resp = bookDao.updateWallet(wid, price);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public void buyMany(Integer wid, Integer... bids) throws InsufficientQuantity, InsufficientAmount {
		
		//Stream.of(bids).forEach(bid -> buyOne(wid, bid));
		for(Integer bidInteger : bids) {
			buyOne(wid, bidInteger);
		}

	}

	@Override
	public Book getBook(Integer bid) {
		return bookDao.getBook(bid);
	}
	
	@Override
	public void addLog(TransactionLog transactionLog){
		bookDao.addTransactionLog(transactionLog);
	}

	

}
