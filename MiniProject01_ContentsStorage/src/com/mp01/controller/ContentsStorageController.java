package com.mp01.controller;

import java.util.ArrayList;
import java.util.List;

import com.mp01.model.dao.JDBCModelDAO;
import com.mp01.model.vo.Book;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.Movie;
import com.mp01.model.vo.User;

public class ContentsStorageController {
	private User user = UserController.user;
	private JDBCModelDAO dbConnect = new JDBCModelDAO();
	
	public boolean addContents(Contents c) {
		return dbConnect.addContents(c) == 1;
	}
	
	public Contents getContents(int contentsId, String contentsType) {
		return dbConnect.getContents(contentsId, contentsType);
	}
	
	public boolean updateContents(int contentsId, String title, String content, String createDate, Contents c) {
		boolean result = false;
		List<Contents> list = user.getContentsList();
		for(int i=0; i<list.size(); i++) {
			Contents currentItem = list.get(i);
			if(currentItem.getContentsId() == contentsId) {
				if(currentItem instanceof Diary) { // 일기
					Diary d = (Diary)currentItem;
					d.setTitle(title);
					d.setContent(content);
					d.setCreateDate(createDate);
					if(c instanceof Diary) {
						Diary dd = (Diary)c;
						d.setFeelings(dd.getFeelings());	
					}
				} else if(currentItem instanceof Movie) { // 영화
					Movie m = (Movie)currentItem;
					m.setTitle(title);
					m.setContent(content);
					m.setCreateDate(createDate);
					if(c instanceof Movie) {
						Movie mm = (Movie)c;
						m.setStarCount(mm.getStarCount());
						m.setReleaseDate(mm.getReleaseDate());
						m.setDirector(mm.getDirector());
						m.setActors(mm.getActors());
						m.setPlace(mm.getPlace());
						m.setWiths(mm.getWiths());
						m.setIsLikeYn(mm.getIsLikeYn());
					}
				} else if(currentItem instanceof Book) { // 책
					Book b = (Book)currentItem;
					b.setTitle(title);
					b.setContent(content);
					b.setCreateDate(createDate);
					if(c instanceof Book) {
						Book bb = (Book)c;
						b.setStarCount(bb.getStarCount());
						b.setAuthor(bb.getAuthor());
						b.setPublisher(bb.getPublisher());
						b.setPrice(bb.getPrice());
						b.setIsLikeYn(bb.getIsLikeYn());
					}
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
//		List<Contents> filteredList = new ArrayList<>(); 
//		List<Contents> list = user.getContentsList();
//		for(int i=0; i<list.size(); i++) {
//			Contents currentItem = list.get(i);
//			if(currentItem.getType().equals(contentsType)) {
//				filteredList.add(currentItem);
//			}
//		}
//		return filteredList;
		return dbConnect.getContentsList(contentsType);
	}
}
