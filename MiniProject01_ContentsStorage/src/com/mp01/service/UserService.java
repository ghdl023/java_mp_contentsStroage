package com.mp01.service;

import com.mp01.controller.UserController;
import com.mp01.model.vo.User;
import com.mp01.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();
	private User user = UserController.user;
	
	public boolean signUp(String id, String password) {
		return userRepository.signUp(id, password);
	}
	
	public boolean signIn(String id, String password) {
		return userRepository.signIn(id, password);
	}
	
	public void signOut() {
		if(user.isLogin()) {
			user.setUserId("");
			user.setUserPassword("");
		}
	} 
}
