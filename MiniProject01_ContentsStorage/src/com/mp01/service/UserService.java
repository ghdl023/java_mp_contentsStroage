package com.mp01.service;

import java.sql.Connection;

import com.mp01.common.DBCP;
import com.mp01.model.vo.User;
import com.mp01.repository.UserRepository;

public class UserService {

	private UserRepository userRepository = new UserRepository();
	
	public int signUp(String id, String password) {
		Connection conn = DBCP.getConnection(false);
		int result = 0;
		result = userRepository.signUp(conn, id, password); 

		if(result > 0) { // 유저정보가 있으면 로그인
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result;
	}

	public int signIn(String id, String password) {
		Connection conn = DBCP.getConnection(true);
		int result = 0;
		result = userRepository.signIn(conn, id, password);
		
		return result;
	}
	
	public boolean deleteUser(String userName) {
		Connection conn = DBCP.getConnection(false);
		int result = 0;
		result = userRepository.deleteUser(conn, userName);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
}
