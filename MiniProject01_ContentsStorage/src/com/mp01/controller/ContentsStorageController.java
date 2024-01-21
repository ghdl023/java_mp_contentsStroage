package com.mp01.controller;

import java.util.ArrayList;
import java.util.List;

import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.User;

public class ContentsStorageController {
	private User user = UserController.user;
	
	
	public boolean addContents(Contents c) {
		return user.getContentsList().add(c);
		
	}
	
	public Contents getContents(int contentsId) {
		Contents targetContents = null;
		List<Contents> list = user.getContentsList();
		for(int i=0; i<list.size(); i++) {
			Contents currentItem = list.get(i);
			if(currentItem.getContentsId() == contentsId) {
				targetContents = currentItem;
				break;
			}
		}
		return targetContents;
	}
	
	public boolean updateContents(int contentsId, String title, String content, String createDate, String feelings) {
		boolean result = false;
		List<Contents> list = user.getContentsList();
		for(int i=0; i<list.size(); i++) {
			Contents currentItem = list.get(i);
			if(currentItem.getContentsId() == contentsId) {
				if(currentItem instanceof Diary) {
					Diary d = (Diary)currentItem;
					d.setTitle(title);
					d.setContent(content);
					d.setCreateDate(createDate);
					d.setFeelings(feelings);
				}
				result = true;
				break;
			}
		}
		return result;
	}
	
	public boolean deleteContents(int contentsId) {
		boolean result = false;
		List<Contents> list = user.getContentsList();
		for(int i=0; i<list.size(); i++) {
			Contents currentItem = list.get(i);
			if(currentItem.getContentsId() == contentsId) {
				list.remove(i);
				result = true;
				break;
			}
		}
		return result;
	}
	
	public List<Contents> getContentsList(String contentsType) {
		List<Contents> filteredList = new ArrayList<>(); 
		List<Contents> list = user.getContentsList();
		for(int i=0; i<list.size(); i++) {
			Contents currentItem = list.get(i);
			if(currentItem.getType().equals(contentsType)) {
				filteredList.add(currentItem);
			}
		}
		return filteredList;
	}
}
