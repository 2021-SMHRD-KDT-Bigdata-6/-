package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class EnglishSYSTEM {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String result = null;
		EnglishVO vo = null;

		ArrayList<EnglishVO> list = null;

		EnglishDAO dao = new EnglishDAO();

		System.out.println("영어 단어 맞추기 게임을 실행합니다 ^^");

		while (true) {
			System.out.print("[1].회원가입  [2].로그인  [3].종료  >>  ");
			int choice = sc.nextInt();

			// ------------ 회원 가입 ----------------
			if (choice == 1) {
				System.out.println("--- 회원가입 ---");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();
				System.out.print("NAME 입력 : ");
				String name = sc.next();

				vo = new EnglishVO(id, pw, name, null);

				int cnt = dao.join(vo);

				if (cnt > 0) {
					System.out.println("회원가입 성공!");
				} else {
					System.out.println("회원가입 실패.../");
				}

				// ----------------- 로 그 인 ----------------

			} else if (choice == 2) {
				System.out.println("--- 로그인 ---");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();

				vo = new EnglishVO(id, pw, null, null);
				EnglishVO info = dao.login(vo);

				if (info != null) {
					System.out.println("로그인 성공!");

					System.out.println(info.getName() + " 님 ^^ 환영합니다");

					
					
					
					
					
					
					
					while (true) {
						System.out.print("[1]게임시작 [2]랭킹확인 [3]오답노트 [4]종료 >> ");
						int select = sc.nextInt();

						if (select == 1) {
							System.out.println("-- 난이도를 선택 해주세요 --");
							System.out.print("[1].Easy [2].Nomal [3].Hard >> ");
							int count = sc.nextInt();

							
							
							
							
							
							
							
							
					if (count == 1) {
						System.out.println("Easy 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");

//						ArrayList<EnglishVO> list =dao.easyword();

						list = dao.easyword();

					for (int i = 2; i < 22; i++) {
						if (i % 2 == 0) { // 영단어
						System.out.print(list.get(i) + " >> ");
					}
//					if(i%2!=0) {
//						result=sc.next();
//						if(list.get(i)==result) {
//							System.out.println("정답");
//					}
//							
//							
//							
//					}						
						
					else if (i % 2 != 0) { // 한글
//						list.get(i);
//						result = sc.next();
//						if (list.get(i) == result) {
							System.out.print(list.get(i));							
							result = sc.next();
							
							
//						}	
								
					}

				}

						
						
						
						
						
						
						
						
						
						
					} else if (count == 2) {
						System.out.println("Nomal 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");

//				ArrayList<EnglishVO> list =dao.nomalword();
						list = dao.nomalword();
						
						for (int i = 0; i < 10; i++) {
							System.out.print(list.get(i) + " >> ");
							result = sc.next();

						}

						
						
						
						
						
						
					} else if (count == 3) {
						System.out.println("Hard 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");
						
//				ArrayList<EnglishVO> list =dao.hardword();
						list = dao.hardword();

						for (int i = 0; i < 10; i++) {
							System.out.print(list.get(i) + " >> ");
							result = sc.next();

						}

					} else {
						System.out.println("잘못눌렀습니다");
					}

					
					
				} else if (select == 2) {
					System.out.println("====랭킹확인====");

					list = dao.rank();

					

				for (int i = 0; i <2; i++) {
				System.out.print(list.get(i));
						
											
				}

//				System.out.println("내 현재 랭킹 : "+ );

							
							
							
							
							
						
					
							
							
							
							
							
				} else if (select == 3) {
					System.out.println("오답노트");
					
										
						
					
					
					
							
							
							
							
							
							
				} else if (select == 4) {
					System.out.println("종료");
					break;
				}
			}

			} else {
				System.out.println("로그인 실패.../");

			}

			}

			
			
			
			
			
			
			
			
			
			else if (choice == 3) {
				System.out.println("종료");
				break;
			}

		}

	}

}
