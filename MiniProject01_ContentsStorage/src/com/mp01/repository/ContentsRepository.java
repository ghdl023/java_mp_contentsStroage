package com.mp01.repository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mp01.model.vo.Book;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.Movie;
import com.mp01.model.vo.User;
import com.mp01.view.ContentsStorageView;

public class ContentsRepository {
	private User user = User.getInstance();
	private Properties prop = new Properties();
	
	{
		try {
			prop.loadFromXML(new FileInputStream("resource/contentsQuery.xml"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Contents> getContentsList(Connection connection, String contentsType) {
		List<Contents> list = new ArrayList<>();

		String sql = "";
		if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, D.FEELINGS ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_DIARY D ON C.id = D.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 1";
			sql = prop.getProperty("getContentsList.diary");

		} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, TO_CHAR(M.RELEASE_DATE, 'YYYY-MM-DD') RELEASE_DATE, M.DIRECTOR, M.ACTORS, M.PLACE, M.WITHS, M.IS_LIKE_YN, M.STAR_COUNT ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_MOVIE M ON C.id = M.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 2";
			sql = prop.getProperty("getContentsList.movie");

		} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, B.AUTHOR, B.PUBLISHER, B.PRICE, B.IS_LIKE_YN, B.STAR_COUNT ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_BOOK B ON C.id = B.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 3";
			sql = prop.getProperty("getContentsList.book");
		}
		sql += "AND USER_ID = '" + user.getUserId() + "'";


		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

			try (ResultSet resultSet = preparedStatement.executeQuery()){

				while(resultSet.next()) {
					Contents c = null;

					int contentsId = resultSet.getInt("id");
					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String createDate = resultSet.getString("create_date");

					//					System.out.println("contentsId: " + contentsId);

					if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
						String feelings = resultSet.getString("feelings");
						c = new Diary(contentsId, contentsType, title, content, createDate, feelings);	

					} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
						String releaseDate = resultSet.getString("release_date");
						String director = resultSet.getString("director");
						String actors = resultSet.getString("actors");
						String place = resultSet.getString("place");
						String withs = resultSet.getString("withs");
						String isLikeYn = resultSet.getString("is_like_yn");
						int starCount = resultSet.getInt("star_count");
						c = new Movie(contentsId, contentsType, title, content, createDate, releaseDate, director, actors, place, withs, isLikeYn, starCount);	

					} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
						String author = resultSet.getString("author");
						String publisher = resultSet.getString("publisher");
						int price = resultSet.getInt("price");
						String isLikeYn = resultSet.getString("is_like_yn");
						int starCount = resultSet.getInt("star_count");
						c = new Book(contentsId, contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);	

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

	public int addContents(Connection connection, Contents c) {
		int result = 0;

//		String sql = "INSERT INTO CONTENTS(id, title, content, create_date, contents_type_id, user_id) VALUES(SEQ_CTS.NEXTVAL, ?, ?, ?, ?, ?)";
		String sql = prop.getProperty("addContents.contents");
		
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"ID"})) { // Statement.RETURN_GENERATED_KEYS not working...

			// Auto Commit off setting
//			connection.setAutoCommit(false); // 또는 Run Configuration > Arguments > VM 에 다음 명령어 "-Doracle.jdbc.autoCommitSpecCompliant=false"
//			Savepoint sp = connection.setSavepoint();
			
			//			System.out.println(c.getTitle());
			//			System.out.println(c.getContent());
			//			System.out.println(c.getCreateDate());
			//			System.out.println(user.getUserId());

			int contents_type_id = c instanceof Diary ? 1 : (c instanceof Movie ? 2 : 3);

			preparedStatement.setString(1, c.getTitle());
			preparedStatement.setString(2, c.getContent());
			preparedStatement.setString(3, c.getCreateDate());
			preparedStatement.setInt(4, contents_type_id);
			preparedStatement.setString(5, user.getUserId());

			result = preparedStatement.executeUpdate();
			
			//			System.out.println("insertCount: " + insertCount );

			ResultSet rs = preparedStatement.getGeneratedKeys();
			int generatedKey = 0;

			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}

			//			System.out.println("Inserted record's ID: " + generatedKey);

			if(c instanceof Diary) {
				Diary d = (Diary)c;
//				sql = "INSERT INTO CONTENTS_DIARY(id, contents_id, feelings) VALUES(SEQ_CTS_DIARY.NEXTVAL, ?, ?)";
				sql = prop.getProperty("addContents.diary");
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)){
					preparedStatement2.setInt(1, generatedKey);
					preparedStatement2.setString(2, d.getFeelings());

					result = preparedStatement2.executeUpdate();
					//					System.out.println("insertCount: " + insertCount );

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(c instanceof Movie) {
				Movie m = (Movie)c;
//				sql = "INSERT INTO CONTENTS_MOVIE(id, contents_id, release_date, director, actors, place, withs, is_like_yn, star_count)"
//						+ " VALUES(SEQ_CTS_MOVIE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)";
				sql = prop.getProperty("addContents.movie");
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)){
					preparedStatement2.setInt(1, generatedKey);
					preparedStatement2.setString(2, m.getReleaseDate());
					preparedStatement2.setString(3, m.getDirector());
					preparedStatement2.setString(4, m.getActors());
					preparedStatement2.setString(5, m.getPlace());
					preparedStatement2.setString(6, m.getWiths());
					preparedStatement2.setString(7, m.getIsLikeYn());
					preparedStatement2.setInt(8, m.getStarCount());

					result = preparedStatement2.executeUpdate();

					//					System.out.println("insertCount: " + insertCount );

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if(c instanceof Book) {
				Book b = (Book)c;
//				sql = "INSERT INTO CONTENTS_BOOK(id, contents_id, author, publisher, price, is_like_yn, star_count)"
//						+ " VALUES(SEQ_CTS_BOOK.NEXTVAL, ?, ?, ?, ?, ?, ?)";
				sql = prop.getProperty("addContents.book");
				try (PreparedStatement preparedStatement2 = connection.prepareStatement(sql)){
					preparedStatement2.setInt(1, generatedKey);
					preparedStatement2.setString(2, b.getAuthor());
					preparedStatement2.setString(3, b.getPublisher());
					preparedStatement2.setInt(4, b.getPrice());
					preparedStatement2.setString(5, b.getIsLikeYn());
					preparedStatement2.setInt(6, b.getStarCount());

					result = preparedStatement2.executeUpdate();

					//					System.out.println("insertCount: " + insertCount );

				} catch (Exception e) {
					e.printStackTrace();
				}
			} 

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public Contents getContents(Connection connection, int contentsId, String contentsType) {
		String sql = "";

		if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, D.FEELINGS ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_DIARY D ON C.id = D.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 1 ";
			sql = prop.getProperty("getContents.diary");

		} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, TO_CHAR(M.RELEASE_DATE, 'YYYY-MM-DD') RELEASE_DATE, M.DIRECTOR, M.ACTORS, M.PLACE, M.WITHS, M.IS_LIKE_YN, M.STAR_COUNT ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_MOVIE M ON C.id = M.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 2 ";
			sql = prop.getProperty("getContents.movie");
			
		} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
//			sql += "SELECT C.ID, C.TITLE, C.CONTENT, TO_CHAR(C.CREATE_DATE, 'YYYY-MM-DD') CREATE_DATE, C.USER_ID, B.AUTHOR, B.PUBLISHER, B.PRICE, B.IS_LIKE_YN, B.STAR_COUNT ";
//			sql += "FROM CONTENTS C LEFT JOIN CONTENTS_BOOK B ON C.id = B.contents_id ";
//			sql += "WHERE 1=1 ";
//			sql += "AND CONTENTS_TYPE_ID = 3 ";
			sql = prop.getProperty("getContents.book");
		}
		sql += "AND C.id = " + contentsId + " ";
		sql += "AND USER_ID = '" + user.getUserId() + "'";


		Contents c = null;

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){

			try (ResultSet resultSet = preparedStatement.executeQuery()){

				while(resultSet.next()) {
					c = null;

					String title = resultSet.getString("title");
					String content = resultSet.getString("content");
					String createDate = resultSet.getString("create_date");

					if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
						String feelings = resultSet.getString("feelings");
						c = new Diary(contentsId, contentsType, title, content, createDate, feelings);	

					} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
						String releaseDate = resultSet.getString("release_date");
						String director = resultSet.getString("director");
						String actors = resultSet.getString("actors");
						String place = resultSet.getString("place");
						String withs = resultSet.getString("withs");
						String isLikeYn = resultSet.getString("is_like_yn");
						int starCount = resultSet.getInt("star_count");
						c = new Movie(contentsId, contentsType, title, content, createDate, releaseDate, director, actors, place, withs, isLikeYn, starCount);	

					} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
						String author = resultSet.getString("author");
						String publisher = resultSet.getString("publisher");
						int price = resultSet.getInt("price");
						String isLikeYn = resultSet.getString("is_like_yn");
						int starCount = resultSet.getInt("star_count");
						c = new Book(contentsId, contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);	

					} 
				}
			}  catch(Exception e) {
				e.printStackTrace();
			}

		} catch(Exception e) {
			e.printStackTrace();
		}

		return c;
	}

	public int updateContents(Connection connection, Contents c) {
		int result = 0;

		String contentsType = c.getType();
//		String sql = "UPDATE CONTENTS SET TITLE=?, CONTENT=?, CREATE_DATE=? WHERE ID=?";
		String sql = prop.getProperty("updateContents.contents");

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			
			// Auto Commit off setting
//			connection.setAutoCommit(false);
//			Savepoint sp = connection.setSavepoint();

			preparedStatement.setString(1, c.getTitle());
			preparedStatement.setString(2, c.getContent());
			preparedStatement.setString(3, c.getCreateDate());
			preparedStatement.setInt(4, c.getContentsId());

			result = preparedStatement.executeUpdate();

			if(contentsType.equals(ContentsStorageView.DIARY_TYPE)) {
//				sql = "UPDATE CONTENTS_DIARY "
//						+ "SET FEELINGS=? "
//						+ "WHERE 1=1 "
//						+ "AND CONTENTS_ID=?";
				sql = prop.getProperty("updateContents.diary");

				try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) {
					Diary d = (Diary) c;
					preparedStatement2.setString(1, d.getFeelings());
					preparedStatement2.setInt(2, d.getContentsId());

					result = preparedStatement2.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}


			} else if(contentsType.equals(ContentsStorageView.MOVIE_TYPE)) {
//				sql = "UPDATE CONTENTS_MOVIE "
//						+ "SET RELEASE_DATE=?, "
//						+ "DIRECTOR=?, "
//						+ "ACTORS=?, "
//						+ "PLACE=?, "
//						+ "WITHS=?, "
//						+ "IS_LIKE_YN=?, "
//						+ "STAR_COUNT=? "
//						+ "WHERE 1=1 "
//						+ "AND CONTENTS_ID=?";
				sql = prop.getProperty("updateContents.movie");

				try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) { 
					Movie m = (Movie)c;
					preparedStatement2.setString(1, m.getReleaseDate());
					preparedStatement2.setString(2, m.getDirector());
					preparedStatement2.setString(3, m.getActors());
					preparedStatement2.setString(4, m.getPlace());
					preparedStatement2.setString(5, m.getWiths());
					preparedStatement2.setString(6, m.getIsLikeYn());
					preparedStatement2.setInt(7, m.getStarCount());
					preparedStatement2.setInt(8, m.getContentsId());

					result = preparedStatement2.executeUpdate();
				} catch(Exception e) {
					e.printStackTrace();
				}

			} else if(contentsType.equals(ContentsStorageView.BOOK_TYPE)) {
//				sql = "UPDATE CONTENTS_BOOK "
//						+ "SET AUTHOR=?, "
//						+ "PUBLISHER=?, "
//						+ "PRICE=?, "
//						+ "IS_LIKE_YN=? "
//						+ "STAR_COUNT=? "
//						+ "WHERE 1=1 "
//						+ "AND CONTENTS_ID=?";
				sql = prop.getProperty("updateContents.book");

				try(PreparedStatement preparedStatement2 = connection.prepareStatement(sql)) {
					Book b = (Book)c;
					preparedStatement2.setString(1, b.getAuthor());
					preparedStatement2.setString(2, b.getPublisher());
					preparedStatement2.setInt(3, b.getPrice());
					preparedStatement2.setString(4, b.getIsLikeYn());
					preparedStatement2.setInt(5, b.getStarCount());
					preparedStatement2.setInt(6, b.getContentsId());

					result = preparedStatement2.executeUpdate();

				} catch(Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int deleteContents(Connection connection, int contentsId, String contentsType) {
//		String sql = String.format("DELETE FROM CONTENTS WHERE 1=1 AND ID=%d AND USER_ID='%s'", contentsId, user.getUserId());		
		String sql = prop.getProperty("deleteContents");
		int result = 0;

		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
			preparedStatement.setInt(1, contentsId);
			preparedStatement.setString(2, user.getUserId());
			
			result = preparedStatement.executeUpdate();

		} catch(Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
