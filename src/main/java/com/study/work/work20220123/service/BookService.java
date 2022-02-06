package com.study.work.work20220123.service;

import com.study.work.work20220123.exception.InsufficientAmount;
import com.study.work.work20220123.exception.InsufficientQuantity;
import com.study.work.work20220123.model.Book;
import com.study.work.work20220123.model.TransactionLog;

public interface BookService {
	void buyOne(Integer wid, Integer bid) throws InsufficientQuantity, InsufficientAmount;
	void buyMany(Integer wid, Integer... bids) throws InsufficientQuantity, InsufficientAmount;
	Book getBook(Integer bid);
	void addLog(TransactionLog transactionLog);
}
