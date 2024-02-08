package com.mp01.service;

import java.sql.Connection;
import java.util.List;

import com.mp01.common.DBCP;
import com.mp01.model.vo.Contents;
import com.mp01.repository.ContentsRepository;

public class ContentsService {
	
	private ContentsRepository contentsRepository = new ContentsRepository();

	public boolean addContents(int userId, Contents c) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.addContents(conn, userId, c);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public Contents getContents(int userId, int contentsId, String contentsType) {
		Connection conn = DBCP.getConnection(true);
		return contentsRepository.getContents(conn, userId, contentsId, contentsType);
	}
	
	public boolean updateContents(int userId, Contents c) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.updateContents(conn, userId, c);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public boolean deleteContents(int userId, int contentsId, String contentsType) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.deleteContents(conn, userId, contentsId, contentsType);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public List<Contents> getContentsList(int userId, String contentsType) {
		Connection conn = DBCP.getConnection(true);
		return contentsRepository.getContentsList(conn, userId, contentsType);
	}
}
