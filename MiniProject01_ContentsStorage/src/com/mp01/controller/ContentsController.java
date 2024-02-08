package com.mp01.controller;

import java.util.List;

import com.mp01.model.vo.Contents;
import com.mp01.service.ContentsService;
import com.mp01.view.ContentsStorageView;

public class ContentsController {
	private ContentsService contentsService = new ContentsService();
	
	public void addContents(int userId, Contents c) {
		boolean result = contentsService.addContents(userId, c);
		if(result) {
			new ContentsStorageView().displaySuccess("컨텐츠가 추가 되었습니다.");
		} else {
			new ContentsStorageView().displayFailed("컨텐츠 추가를 실패했습니다.");
		}
	}
	
	public void getContents(int userId, int contentsId, String contentsType) {
		Contents c = contentsService.getContents(userId, contentsId, contentsType);
		
		if(c != null) {
			new ContentsStorageView().displayContents(c);
		} else {
			new ContentsStorageView().displayFailed("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
	
	public void updateContents(int userId, Contents c) {
		boolean result = contentsService.updateContents(userId, c);
		
		if(result) {
			new ContentsStorageView().displaySuccess("컨텐츠가 수정 되었습니다.");
		} else {
			new ContentsStorageView().displayFailed("컨텐츠 수정을 실패했습니다.");
		}
	}
	
	public void deleteContents(int userId, int contentsId, String contentsType) {
		boolean result = contentsService.deleteContents(userId, contentsId, contentsType);
		if(result) {
			new ContentsStorageView().displaySuccess("컨텐츠가 삭제 되었습니다.");
		} else {
			new ContentsStorageView().displayFailed("컨텐츠 삭제를 실패했습니다.");
		}
	}
	
	public List<Contents> getContentsList(int userId, String contentsType) {
		List<Contents> list = contentsService.getContentsList(userId, contentsType);
		if(!list.isEmpty()) {
			new ContentsStorageView().displayContentsList(list);
		} else {
			new ContentsStorageView().displayFailed("조회된 결과가 없습니다. 새 컨텐츠를 생성하여 주세요.");
		}
		return list;	
	}
}
