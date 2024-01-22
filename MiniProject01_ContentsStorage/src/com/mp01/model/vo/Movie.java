package com.mp01.model.vo;

public class Movie extends Contents{
	private int starCount;
	private String releaseDate;
	private String director;
	private String actors;
	private String place;
	private String with;
	private boolean isLike;
	
	public Movie(int starCount, String releaseDate, String director, String actors, String place, String with, boolean isLike) {
		this.starCount = starCount;
		this.releaseDate = releaseDate;
		this.director = director;
		this.actors = actors;
		this.place = place;
		this.with = with;
		this.isLike = isLike;
	}
	
	public Movie(String type, String title, String content, int starCount, String releaseDate, String director,
			String actors, String createDate, String place, String with, boolean isLike) {
		super(type, title, content, createDate);
		this.starCount = starCount;
		this.releaseDate = releaseDate;
		this.director = director;
		this.actors = actors;
		this.place = place;
		this.with = with;
		this.isLike = isLike;
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


	public String getWith() {
		return with;
	}


	public void setWith(String with) {
		this.with = with;
	}


	public boolean isLike() {
		return isLike;
	}


	public void setLike(boolean isLike) {
		this.isLike = isLike;
	}


	@Override
	public String toString() {
		return "Movie [title="+ getTitle() + ", content=" + getContent() +", starCount=" + starCount + ", releaseDate=" + releaseDate + ", director=" + director + ", actors="
				+ actors + ", createDate=" + getCreateDate() + ", place=" + place + ", with=" + with + ", isLike=" + isLike
				+ "]";
	}
}
