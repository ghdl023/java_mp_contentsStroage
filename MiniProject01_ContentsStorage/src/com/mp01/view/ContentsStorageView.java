package com.mp01.view;

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
			System.out.println(" __        __   _                          ");
			System.out.println(" \\ \\      / /__| | ___ ___  _ __ ___   ___ ");
			System.out.println("  \\ \\ /\\ / / _ \\ |/ __/ _ \\| '_ ` _ \\ / _ \\");
			System.out.println("   \\ V  V /  __/ | (_| (_) | | | | | |  __/");
			System.out.println("    \\_/\\_/ \\___|_|\\___\\___/|_| |_| |_|\\___|");
			System.out.println();
			System.out.println("***********************************************");
			
			System.out.println("1.로그인");
			System.out.println("2.회원가입");
			System.out.println("3.프로그램 종료");

			System.out.print("메뉴 선택 : ");

			try {
				int selMenu = Integer.parseInt(sc.nextLine());

				switch(selMenu) {
					case 1: 
						if(signInMenu()) {
							homeMenu();
						}
						break;
					case 2:
						if(signUpMenu()) {
							homeMenu();
						}
						break;
					case 3: 
						System.out.println("프로그램을 종료합니다.");
						System.exit(0);
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public boolean signInMenu() {
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
				return false;
			}
	
			System.out.print("비밀번호: ");
			String userPassword = sc.nextLine();
			int result = uc.signIn(userId, userPassword);
			
			if(result > 0) {
				user.setUserId(result);
				user.setUserName(userId);
				user.setUserPassword(userPassword);
				return true;
			}
			
		}
	}
	
	public boolean signUpMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println("   ____  _             _   _       ");
			System.out.println("  / ___|(_) __ _ _ __ | | | |_ __  ");
			System.out.println("  \\___ \\| |/ _` | '_ \\| | | | '_ \\ ");
			System.out.println("   ___) | | (_| | | | | |_| | |_) |");
			System.out.println("  |____/|_|\\__, |_| |_|\\___/| .__/ ");
			System.out.println("           |___/            |_|    ");
			System.out.println("***********************************************");
			System.out.println(">>>> 메인화면으로 이동하려면 exit를 입력하세요.");
			System.out.print("아이디: " );
			String userId = sc.nextLine();
			
			if(userId.toLowerCase().equals("exit")) {
				return false;
			}
	
			System.out.print("비밀번호: ");
			String userPassword = sc.nextLine();
			
			int result = uc.signUp(userId, userPassword);
			if(result > 0) {
				user.setUserId(result);
				user.setUserName(userId);
				user.setUserPassword(userPassword);
				return true;
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
			
			try {
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
						if(user.isLogin()) {
							user.setUserId(0);
							user.setUserName("");
							user.setUserPassword("");
						}
						System.out.println("로그아웃 되었습니다.");
						return;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void diaryMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println(" ____  _                  ");
			System.out.println("|  _ \\(_) __ _ _ __ _   _ ");
			System.out.println("| | | | |/ _` | '__| | | |");
			System.out.println("| |_| | | (_| | |  | |_| |");
			System.out.println("|____/|_|\\__,_|_|   \\__, |");
			System.out.println("                     |___/ ");
			System.out.println();
			user.setContentsList(csc.getContentsList(user.getUserId(), DIARY_TYPE));
			System.out.println("***********************************************");
			
			System.out.println("1.일기 추가");
			System.out.println("2.일기 조회");
			System.out.println("3.일기 수정");
			System.out.println("4.일기 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			try {
				int selMenu = Integer.parseInt(sc.nextLine());
				switch(selMenu) {
					case 1:
						addContents(DIARY_TYPE);
						break;
					case 2:
						getContents(DIARY_TYPE);
						break;
					case 3:
						updateContents(DIARY_TYPE);
						break;
					case 4:
						deleteContents(DIARY_TYPE);
						break;
					case 9: 
						System.out.println("메인으로 돌아가기");
						return;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void movieMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println(" __  __            _      ");
			System.out.println("|  \\/  | _____   _(_) ___ ");
			System.out.println("| |\\/| |/ _ \\ \\ / / |/ _ \\");
			System.out.println("| |  | | (_) \\ V /| |  __/");
			System.out.println("|_|  |_|\\___/ \\_/ |_|\\___|");
			System.out.println();
			user.setContentsList(csc.getContentsList(user.getUserId(), MOVIE_TYPE));
			System.out.println("***********************************************");
			
			System.out.println("1.영화 추가");
			System.out.println("2.영화 조회");
			System.out.println("3.영화 수정");
			System.out.println("4.영화 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			try {
				int selMenu = Integer.parseInt(sc.nextLine());
				switch(selMenu) {
					case 1:
						addContents(MOVIE_TYPE);
						break;
					case 2:
						getContents(MOVIE_TYPE);
						break;
					case 3:
						updateContents(MOVIE_TYPE);
						break;
					case 4:
						deleteContents(MOVIE_TYPE);
						break;
					case 9: 
						System.out.println("메인으로 돌아가기");
						return;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public void bookMenu() {
		while(true) {
			System.out.println("***********************************************");
			System.out.println(" ____              _    ");
			System.out.println("| __ )  ___   ___ | | __");
			System.out.println("|  _ \\ / _ \\ / _ \\| |/ /");
			System.out.println("| |_) | (_) | (_) |   < ");
			System.out.println("|____/ \\___/ \\___/|_|\\_\\");
			System.out.println();
			user.setContentsList(csc.getContentsList(user.getUserId(), BOOK_TYPE));
			System.out.println("***********************************************");
			
//			System.out.println("1.목록 보기");
			System.out.println("1.책 추가");
			System.out.println("2.책 조회");
			System.out.println("3.책 수정");
			System.out.println("4.책 삭제");
			System.out.println("9.메인으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			try {
				int selMenu = Integer.parseInt(sc.nextLine());
				switch(selMenu) {
					case 1:
						addContents(BOOK_TYPE);
						break;
					case 2:
						getContents(BOOK_TYPE);
						break;
					case 3:
						updateContents(BOOK_TYPE);
						break;
					case 4:
						deleteContents(BOOK_TYPE);
						break;
					case 9: 
						System.out.println("메인으로 돌아가기");
						return;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
		
	}
	
	public boolean myPageMenu() {
		while(true) {
			System.out.println("1.회원탈퇴");
			System.out.println("9.홈으로 돌아가기");
			System.out.print("메뉴 선택 : ");
			
			try {
				int selMenu = Integer.parseInt(sc.nextLine());
				switch(selMenu) {
					case 1:
						if(deleteUser()) {
							return true;
						} 
						break;
					case 9: 
						System.out.println("홈으로 돌아가기");
						return false;
					default:
						System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
				}
			} catch(Exception e) {
				System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
			}
		}
	}
	
	public void getContentsList(String contentsType) {
		user.setContentsList(csc.getContentsList(user.getUserId(), contentsType));
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
		
		csc.addContents(user.getUserId(), c);
	}
	
	public void getContents(String contentsType) {
		System.out.print("조회할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		if(rowNum <= user.getContentsList().size()) {
			contentsId = user.getContentsList().get(rowNum-1).getContentsId();
		}
		csc.getContents(user.getUserId(), contentsId, contentsType);
	}
	
	public void updateContents(String contentsType) {
		System.out.print("수정할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		Contents target = null;
		if(rowNum <= user.getContentsList().size()) {
			target = user.getContentsList().get(rowNum-1);
			contentsId = target.getContentsId();
		}
		
		if(contentsId == 0 || target == null) {
			System.out.println("입력하신 컨텐츠 NO과 일치하는 컨텐츠가 없습니다.");
			return;
		}
		System.out.println("* 해당 항목을 건너뛰시려면 엔터를 누르세요.");
		System.out.print("제목을 입력해주세요. : ");
		String title = sc.nextLine();
		
		System.out.print("날짜를 입력해주세요.(yyyy년mm월dd일) : ");
		String createDate = sc.nextLine();
		
		String content = "";
		
		Contents c = null;
		
		if(title == null || title.equals("")) {
			title = target.getTitle();
		}
		
		if(createDate == null || createDate.equals("")) {
			createDate = target.getCreateDate();
		}
		
		if(contentsType.equals(DIARY_TYPE)) { // 일기
			System.out.print("오늘을 감정으로 표현한다면 어떤 감정일까요?(기쁨,행복,슬픔,우울 등) : ");
			String feelings = sc.nextLine();
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			if(feelings == null || feelings.equals("")) {
				feelings = ((Diary)target).getFeelings();
			}
			if(content == null || content.equals("")) {
				content = target.getContent();
			}
			
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
			String withs = sc.nextLine();
			
			System.out.print("다른사람에게 추천할 의향이 있으신가요?(y/n) : ");
			String isLikeYn = sc.nextLine().toLowerCase().substring(0,1);
			
			System.out.print("내용 : ");
			content = sc.nextLine();
			
			if(releaseDate == null || releaseDate.equals("")) {
				releaseDate = ((Movie)target).getReleaseDate();
			}
			if(director == null || director.equals("")) {
				director = ((Movie)target).getDirector();
			}
			if(actors == null || actors.equals("")) {
				actors = ((Movie)target).getActors();
			}
//			if(starCount == null || starCount.equals("")) {
//				starCount = ((Movie)target).getReleaseDate();
//			}
			if(place == null || place.equals("")) {
				place = ((Movie)target).getPlace();
			}
			if(withs == null || withs.equals("")) {
				withs = ((Movie)target).getWiths();
			}
			if(isLikeYn == null || isLikeYn.equals("")) {
				isLikeYn = ((Movie)target).getIsLikeYn();
			}
			if(content == null || content.equals("")) {
				content = target.getContent();
			}
			
			c = new Movie(contentsId, contentsType, title, content, createDate, releaseDate, director, actors, place, withs, isLikeYn, starCount);
			
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
			
			if(author == null || author.equals("")) {
				author = ((Book)target).getAuthor();
			}
			if(publisher == null || publisher.equals("")) {
				publisher = ((Book)target).getPublisher();
			}
//			if(price == null || price.equals("")) {
//				price = ((Book)target).getPublisher();
//			}
//			if(starCount == null || starCount.equals("")) {
//				starCount = ((Book)target).getAuthor();
//			}
			if(isLikeYn == null || isLikeYn.equals("")) {
				isLikeYn = ((Book)target).getIsLikeYn();
			}
			if(content == null || content.equals("")) {
				content = target.getContent();
			}
			
			c = new Book(contentsId, contentsType, title, content, createDate, author, publisher, price, isLikeYn, starCount);
		}
		
		csc.updateContents(user.getUserId(), c);
	}
	
	public void deleteContents(String contentsType) {
		System.out.print("삭제할 컨텐츠 NO을 입력하세요. : ");
		int rowNum = Integer.parseInt(sc.nextLine());
		int contentsId = 0;
		if(rowNum <= user.getContentsList().size()) {
			contentsId = user.getContentsList().get(rowNum-1).getContentsId();
		}
		csc.deleteContents(user.getUserId(), contentsId, contentsType);
	}
	
	public boolean deleteUser() {
		System.out.println("정말 삭제하려면 '탈퇴'를, 뒤로가려면 아무거나 입력해주세요.");
		String userInput = sc.nextLine();
		
		if(userInput.equals("탈퇴")) {
			boolean result = uc.deleteUser(user.getUserName());
			if(result) {
				user.setUserId(0);
				user.setUserName("");
				user.setUserPassword("");
				user.setStatus('D');
				return true;
			}
			return false;
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
	
	public void displayContents(Contents c) {
		System.out.println("=================================================");
		if(c instanceof Diary) { // 일기
			System.out.println("날짜\t\t제목\t\t\t감정");
		} else if(c instanceof Movie) { // 영화
			System.out.println("날짜\t\t제목\t\t\t개봉일\t\t감독\t\t출연배우\t\t별점");
		} else { // 책
			System.out.println("날짜\t\t제목\t\t\t작가\t\t출판사\t\t가격\t별점");
		}
		System.out.println(c);
		System.out.println("=================================================");
		System.out.println("내용:");
		System.out.println(c.getContent() + "\n");
		System.out.println("=================================================");
		System.out.print("뒤로 가시려면 아무키나 입력하세요. : ");
		sc.nextLine();
	}
	
	public void displayContentsList(List<Contents> list) {
		if(!list.isEmpty()) {
			Contents c = list.get(0);
			if(c instanceof Diary) { // 일기
				System.out.println("NO\t작성일\t\t제목\t\t\t감정");
			} else if(c instanceof Movie) { // 영화
				System.out.println("NO\t작성일\t\t제목\t\t\t개봉일\t\t감독\t\t출연배우\t\t별점");
			} else { // 책
				System.out.println("NO\t작성일\t\t제목\t\t\t작가\t\t출판사\t\t가격\t별점");
			}
			
			for(int i=0; i<list.size(); i++) {
				c = list.get(i);
				System.out.println((i+1) + "\t" + c);
			}
		}
	}
}
