package com.mp01.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class UserRepository {
	
	private Properties prop = new Properties();
	
	{
		try {
			prop.load(new FileInputStream("resource/userQuery.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int signUp(Connection connection, String id, String password) {
//		String sql = "INSERT INTO CONTENTS_USER(USER_NAME, USER_PWD) VALUES(?, ?)";
		String sql = prop.getProperty("signUp");
		
		int result = 0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			result = pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int signIn(Connection connection, String id, String password) {
//		String sql = "SELECT * FROM CONTENTS_USER WHERE 1=1 AND USER_NAME = ? AND USER_PWD = ? AND ENT_YN != 'Y' ";
		String sql = prop.getProperty("signIn");
		
		int result = 0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			result = pstmt.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int deleteUser(Connection connection, String userId) {
//		String sql = "UPDATE CONTENTS_USER SET ENT_YN = 'Y', ENT_DATE = SYSDATE WHERE 1=1 AND USER_NAME = ?";
		String sql = prop.getProperty("deleteUser");
		
		int result = 0;
		try(PreparedStatement pstmt = connection.prepareStatement(sql)){
			
			pstmt.setString(1, userId);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
