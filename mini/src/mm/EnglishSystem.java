package mm;

import java.util.ArrayList;
import java.util.Scanner;



public class EnglishSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String result = null;
		EnglishDAO dao = new EnglishDAO();
		String name = null;
		ProfileVO info = null;
		int point = 0;

//---------------------------------------------------------------------------
		System.out.println("영어 단어 맞추기 게임을 실행합니다 ^^");

		while (true) {
			System.out.print("[1].회원가입  [2].로그인 [3].회원 목록 보기 [4].회원탈퇴 [5].종료  >>  ");
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
				ProfileVO vo = new ProfileVO(id, pw, name, point);         // 입력한 (id ,pw , name)  , point =0으로 저장
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
				ProfileVO vo = new ProfileVO(id, pw, name, 0);             
				info = dao.login(vo);
				if (info != null) {
					System.out.println("로그인 성공!");
					System.out.println(info.getName() + " 님 ^^ 환영합니다");
// -----------------------    로 그 인 후   ----------------------------------------------
					while (true) {
						System.out.print("[1]게임시작 [2]랭킹확인 [3]오답노트 [4]종료 >> ");
						int select = sc.nextInt();

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

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.easyword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();                     //  랜덤 한 5글자 이하 word 
//						System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("정답입니다");
										point = 1;                                                // easy모드 정답일 때마다 포인트 1점 추가

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);   // 포인트 넘기기 (로그인한 name 에 따른)
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {                // 틀렸을 때 
										System.out.println("오답");
										NoteVO vo2 = new NoteVO(word, id, answer);        // 랜덤한 5글자 이하 word . 로그인한 id .  단어 뜻
										int bnt = dao.wrong(vo2);                         // 틀렸을때  word  id answer 을   넘기기 

									}

								}

// -------------------------- Nomal  ------------------------------------------					
							} else if (count == 2) {
								System.out.println("Nomal 모드 게임 시작합니다.");
								System.out.println("총 10개의 단어를 보여드립니다");
								System.out.println("정답을 정확히 입력하세요");

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.nomalword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();
//							System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("정답입니다");
										point = 2;

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {
										System.out.println("오답");
										NoteVO vo2 = new NoteVO(word, id, answer);
										int bnt = dao.wrong(vo2);
									}

								}

// -------------------------- Hard  ------------------------------------------				

							} else if (count == 3) {
								System.out.println("Hard 모드 게임 시작합니다.");
								System.out.println("총 10개의 단어를 보여드립니다");
								System.out.println("정답을 정확히 입력하세요");

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.hardword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();
//							System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("정답입니다");
										point = 3;

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {
										System.out.println("오답");
										NoteVO vo2 = new NoteVO(word, id, answer);
										int bnt = dao.wrong(vo2);

									}
								}
							}

							else {
								System.out.println("잘못눌렀습니다");
							}
//		  ------------------------랭킹 -------------------------------
						} else if (select == 2) {
							System.out.println("====랭킹확인====");

							ArrayList<ProfileVO> list1 = dao.rank();                 // 
							int count = 1;

							for (int i = 0; i < list1.size(); i++) {

								System.out.print(count + "등\t" + list1.get(i).getName() + "\t");
								System.out.println(list1.get(i).getPoint() + " 점");
								count++;

							}
//		 ------------------------오답 노트 --------------------------------
						} else if (select == 3) {
							System.out.println("====오답노트====");

							ProfileVO vo4 = new ProfileVO(info.getId(), null);         // 로그인한 id 불러오기

							ArrayList<NoteVO> list = dao.note(vo4);                    // id에 맞는 데이터 값을 list에

							for (int i = 0; i < list.size(); i++) {
								System.out.print(list.get(i).getWord() + "\t");
								System.out.println(list.get(i).getAnswer());
							}
//		-------------------------종료 ---------------------------------
						} else if (select == 4) {
							System.out.println("종료");
							point = 0;
							break;
						}
					}
				} else {
					System.out.println("로그인 실패.../");

				}
//      --------------------회원목록 보기 -----------------------------------------
			}else if(choice ==3) {
				System.out.println("회원 목록 보기");
				
				ArrayList<ProfileVO> list =dao.selectAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));					
				}
//	     --------------------회원 탈퇴 -------------------------------------------
			}else if(choice ==4) {
				System.out.print("회원 탈퇴");
				System.out.println("ID 입력 : ");
				String id=sc.next();
				System.out.print("PW 입력 : ");
				String pw=sc.next();
				
				NoteVO vo=new NoteVO(id);           // id를 불러와서 note 테이블 삭제    fk 떄문에
				ProfileVO vo1=new ProfileVO(id, pw);  // id pw 불러와서 profile 테이블 삭제
				
				int dnt=dao.delete1(vo);										
				int ent=dao.delete2(vo1);
				
				if (ent > 0) {
					System.out.println("회원탈퇴 성공!");
				} else {
					System.out.println("회원탈 실패.../");
				}
				}
//       ------------------종료 ---------------------------------------
			else if (choice == 5) {
				System.out.println("종료");
				break;
			}
		}
	}
}
