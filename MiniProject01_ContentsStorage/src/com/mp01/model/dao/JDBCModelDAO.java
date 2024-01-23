package com.mp01.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mp01.controller.UserController;
import com.mp01.model.vo.Book;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.Movie;
import com.mp01.model.vo.User;
import com.mp01.view.ContentsStorageView;

public class JDBCModelDAO {

	private User user = UserController.user;
	
	private final String DRIVER_NAME = "oracle.jdbc.driver.OracleDriver";
	private final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String DATABASE_USER_ID = "kh";
	private final String DATABASE_USER_PW = "kh";
	
	public List<Contents> getContentsList(String contentsType) {
		List<Contents> list = new ArrayList<>();
		
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT * FROM " + contentsType + " WHERE 1=1 AND USER_ID = ?";
		
		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_ID, DATABASE_USER_PW);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			
			preparedStatement.setString(1, user.getUserId());
			
			try (ResultSet resultSet = preparedStatement.executeQuery()){
				
				while(resultSet.next()) {
					Contents c = null;
					
					String contentsId = resultSet.getString("contents_id");
					String type = resultSet.getString("type");
					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String createDate = resultSet.getString("create_date");
					
					if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
						String feelings = resultSet.getString("feelings");
						c = new Diary(type, title, content, createDate, feelings);	
						
					} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
//						c = new Movie(type, title, content, createDate, feelings);	
						
					} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
//						c = new Book(type, title, content, createDate, feelings);	
						
					} 
					
					list.add(c);
				}
			}  catch(Exception e) {
				e.printStackTrace();
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}

	public int addContents(Contents c) {
		int insertCount = 0;

		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "";
		
		if(c instanceof Diary) {
			sql = "INSERT INTO diary VALUES(?, ?, ?, ?, ?, ?, ?)";
		} else if(c instanceof Movie) {
			sql = "INSERT INTO movie VALUES(?, ?, ?, ?, ?, ?, ?)";
		} else if(c instanceof Book) {
			sql = "INSERT INTO book VALUES(?, ?, ?, ?, ?, ?, ?)";
		}

		try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER_ID, DATABASE_USER_PW);
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, c.getContentsId());
			preparedStatement.setString(2, c.getType());
			preparedStatement.setString(3, c.getTitle());
			preparedStatement.setString(4, c.getContent());
			preparedStatement.setString(5, c.getCreateDate());

			if(c instanceof Diary) {
				Diary d = (Diary)c;
				preparedStatement.setString(6, d.getFeelings()); 	
			}
			preparedStatement.setString(7, user.getUserId());
			
			insertCount = preparedStatement.executeUpdate();
			
			System.out.println("insertCount: " + insertCount );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return insertCount;
	}
}
