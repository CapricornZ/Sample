package com.unei.service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.unei.bean.Book;

@WebService
public interface IBookService {
	@WebMethod
	public void addBook(Book book);
	@WebMethod
	public void deleteBook(int bookId);
	@WebMethod
	public void updateBook(Book book);
	@WebMethod
	public Book getBook(int bookId);
}
