package com.study.work.work20220123.dao;

import com.study.work.work20220123.exception.InsufficientAmount;
import com.study.work.work20220123.exception.InsufficientQuantity;
import com.study.work.work20220123.model.Book;
import com.study.work.work20220123.model.TransactionLog;

public interface BookDao {
	Integer getPrice(Integer bid);
	Integer getStockAmount(Integer bid);
	Integer getWalletMoney(Integer wid);
	Integer updateStock(Integer bid, Integer amount) throws InsufficientQuantity;
	Integer updateWallet(Integer wid, Integer money) throws InsufficientAmount;
	
	Book getBook(Integer bid);
	Integer addTransactionLog(TransactionLog transactionLog);
}
