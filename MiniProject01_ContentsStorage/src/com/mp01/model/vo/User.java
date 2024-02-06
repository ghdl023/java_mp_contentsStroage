package com.mp01.model.vo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String userId;
	private String userPassword;
	private boolean isLogin;
	private char status = 'A'; // 계정상태여부 (A: 활성화, D: 탈퇴)
	private List<Contents> contentsList = new ArrayList<Contents>();
	
	private static User instance = new User();
	
	private User() {
	
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
	
	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public List<Contents> getContentsList() {
		return contentsList;
	}

	public void setContentsList(List<Contents> contentsList) {
		this.contentsList = contentsList;
	}
	
	public static User getInstance() {
		return instance;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", isLogin=" + isLogin + ", contentsList="
				+ contentsList.size() + "]";
	}
}
