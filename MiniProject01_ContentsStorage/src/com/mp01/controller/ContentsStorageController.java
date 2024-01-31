package com.mp01.controller;

import java.util.List;

import com.mp01.model.vo.Contents;
import com.mp01.service.ContentsService;

public class ContentsStorageController {
//	private JDBCModelDAO dbConnect = new JDBCModelDAO();
	private ContentsService contentsService = new ContentsService();
	
	public boolean addContents(Contents c) {
		return contentsService.addContents(c);
		
	}
	
	public Contents getContents(int contentsId, String contentsType) {
		return contentsService.getContents(contentsId, contentsType);
	}
	
	public boolean updateContents(Contents c) {
		return contentsService.updateContents(c);
	}
	
	public boolean deleteContents(int contentsId, String contentsType) {
		return contentsService.deleteContents(contentsId, contentsType);
	}
	
	public List<Contents> getContentsList(String contentsType) {
		return contentsService.getContentsList(contentsType);
	}
}
