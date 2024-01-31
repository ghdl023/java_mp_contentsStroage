package com.mp01.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.PooledConnection;

import com.mp01.controller.UserController;
import com.mp01.model.vo.User;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class UserRepository {
	private User user = User.getInstance();

	//	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver"; // DriverManager을 Connection Pool로 대체
	private final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	//	private final String DATABASE_URL = "jdbc:oracle:thin:@192.168.219.100:1521:xe";
	private final String DATABASE_USER_ID = "kh";
	private final String DATABASE_USER_PW = "kh";

	PooledConnection pc;

	{
		try {
			OracleConnectionPoolDataSource ocpds = new OracleConnectionPoolDataSource(); 
			ocpds.setURL(DATABASE_URL);
			ocpds.setUser(DATABASE_USER_ID);
			ocpds.setPassword(DATABASE_USER_PW);

			pc = ocpds.getPooledConnection();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public boolean signUp(String id, String password) {
		String sql = "INSERT INTO CONTENTS_USER(USER_NAME, USER_PWD) VALUES(?, ?)";

		int result = 0;
		try (Connection connection = pc.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			result = pstmt.executeUpdate();

			if(result == 1) { // 유저정보가 있으면 로그인
				user.setUserId(id);
				user.setUserPassword(password);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result == 1;
	}

	public boolean signIn(String id, String password) {
		String sql = "SELECT * FROM CONTENTS_USER WHERE 1=1 AND USER_NAME = ? AND USER_PWD = ? ";

		int result = 0;
		try (Connection connection = pc.getConnection();
				PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			result = pstmt.executeUpdate();

			if(result == 1) { // 유저정보가 있으면 로그인
				user.setUserId(id);
				user.setUserPassword(password);
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result == 1;
	}
}
