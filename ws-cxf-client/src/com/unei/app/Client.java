package com.unei.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unei.bean.Book;
import com.unei.service.IBookService;


public class Client {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		IBookService client=(IBookService)context.getBean("client");
		Book b=new Book();
		b.setBookId(1);
		b.setBookName("new book");
		client.addBook(b);
	}
}
