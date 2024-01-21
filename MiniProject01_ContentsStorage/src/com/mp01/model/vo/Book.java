package com.mp01.model.vo;

public class Book extends Contents {
	private int starCount;
	private String author;
	private String publisher;
	private int price;
	private boolean isLike;
	
	
	public Book(String type, String title, String content, int starCount, String author, String publisher,
			String createDate, int price, boolean isLike) {
		super(type, title, content, createDate);
		this.starCount = starCount;
		this.author = author;
		this.publisher = publisher;
		this.price = price;
		this.isLike = isLike;
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


	public boolean isLike() {
		return isLike;
	}


	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}


	@Override
	public String toString() {
		return "Book [title=" + getTitle() + ", content=" + getContent() + ", starCount=" + starCount + ", author=" + author + ", publisher=" + publisher + ", createDate="
				+ getCreateDate() + ", price=" + price + ", isLike=" + isLike + "]";
	}
	
	
	
	
}
