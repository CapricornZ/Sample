package com.unei.service.impl;

import com.unei.bean.Book;
import com.unei.service.IBookService;

public class BookService implements IBookService{

	public void addBook(Book book) {
		System.out.println("addBook:"+book.getBookName());
	}

	public void deleteBook(int bookId) {
		System.out.println("deleteBook:"+bookId);
	}

	public void updateBook(Book book) {
		System.out.println("updateBook:"+book.getBookName());
	}

	public Book getBook(int bookId) {
		Book book=new Book();
		book.setBookId(4);
		book.setBookName("getBook");
		book.setBookISBNnumber("ABCDEFG");
		book.setPrice(20.00);
		return book;
	}

}
