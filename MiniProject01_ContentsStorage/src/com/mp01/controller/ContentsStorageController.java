package com.mp01.controller;

import java.util.List;

import com.mp01.model.dao.JDBCModelDAO;
import com.mp01.model.vo.Contents;

public class ContentsStorageController {
	private JDBCModelDAO dbConnect = new JDBCModelDAO();
	
	public boolean addContents(Contents c) {
		return dbConnect.addContents(c) == 1;
	}
	
	public Contents getContents(int contentsId, String contentsType) {
		return dbConnect.getContents(contentsId, contentsType);
	}
	
	public boolean updateContents(Contents c) {
		return dbConnect.updateContents(c);
	}
	
	public boolean deleteContents(int contentsId, String contentsType) {
		return dbConnect.deleteContents(contentsId, contentsType);
	}
	
	public List<Contents> getContentsList(String contentsType) {
		return dbConnect.getContentsList(contentsType);
	}
}
