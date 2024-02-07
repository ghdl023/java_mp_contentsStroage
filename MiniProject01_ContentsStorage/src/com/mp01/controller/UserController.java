package com.mp01.controller;

import com.mp01.service.UserService;
import com.mp01.view.ContentsStorageView;

public class UserController {
	
	private UserService userService = new UserService();
	
	public boolean signUp(String id, String password) {
		boolean result = userService.signUp(id, password);
		if(result) {
			new ContentsStorageView().displaySuccess(id + "님 회원가입을 축하합니다!");
		} else {
			new ContentsStorageView().displayFailed("회원가입을 실패했습니다. 중복된 아이디입니다.");
		}
		return result;
	}
	
	public boolean signIn(String id, String password) {
		boolean result = userService.signIn(id, password);
		if(result) {
			new ContentsStorageView().displaySuccess(id + "님 어서오세요!");
		} else {
			new ContentsStorageView().displayFailed("아이디 또는 비밀번호가 일치하지 않습니다.");
		}
		return result;
	}
	
	public boolean deleteUser(String userId) {
		boolean result = userService.deleteUser(userId);
		if(result) {
			new ContentsStorageView().displaySuccess(userId + "님 그동안 이용해주셔서 감사합니다.");
		} else {
			new ContentsStorageView().displayFailed("회원탈퇴 중 오류가 발생했습니다.");
		}
		return result;
	}
}
