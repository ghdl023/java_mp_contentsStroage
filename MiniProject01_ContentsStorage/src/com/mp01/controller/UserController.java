package com.mp01.controller;

import com.mp01.model.vo.User;
import com.mp01.service.UserService;

public class UserController {
	
	public static User user = new User();
	
//	private JDBCModelDAO service = new JDBCModelDAO();
	private UserService userService = new UserService();
	
	public boolean signUp(String id, String password) {
		return userService.signUp(id, password);
	}
	
	public boolean signIn(String id, String password) {
		return userService.signIn(id, password);
	}
	
	public void signOut() {
		userService.signOut();
	}
}
