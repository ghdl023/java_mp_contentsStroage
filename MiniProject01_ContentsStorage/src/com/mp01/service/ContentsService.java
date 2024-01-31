package com.mp01.service;

import java.util.List;

import com.mp01.model.vo.Contents;
import com.mp01.repository.ContentsRepository;

public class ContentsService {
	
	private ContentsRepository contentsRepository = new ContentsRepository();

	public boolean addContents(Contents c) {
		return contentsRepository.addContents(c) == 1;
	}
	
	public Contents getContents(int contentsId, String contentsType) {
		return contentsRepository.getContents(contentsId, contentsType);
	}
	
	public boolean updateContents(Contents c) {
		return contentsRepository.updateContents(c);
	}
	
	public boolean deleteContents(int contentsId, String contentsType) {
		return contentsRepository.deleteContents(contentsId, contentsType);
	}
	
	public List<Contents> getContentsList(String contentsType) {
		return contentsRepository.getContentsList(contentsType);
	}
}
