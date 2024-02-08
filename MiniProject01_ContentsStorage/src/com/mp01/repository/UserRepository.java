package com.mp01.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
		String sql = prop.getProperty("signUp");
		
		int result = 0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql, new String[]{"USER_ID"})) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			result = pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				result = rs.getInt(1);
			}
//			System.out.println("Inserted record's ID: " + generatedKey);

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public int signIn(Connection connection, String id, String password) {
		String sql = prop.getProperty("signIn");
		
		int result = 0;
		try (PreparedStatement pstmt = connection.prepareStatement(sql)) {

			pstmt.setString(1, id);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				result = rs.getInt(1);
//				System.out.println("result : " + result);
			}
			

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public int deleteUser(Connection connection, String userName) {
		String sql = prop.getProperty("deleteUser");
		
		int result = 0;
		try(PreparedStatement pstmt = connection.prepareStatement(sql)){
			
			pstmt.setString(1, userName);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
