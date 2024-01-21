package com.mp01.controller;

import com.mp01.model.vo.User;

public class UserController {
	
	public static User user = new User();
	
	public boolean signIn(String userId, String userPassword) {
		user.setUserId(userId);
		user.setUserPassword(userPassword);
		return true;
	}
	
	public void signOut() {
		if(user.isLogin()) {
			user.setUserId("");
			user.setUserPassword("");
		}
	}
}
