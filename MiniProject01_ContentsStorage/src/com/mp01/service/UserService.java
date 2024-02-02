package com.mp01.service;

import com.mp01.model.vo.User;
import com.mp01.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();
	
	public boolean signUp(String id, String password) {
		return userRepository.signUp(id, password);
	}
	
	public boolean signIn(String id, String password) {
		return userRepository.signIn(id, password);
	}
	
	public void signOut() {
		User user = User.getInstance();
		if(user.isLogin()) {
			user.setUserId("");
			user.setUserPassword("");
		}
	} 
	
	public boolean deleteUser() {
		return userRepository.deleteUser();
	}
}
