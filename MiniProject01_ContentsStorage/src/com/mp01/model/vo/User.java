package com.mp01.model.vo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String userPassword;
	private boolean isLogin;
	private List<Contents> contentsList = new ArrayList<Contents>();
	
	public User() {
	}
	
	public User(String userId, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
		isLogin = true;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isLogin() {
		return (userId != null && !userId.equals("")) && (userPassword != null && !userPassword.equals(""));
	}

	public List<Contents> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<Contents> contentsList) {
		this.contentsList = contentsList;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", isLogin=" + isLogin + ", contentsList="
				+ contentsList.size() + "]";
	}
}