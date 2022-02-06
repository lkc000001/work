package com.study.work.work20220123;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.work.work20220123.controller.BookController;

public class TestBook {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("jdbc-config.xml");
		BookController BookController = ctx.getBean("bookController", BookController.class);
		
		/*System.out.println(BookController);
		Integer wid = 1;
		Integer bid = 1;
		BookController.buyBook(wid, bid);*/
		Integer wid = 1;
		BookController.buyBooks(wid, 1,1,2,2,2);
		
		
	}
	

}
