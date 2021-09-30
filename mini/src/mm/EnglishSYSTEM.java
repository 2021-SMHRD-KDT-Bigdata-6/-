package mm;

import java.util.ArrayList;
import java.util.Scanner;



public class EnglishSYSTEM {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String result = null;
		EnglishDAO dao = new EnglishDAO();
		String name=null;
//---------------------------------------------------------------------------
		System.out.println("영어 단어 맞추기 게임을 실행합니다 ^^");

		while (true) {
			System.out.print("[1].회원가입  [2].로그인  [3].종료  >>  ");
			int choice = sc.nextInt();

// ------------------------- 회원 가입 --------------------------
			if (choice == 1) {
				System.out.println("--- 회원가입 ---");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();
				System.out.print("NAME 입력 : ");
				name = sc.next();
				ProfileVO vo = new ProfileVO(id, pw, name);
				int cnt = dao.join(vo);
				if (cnt > 0) {
					System.out.println("회원가입 성공!");
				} else {
					System.out.println("회원가입 실패.../");
				}
// ------------------------------ 로 그 인 ---------------------------
			} else if (choice == 2) {
				System.out.println("--- 로그인 ---");
				System.out.print("ID 입력 : ");
				String id = sc.next();
				System.out.print("PW 입력 : ");
				String pw = sc.next();
				ProfileVO vo = new ProfileVO(id, pw,name );
				ProfileVO info = dao.login(vo);
				if (info != null) {
					System.out.println("로그인 성공!");
					System.out.println(info.getName() + " 님 ^^ 환영합니다");	
// -----------------------    로 그 인 후   ----------------------------------------------
				while (true) {
					System.out.print("[1]게임시작 [2]랭킹확인 [3]오답노트 [4]종료 >> ");
					int select = sc.nextInt();						
					int po=0;
// --------------------------- 게임 시작 -----------------------------------------
					if (select == 1) {
						System.out.println("-- 난이도를 선택 해주세요 --");
						System.out.print("[1].Easy [2].Nomal [3].Hard >> ");
						int count = sc.nextInt();
// -------------------------- Easy  ------------------------------------------								
					if (count == 1) {
						System.out.println("Easy 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");							

						ArrayList<WordVO> list = dao.easyword();

					for (int i = 0; i < 10; i++) {						
						System.out.print(list.get(i).getWord() + " >> ");
						String word=list.get(i).getWord();
						System.out.print(list.get(i).getAnswer() + " >> ");
						result=sc.next();
						String answer=list.get(i).getAnswer();
						System.out.println(answer);
						if(answer.equals(result)) {
							System.out.println("정답입니다");
							count++;
						}else if(!answer.equals(result)) {
							System.out.println("오답");
						NoteVO vo2=new NoteVO(word, id, answer);
							int bnt=dao.wrong(vo2);
							
						}
						
						
						
						
					
						
						
					}
					
					RankVO vo1=new RankVO(name, po);
					int cnt=dao.RankPoint(vo1);
					
// -------------------------- Nomal  ------------------------------------------					
					} else if (count == 2) {
						System.out.println("Nomal 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");

						ArrayList<WordVO> list = dao.nomalword();
						
						for (int i = 0; i < list.size(); i++) {
							if(i%2==0) {
							System.out.print(list.get(i) + " >> ");
							
							}else if(i%2==1) {
								System.out.println(list.get(i));
								result = sc.next();
							}

						}
						
// -------------------------- Hard  ------------------------------------------						
						
					} else if (count == 3) {
						System.out.println("Hard 모드 게임 시작합니다.");
						System.out.println("총 10개의 단어를 보여드립니다");
						System.out.println("정답을 정확히 입력하세요");
						

						ArrayList<WordVO> list = dao.hardword();
						
						for (int i = 0; i < 10; i++) {						
							System.out.print(list.get(i).getWord() + " >> ");
							System.out.print(list.get(i).getAnswer() + " >> ");
							result=sc.next();
							String re=list.get(i).getAnswer();
							if(re==result) {
								System.out.println("정답입니다");
							}
							
						}		
																		
					} else {
						System.out.println("잘못눌렀습니다");
					}
//		  ------------------------랭킹 -------------------------------
				} else if (select == 2) {
					System.out.println("====랭킹확인====");

					ArrayList<RankVO> list1 = dao.rank();
					int count=1;

					for (int i = 0; i < list1.size(); i++) {
						
						System.out.print(count+"등\t"+list1.get(i).getName()+"\t");
						System.out.println(list1.get(i).getPoint()+" 점");
						count++;
						
					}
//		 ------------------------오답 노트 --------------------------------
				}else if (select == 3) {
					System.out.println("====오답노트====");

					ArrayList<NoteVO> list = dao.note();
					
					for (int i = 0; i < list.size(); i++) {
						
						System.out.print(list.get(i).getWord()+"\t");
						System.out.println(list.get(i).getAnswer());
						
					}
				}else if (select == 4) {
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
