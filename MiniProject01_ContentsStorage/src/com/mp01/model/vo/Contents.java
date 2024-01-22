package com.mp01.model.vo;

public class Contents {
	private static int rowId= 1;
	private int contentsId;
	private String type;
	private String title;
	private String content;
	private String createDate;
	
	public Contents() {
		
	}
	
	public Contents(String type, String title, String content, String createDate) {
		contentsId = rowId++;
		this.type = type;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
	}
	

	public int getContentsId() {
		return contentsId;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreateDate() {
		return createDate;
	}


	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}


	@Override
	public String toString() {
		return "Contents [type=" + type + ", title=" + title + ", content=" + content + "]";
	}
	
	
}
