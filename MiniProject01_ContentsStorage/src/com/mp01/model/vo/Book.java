package com.mp01.model.vo;

public class Book extends Contents {
	private String author;
	private String publisher;
	private int price;
	private String isLikeYn;
	private int starCount;
	
	public Book(String author, String publisher, int price, String isLikeYn, int starCount) {
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}
	
	public Book(String type, String title, String content, String createDate, String author, String publisher,
			int price, String isLikeYn, int starCount) {
		super(type, title, content, createDate);
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}
	
	public Book(int contentsId, String type, String title, String content, String createDate, String author, String publisher,
			int price, String isLikeYn, int starCount) {
		super(contentsId, type, title, content, createDate);
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}

	public int getStarCount() {
		return starCount;
	}

	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getIsLikeYn() {
		return isLikeYn;
	}


	public void setIsLikeYn(String isLikeYn) {
		this.isLikeYn = isLikeYn;
	}


	@Override
	public String toString() {
		return String.format("%s\t%s\t\t%s\t\t%s\t\t%d\t%d", getCreateDate(), getTitle(), author, publisher, price, starCount);
	}
	
	
	
	
}
