package com.study.work.work20220123.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;

import com.study.work.work20220123.dao.BookDao;
import com.study.work.work20220123.exception.InsufficientAmount;
import com.study.work.work20220123.exception.InsufficientQuantity;
import com.study.work.work20220123.model.Book;
import com.study.work.work20220123.model.TransactionLog;
import com.study.work.work20220123.service.BookService;

@Controller
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	public void buyBook(Integer wid, Integer bid) {
		try {
			bookService.buyOne(wid, bid);
			Book bookresp = bookService.getBook(bid);
			TransactionLog log = new TransactionLog(wid, bid, bookresp.getBname(),
							1, bookresp.getPrice(), new Date());
			bookService.addLog(log);
			System.out.println("單筆 buyBook OK !");
		} catch (InsufficientQuantity e) {
			System.out.println("庫存不足:"+e);
			e.printStackTrace();
		} catch (InsufficientAmount e) {
			System.out.println("金額不足:"+e);
			e.printStackTrace();
		}
		
	}
	
	public void buyBooks(Integer wid, Integer... bids) {
		try {
			bookService.buyMany(wid, bids);
			List<Book> bookList = new ArrayList<>();
			for(Integer bid : bids) {
				bookList.add(bookService.getBook(bid));
			}
			Map<Integer, List<Book>> bookmap = bookList.stream()
													.collect(Collectors.groupingBy(Book::getBid));
			Date nowDate = new Date();
			
			 for (Object key : bookmap.keySet()) {
				 List<Book> book = (List<Book>) bookmap.get(key);
				 TransactionLog log = new TransactionLog(wid, book.get(0).getBid(),
						 				book.get(0).getBname(), book.size(),
						 				book.get(0).getPrice() * book.size(), nowDate);
				 bookService.addLog(log);
		     }	
			 System.out.println("多筆 buyBook OK !");
		} catch (InsufficientQuantity e) {
			System.out.println("庫存不足:"+e);
			e.printStackTrace();
		} catch (InsufficientAmount e) {
			System.out.println("金額不足:"+e);
			e.printStackTrace();
		}
		
	}

}
