package com.mp01.service;

import java.sql.Connection;
import java.util.List;

import com.mp01.common.DBCP;
import com.mp01.model.vo.Contents;
import com.mp01.repository.ContentsRepository;

public class ContentsService {
	
	private ContentsRepository contentsRepository = new ContentsRepository();

	public boolean addContents(Contents c) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.addContents(conn, c);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public Contents getContents(int contentsId, String contentsType) {
		Connection conn = DBCP.getConnection(true);
		return contentsRepository.getContents(conn, contentsId, contentsType);
	}
	
	public boolean updateContents(Contents c) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.updateContents(conn, c);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public boolean deleteContents(int contentsId, String contentsType) {
		Connection conn = DBCP.getConnection(false);
		int result = contentsRepository.deleteContents(conn, contentsId, contentsType);
		
		if(result > 0) {
			DBCP.commit(conn);
		} else {
			DBCP.rollback(conn);
		}
		
		return result == 1;
	}
	
	public List<Contents> getContentsList(String contentsType) {
		Connection conn = DBCP.getConnection(true);
		return contentsRepository.getContentsList(conn, contentsType);
	}
}
