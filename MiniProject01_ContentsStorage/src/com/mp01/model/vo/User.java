package com.mp01.model.vo;

import java.util.ArrayList;
import java.util.List;

public class User {
	private int userId;				// DB ROW_ID
	private String userName;		// 유저 ID
	private String userPassword;	// 유저 PASSWORD
	private boolean isLogin;		// 로그인 여부
	private char status = 'A'; 		// 계정상태 여부 (A: 활성화, D: 탈퇴)
	private List<Contents> contentsList = new ArrayList<Contents>();
	
	private static User instance = new User();
	
	private User() {
	
	}
	
	public User(int userId, String userName, String userPassword) {
		this.userId = userId;
		this.userPassword = userPassword;
		isLogin = true;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isLogin() {
		return (userName != null && !userName.equals("")) && (userPassword != null && !userPassword.equals(""));
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
