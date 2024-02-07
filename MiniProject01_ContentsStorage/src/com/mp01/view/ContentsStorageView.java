package com.mp01.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mp01.controller.ContentsController;
import com.mp01.controller.UserController;
import com.mp01.model.vo.Book;
import com.mp01.model.vo.Contents;
import com.mp01.model.vo.Diary;
import com.mp01.model.vo.Movie;
import com.mp01.model.vo.User;

public class ContentsStorageView {
	private UserController uc = new UserController();
	private ContentsController csc = new ContentsController();
	private Scanner sc = new Scanner(System.in);
	
	public static final String BOOK_TYPE = "book";
	public static final String MOVIE_TYPE = "movie";
	public static final String DIARY_TYPE = "diary";
	
	private User user = User.getInstance(); 
	
	public void mainMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println(" __  __                           _           ");
			System.out.println("|  \\/  | ___ _ __ ___   ___  _ __(_) ___  ___ ");
			System.out.println("| |\\/| |/ _ \\ '_ ` _ \\ / _ \\| '__| |/ _ \\/ __|");
			System.out.println("| |  | |  __/ | | | | | (_) | |  | |  __/\\__ \\");
			System.out.println("|_|  |_|\\___|_| |_| |_|\\___/|_|  |_|\\___||___/");
			System.out.println();
			System.out.println("***********************************************");

			System.out.println("1.로그인");
			System.out.println("2.회원가입");
			System.out.println("3.프로그램 종료");

			System.out.print("메뉴 선택 : ");

			int selMenu = Integer.parseInt(sc.nextLine());

			switch(selMenu) {
				case 1: 
					signInMenu();
					break;
				case 2:
					signUpMenu();
					break;
				case 3: 
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void signInMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println(" _     ___   ____ ___ _   _ ");
			System.out.println("| |   / _ \\ / ___|_ _| \\ | |");
			System.out.println("| |  | | | | |  _ | ||  \\| |");
			System.out.println("| |__| |_| | |_| || || |\\  |");
			System.out.println("| |__| |_| | |_| || || |\\  |");
			System.out.println("|_____\\___/ \\____|___|_| \\_|");
			System.out.println();
			System.out.println("***********************************************");
			
			System.out.println(">>>> 메인화면으로 이동하려면 exit를 입력하세요.");
			System.out.print("아이디: " );
			String userId = sc.nextLine();
			
			if(userId.toLowerCase().equals("exit")) {
				return;
			}
	
			System.out.print("비밀번호: ");
			String userPassword = sc.nextLine();
			uc.signIn(userId, userPassword);
			System.out.println("끝!");
			if(user.getStatus() == 'D') {
				user.setStatus('A');
				return;
			}
		}
	}
	
	public void signUpMenu() {
		while(true) {
			System.out.println(">>>> 메인화면으로 이동하려면 exit를 입력하세요.");
			System.out.print("아이디: " );
			String userId = sc.nextLine();
			
			if(userId.toLowerCase().equals("exit")) {
				return;
			}
	
			System.out.print("비밀번호: ");
			String userPassword = sc.nextLine();
			
			uc.signUp(userId, userPassword);
			
			if(user.getStatus() == 'D') {
				user.setStatus('A');
				return;
			}
		}
	}
	
	public void homeMenu() {

		while(true) {
			System.out.println("***********************************************");
			System.out.println(" _   _  ___  __  __ _____ ");
			System.out.println("| | | |/ _ \\|  \\/  | ____|");
			System.out.println("| |_| | | | | |\\/| |  _|  ");
			System.out.println("|  _  | |_| | |  | | |___ ");
			System.out.println("|_| |_|\\___/|_|  |_|_____|");
			System.out.println();
			System.out.println("***********************************************");
			
			System.out.println("1.일기");
			System.out.println("2.영화");
			System.out.println("3.책");
			System.out.println("4.마이페이지");
			System.out.println("9.로그아웃");
			
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
				case 4:
					myPageMenu();
					if(user.getStatus() == 'D') {  // 회원탈퇴시
						return;
					}
					break;
				case 9: 
					uc.signOut();
					System.out.println("로그아웃 되었습니다.");
					return;
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
	
	public void myPageMenu() {
		while(true) {
			System.out.println("1.회원탈퇴");
			System.out.println("9.홈으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			int selMenu = Integer.parseInt(sc.nextLine());
			switch(selMenu) {
				case 1:
					deleteUser();
					return;
				case 9: 
					System.out.println("홈으로 돌아가기");
					return;
				default:
					System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void getContentsList(String contentsType) {
		user.setContentsList(csc.getContentsList(contentsType));
		List<Contents> list = user.getContentsList();
		if(!list.isEmpty()) {
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t작성일\t\t제목\t\t\t감정");
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				System.out.println("NO\t작성일\t\t제목\t\t\t개봉일\t\t감독\t출연배우\t별점");
			} else { // 책
				System.out.println("NO\t작성일\t\t제목\t\t\t작가\t\t출판사\t\t가격\t별점");
			}
			
			for(int i=0; i<list.size(); i++) {
				Contents c = list.get(i);
				System.out.println((i+1) + "\t" + c);
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
		
		if(csc.addContents(c)) {
			System.out.println("컨텐츠가 생성 되었습니다.");
			user.setContentsList(csc.getContentsList(contentsType)); // 목록 재조회
		} else {
			System.out.println("컨텐츠 생성을 실패하였습니다.");
		}
	}
	
	public void getContents(String contentsType) {
		System.out.print("조회할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		if(rowNum <= user.getContentsList().size()) {
			contentsId = user.getContentsList().get(rowNum-1).getContentsId();
		}
		
		if(contentsId > 0) {
			Contents c = csc.getContents(contentsId, contentsType);
			
			if(contentsType.equals(DIARY_TYPE)) { // 일기
				System.out.println("NO\t날짜\t\t제목\t\t\t감정");
			} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
				System.out.println("NO\t날짜\t\t제목\t\t\t개봉일\t\t감독\t출연배우\t별점");
			} else { // 책
				System.out.println("NO\t날짜\t\t제목\t\t\t작가\t\t출판사\t\t가격\t별점");
			}
			System.out.println(rowNum + "\t" + c);
			System.out.println("내용:");
			System.out.println(c.getContent());
			
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
	
	public void updateContents(String contentsType) {
		boolean result = false;
		
		System.out.print("수정할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		if(rowNum <= user.getContentsList().size()) {
			contentsId = user.getContentsList().get(rowNum-1).getContentsId();
		}
		
		if(contentsId == 0 || csc.getContents(contentsId, contentsType) == null) {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
			return;
		}
		
		System.out.print("제목을 입력해주세요. : ");
		String title = sc.nextLine();
		
		System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
		String createDate = sc.nextLine();
		
		String content = "";
		
		Contents c = null;
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Diary(contentsId, contentsType, title, content, createDate, feelings);
			
		} else if(contentsType.equals(MOVIE_TYPE)) { // 영화
			System.out.print("개봉일을 입력해주세요. : ");
			String releaseDate = sc.nextLine();
			
			System.out.print("감독을 입력해주세요. : ");
			String director = sc.nextLine();
			
			System.out.print("출연배우들을 입력해주세요.(,로 구분) : ");
			String actors = sc.nextLine();
			
			System.out.print("별점을 입력해주세요.(1~5) : ");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("영화를 어디서 보셨나요? : ");
			String place = sc.nextLine();
			
			System.out.print("누구와 함께 보셨나요? : ");
			String with = sc.nextLine();
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Movie(contentsId, contentsType, title, content, createDate, releaseDate, director, actors, place, with, isLikeYn, starCount);
			
		} else { // 책
			System.out.print("작가를 입력해주세요. : ");
			String author = sc.nextLine();
			
			System.out.print("출판사를 입력해주세요. : ");
			String publisher = sc.nextLine();
			
			System.out.print("가격을 입력해주세요. : ");
			int price = Integer.parseInt(sc.nextLine());
			
			System.out.print("별점을 입력해주세요.(1~5) : ");
			int starCount = Integer.parseInt(sc.nextLine());
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			c = new Book(contentsId, contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);
		}
		
		result = csc.updateContents(c);
		
		if(result) {
			System.out.println("컨텐츠가 수정 되었습니다.");
			user.setContentsList(csc.getContentsList(contentsType));
		} else {
			System.out.println("컨텐츠 수정을 실패하였습니다.");
		}
	}
	
	public void deleteContents(String contentsType) {
		System.out.print("삭제할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		if(rowNum <= user.getContentsList().size()) {
			contentsId = user.getContentsList().get(rowNum-1).getContentsId();
		}
		
		if(contentsId > 0 && csc.deleteContents(contentsId, contentsType)) {
			System.out.println("컨텐츠가 삭제 되었습니다.");
			user.setContentsList(csc.getContentsList(contentsType));
		} else {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
		}
	}
	
	public boolean deleteUser() {
		System.out.println("정말 삭제하려면 '탈퇴'를, 뒤로가려면 아무거나 입력해주세요.");
		String userInput = sc.nextLine();
		
		if(userInput.equals("탈퇴")) {
			uc.deleteUser(user.getUserId());
			return true;
		} else {
			return false;
		}
	}
	
	// *********************** 응답화면 ***************************

	public void displaySuccess(String message) {
		System.out.println("[요청 성공] " + message);
	}
	
	public void displayFailed(String message) {
		System.out.println("[요청 실패] " + message);
	}
}
