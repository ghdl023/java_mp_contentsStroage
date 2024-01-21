package com.mp01.view;

import java.util.List;
import java.util.Scanner;

import com.mp01.controller.ContentsStorageController;
import com.mp01.controller.UserController;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.User;

public class ContentsStorageView {
	private UserController uc = new UserController();
	private ContentsStorageController csc = new ContentsStorageController();
	private Scanner sc = new Scanner(System.in);
	
	private final String BOOK_TYPE = "book";
	private final String MOVIE_TYPE = "movie";
	private final String DIARY_TYPE = "diary";
	
	public void mainMenu() {
		User user = UserController.user;
		if(false && !user.isLogin()) {
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
			System.out.println("██████  ██  █████  ██████  ██    ██");
			System.out.println("██   ██ ██ ██   ██ ██   ██  ██  ██  ");
			System.out.println("██   ██ ██ ███████ ██████    ████ ");
			System.out.println("██   ██ ██ ██   ██ ██   ██    ██   ");
			System.out.println("██   ██ ██ ██   ██ ██   ██    ██ ");
			System.out.println("██████  ██ ██   ██ ██   ██    ██  ");
			System.out.println("*************************************");
			
			System.out.println("1.목록 보기");
			System.out.println("2.일기 추가");
			System.out.println("3.일기 조회");
			System.out.println("4.일기 수정");
			System.out.println("5.일기 삭제");
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
					System.out.println("프로그램을 종료합니다.");
					break;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void movieMenu() {
		
	}
	
	public void bookMenu() {
		
	}
	
	public void getContentsList(String contentsType) {
		List<Contents> list = csc.getContentsList(contentsType);
		if(!list.isEmpty()) {
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t날짜\t\t제목\t감정");
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				
			} else { // 책
				
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
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
			String createDate = sc.nextLine();
			
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
			
			System.out.print("내용 : ");
			String content = sc.nextLine();
			
			c = new Diary(contentsType, title, content, createDate, feelings);
			
			
		} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
			
		} else { // 책
			
		}
		
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
		
		c = csc.getContents(contentsId);
		
		if(c != null) {
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t날짜\t\t제목\t감정");
				System.out.println(c);
				System.out.println("내용:");
				System.out.println(c.getContent());
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				
			} else { // 책
				
			}
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
	
	public void updateContents(String contentsType) {
		Contents c = null;
		boolean result = false;
		
		System.out.print("수정할 컨텐츠 NO을 입력하세요. : ");
		int contentsId = Integer.parseInt(sc.nextLine());
		
		if(csc.getContents(contentsId) == null) {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
			return;
		}
		
		System.out.print("제목을 입력해주세요. : ");
		String title = sc.nextLine();
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
			String createDate = sc.nextLine();
			
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
			
			System.out.print("내용 : ");
			String content = sc.nextLine();
			
			result = csc.updateContents(contentsId, title, content, createDate, feelings);
			
			
		} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
			
		} else { // 책
			
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
		
		if(csc.deleteContents(contentsId)) {
			System.out.println("컨텐츠가 삭제 되었습니다.");
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
}
