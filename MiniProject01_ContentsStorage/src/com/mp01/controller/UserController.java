package com.mp01.controller;

import com.mp01.model.dao.JDBCModelDAO;
import com.mp01.model.vo.User;

public class UserController {
	
	public static User user = new User();
	
	private JDBCModelDAO service = new JDBCModelDAO();
	
	public boolean signUp(String id, String password) {
		return service.signUp(id, password);
	}
	
	public boolean signIn(String id, String password) {
		return service.signIn(id, password);
	}
	
	public void signOut() {
		if(user.isLogin()) {
			user.setUserId("");
			user.setUserPassword("");
		}
	}
}
