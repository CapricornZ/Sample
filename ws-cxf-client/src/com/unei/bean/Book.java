package com.unei.bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
public class Book {
	private int bookId;
	private String bookISBNnumber;
	private String bookName;
	private double price;
	private String author;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookISBNnumber() {
		return bookISBNnumber;
	}

	public void setBookISBNnumber(String bookISBNnumber) {
		this.bookISBNnumber = bookISBNnumber;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
}
