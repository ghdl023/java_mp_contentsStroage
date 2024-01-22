package com.mp01.model.vo;

public class Diary extends Contents {
	private String feelings;
	
	public Diary(String feelings) {
		this.feelings = feelings;	
	}
	
	public Diary(String type, String title, String content, String createDate, String feelings) {
		super(type, title, content,createDate);
		this.feelings = feelings;
	}

	public String getFeelings() {
		return feelings;
	}

	public void setFeelings(String feelings) {
		this.feelings = feelings;
	}

	@Override
	public String toString() {
		return String.format("%d\t%s\t\t%s\t%s", getContentsId(), getCreateDate(), getTitle(), feelings);
	}
	
	
}
