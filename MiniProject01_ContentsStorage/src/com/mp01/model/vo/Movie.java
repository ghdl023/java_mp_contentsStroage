package com.mp01.model.vo;

public class Movie extends Contents{
	private String releaseDate;
	private String director;
	private String actors;
	private String place;
	private String withs;
	private String isLikeYn;
	private int starCount;
	
	public Movie(String releaseDate, String director, String actors, String place, String withs, String isLikeYn, int starCount) {
		this.releaseDate = releaseDate;
		this.director = director;
		this.actors = actors;
		this.place = place;
		this.withs = withs;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}
	
	public Movie(String type, String title, String content, String createDate, String releaseDate, String director,
			String actors, String place, String withs, String isLikeYn, int starCount) {
		super(type, title, content, createDate);
		this.releaseDate = releaseDate;
		this.director = director;
		this.actors = actors;
		this.place = place;
		this.withs = withs;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}
	
	public Movie(int contentsId, String type, String title, String content, String createDate, String releaseDate, String director,
			String actors, String place, String withs, String isLikeYn, int starCount) {
		super(contentsId, type, title, content, createDate);
		this.releaseDate = releaseDate;
		this.director = director;
		this.actors = actors;
		this.place = place;
		this.withs = withs;
		this.isLikeYn = isLikeYn;
		this.starCount = starCount;
	}


	public int getStarCount() {
		return starCount;
	}


	public void setStarCount(int starCount) {
		this.starCount = starCount;
	}


	public String getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String getDirector() {
		return director;
	}


	public void setDirector(String director) {
		this.director = director;
	}


	public String getActors() {
		return actors;
	}


	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getWiths() {
		return withs;
	}


	public void setWiths(String withs) {
		this.withs = withs;
	}


	public String getIsLikeYn() {
		return isLikeYn;
	}


	public void setIsLikeYn(String isLikeYn) {
		this.isLikeYn = isLikeYn;
	}


	@Override
	public String toString() {
		return String.format("%s\t%s\t\t%s\t%s\t\t%s\t\t%d", getCreateDate(), getTitle(), releaseDate, director, actors, starCount);
	}
}
