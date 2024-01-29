package com.mp01.view;

import java.util.List;
import java.util.Scanner;

import com.mp01.controller.ContentsStorageController;
import com.mp01.controller.UserController;
import com.mp01.model.vo.Book;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.Movie;
import com.mp01.model.vo.User;

public class ContentsStorageView {
	private UserController uc = new UserController();
	private ContentsStorageController csc = new ContentsStorageController();
	private Scanner sc = new Scanner(System.in);
	
	public static final String BOOK_TYPE = "book";
	public static final String MOVIE_TYPE = "movie";
	public static final String DIARY_TYPE = "diary";
	
	public void mainMenu() {
		User user = UserController.user;
		if(!user.isLogin()) {
			System.out.println("===== LOGIN =====");
			
			while(true) {
				System.out.print("id: " );
				String userId = sc.nextLine();
				
				System.out.print("password: ");
				String userPassword = sc.nextLine();
				if(uc.signIn(userId, userPassword)) {
					break;
				}
			}
		}
		
		while(true) {
			System.out.println("===== 컨텐츠 선택 =====");
			System.out.println("1.일기");
			System.out.println("2.영화");
			System.out.println("3.책");
			
			System.out.print("메뉴 선택 : ");
			int selMenu = Integer.parseInt(sc.nextLine());
			
			switch(selMenu) {
				case 1:
					diaryMenu();
					break;
				case 2:
					movieMenu();
					break;
				case 3:
					bookMenu();
					break;
				case 9: 
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void diaryMenu() {
		while(true) {
			System.out.println("*************************************");
			System.out.println(" ____  _                  ");
			System.out.println("|  _ \\(_) __ _ _ __ _   _ ");
			System.out.println("| | | | |/ _` | '__| | | |");
			System.out.println("| |_| | | (_| | |  | |_| |");
			System.out.println("|____/|_|\\__,_|_|   \\__, |");
			System.out.println("                     |___/ ");
			System.out.println();
			System.out.println("*************************************");
			
			System.out.println("1.목록 보기");
			System.out.println("2.일기 추가");
			System.out.println("3.일기 조회");
			System.out.println("4.일기 수정");
			System.out.println("5.일기 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			int selMenu = Integer.parseInt(sc.nextLine());
			switch(selMenu) {
				case 1:
					getContentsList(DIARY_TYPE);
					break;
				case 2:
					addContents(DIARY_TYPE);
					break;
				case 3:
					getContents(DIARY_TYPE);
					break;
				case 4:
					updateContents(DIARY_TYPE);
					break;
				case 5:
					deleteContents(DIARY_TYPE);
					break;
				case 9: 
					System.out.println("메인으로 돌아가기");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void movieMenu() {
		while(true) {
			System.out.println("*************************************");
			System.out.println(" __  __            _      ");
			System.out.println("|  \\/  | _____   _(_) ___ ");
			System.out.println("| |\\/| |/ _ \\ \\ / / |/ _ \\");
			System.out.println("| |  | | (_) \\ V /| |  __/");
			System.out.println("|_|  |_|\\___/ \\_/ |_|\\___|");
			System.out.println();
			System.out.println("*************************************");
			
			System.out.println("1.목록 보기");
			System.out.println("2.영화 추가");
			System.out.println("3.영화 조회");
			System.out.println("4.영화 수정");
			System.out.println("5.영화 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			int selMenu = Integer.parseInt(sc.nextLine());
			switch(selMenu) {
				case 1:
					getContentsList(MOVIE_TYPE);
					break;
				case 2:
					addContents(MOVIE_TYPE);
					break;
				case 3:
					getContents(MOVIE_TYPE);
					break;
				case 4:
					updateContents(MOVIE_TYPE);
					break;
				case 5:
					deleteContents(MOVIE_TYPE);
					break;
				case 9: 
					System.out.println("메인으로 돌아가기");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void bookMenu() {
		while(true) {
			System.out.println("*************************************");
			System.out.println(" ____              _    ");
			System.out.println("| __ )  ___   ___ | | __");
			System.out.println("|  _ \\ / _ \\ / _ \\| |/ /");
			System.out.println("| |_) | (_) | (_) |   < ");
			System.out.println("|____/ \\___/ \\___/|_|\\_\\");
			System.out.println();
			System.out.println("*************************************");
			
			System.out.println("1.목록 보기");
			System.out.println("2.책 추가");
			System.out.println("3.책 조회");
			System.out.println("4.책 수정");
			System.out.println("5.책 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			int selMenu = Integer.parseInt(sc.nextLine());
			switch(selMenu) {
				case 1:
					getContentsList(BOOK_TYPE);
					break;
				case 2:
					addContents(BOOK_TYPE);
					break;
				case 3:
					getContents(BOOK_TYPE);
					break;
				case 4:
					updateContents(BOOK_TYPE);
					break;
				case 5:
					deleteContents(BOOK_TYPE);
					break;
				case 9: 
					System.out.println("메인으로 돌아가기");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void getContentsList(String contentsType) {
		List<Contents> list = csc.getContentsList(contentsType);
		if(!list.isEmpty()) {
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t작성일\t\t제목\t감정");
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				System.out.println("NO\t작성일\t\t제목\t개봉일\t감독\t출연배우\t별점");
			} else { // 책
				System.out.println("NO\t작성일\t\t제목\t작가\t출판사\t가격\t별점");
			}
			for(Contents c : list) {
				System.out.println(c);
			}
		} else {
			System.out.println("조회된 결과가 없습니다. 새 컨텐츠를 생성하여 주세요.");
		}
	}
	
	public void addContents(String contentsType) {
		Contents c = null;
		
		System.out.print("제목을 입력해주세요. : ");
		String title = sc.nextLine();
		
		System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
		String createDate = sc.nextLine();
		
		String content = "";
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
	
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Diary(contentsType, title, content, createDate, feelings);
			
		} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
			System.out.print("개봉일을 입력해주세요. : ");
			String releaseDate = sc.nextLine();
			
			System.out.print("감독을 입력해주세요. : ");
			String director = sc.nextLine();
			
			System.out.print("출연배우들을 입력해주세요.(,로 구분) : ");
			String actors = sc.nextLine();
			
			System.out.print("별점을 입력해주세요.(1~5)");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("영화를 어디서 보셨나요? : ");
			String place = sc.nextLine();
			
			System.out.print("누구와 함께 보셨나요? : ");
			String with = sc.nextLine();
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Movie(contentsType, title, content, createDate, releaseDate, director, actors, place, with, isLikeYn, starCount);
			
		} else { // 책
			System.out.print("작가를 입력해주세요. : ");
			String author = sc.nextLine();
			
			System.out.print("출판사를 입력해주세요. : ");
			String publisher = sc.nextLine();
			
			System.out.print("가격을 입력해주세요. : ");
			int price = Integer.parseInt(sc.nextLine());
			
			System.out.print("별점을 입력해주세요.(1~5)");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Book(contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);
		}
		
		System.out.println("111111");
		
		if(csc.addContents(c)) {
			System.out.println("컨텐츠가 생성 되었습니다.");
		} else {
			System.out.println("컨텐츠 생성을 실패하였습니다.");
		}
	}
	
	public void getContents(String contentsType) {
		Contents c = null;
		
		System.out.print("조회할 컨텐츠 NO을 입력하세요. : ");
		int contentsId = Integer.parseInt(sc.nextLine());
		
		c = csc.getContents(contentsId, contentsType);
		
		if(c != null) {
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t날짜\t\t제목\t감정");
				System.out.println(c);
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				System.out.println("NO\t날짜\t\t제목\t개봉일\t감독\t출연배우\t별점");
				System.out.println(c);
			} else { // 책
				System.out.println("NO\t날짜\t\t제목\t작가\t출판사\t가격\t별점");
				System.out.println(c);
			}
			System.out.println("내용:");
			System.out.println(c.getContent());
			
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
	
	public void updateContents(String contentsType) {
		boolean result = false;
		
		System.out.print("수정할 컨텐츠 NO을 입력하세요. : ");
		int contentsId = Integer.parseInt(sc.nextLine());
		
		if(csc.getContents(contentsId, contentsType) == null) {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
			return;
		}
		
		System.out.print("제목을 입력해주세요. : ");
		String title = sc.nextLine();
		
		System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
		String createDate = sc.nextLine();
		
		String content = "";
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			Diary d = new Diary(feelings);
			
			result = csc.updateContents(contentsId, title, content, createDate, d);
			
		} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
			System.out.print("개봉일을 입력해주세요. : ");
			String releaseDate = sc.nextLine();
			
			System.out.print("감독을 입력해주세요. : ");
			String director = sc.nextLine();
			
			System.out.print("출연배우들을 입력해주세요.(,로 구분) : ");
			String actors = sc.nextLine();
			
			System.out.print("별점을 입력해주세요.(1~5)");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("영화를 어디서 보셨나요? : ");
			String place = sc.nextLine();
			
			System.out.print("누구와 함께 보셨나요? : ");
			String with = sc.nextLine();
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			Movie m = new Movie(contentsType, title, content, createDate, releaseDate, director, actors, place, with, isLikeYn, starCount);
			
			result = csc.updateContents(contentsId, title, content, createDate, m);
			
		} else { // 책
			System.out.print("작가를 입력해주세요. : ");
			String author = sc.nextLine();
			
			System.out.print("출판사를 입력해주세요. : ");
			String publisher = sc.nextLine();
			
			System.out.print("가격을 입력해주세요. : ");
			int price = Integer.parseInt(sc.nextLine());
			
			System.out.print("별점을 입력해주세요.(1~5)");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			Book b = new Book(contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);
			
			result = csc.updateContents(contentsId, title, content, createDate, b);
		}
		
		if(result) {
			System.out.println("컨텐츠가 수정 되었습니다.");
		} else {
			System.out.println("컨텐츠 수정을 실패하였습니다.");
		}
	}
	
	public void deleteContents(String contentsType) {
		System.out.print("삭제할 컨텐츠 NO을 입력하세요. : ");
		int contentsId = Integer.parseInt(sc.nextLine());
		
		if(csc.deleteContents(contentsId, contentsType)) {
			System.out.println("컨텐츠가 삭제 되었습니다.");
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
}
