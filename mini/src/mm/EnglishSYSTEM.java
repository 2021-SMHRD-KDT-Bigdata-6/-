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
		System.out.println("���� �ܾ� ���߱� ������ �����մϴ� ^^");

		while (true) {
			System.out.print("[1].ȸ������  [2].�α���  [3].����  >>  ");
			int choice = sc.nextInt();

// ------------------------- ȸ�� ���� --------------------------
			if (choice == 1) {
				System.out.println("--- ȸ������ ---");
				System.out.print("ID �Է� : ");
				String id = sc.next();
				System.out.print("PW �Է� : ");
				String pw = sc.next();
				System.out.print("NAME �Է� : ");
				name = sc.next();
				ProfileVO vo = new ProfileVO(id, pw, name);
				int cnt = dao.join(vo);
				if (cnt > 0) {
					System.out.println("ȸ������ ����!");
				} else {
					System.out.println("ȸ������ ����.../");
				}
// ------------------------------ �� �� �� ---------------------------
			} else if (choice == 2) {
				System.out.println("--- �α��� ---");
				System.out.print("ID �Է� : ");
				String id = sc.next();
				System.out.print("PW �Է� : ");
				String pw = sc.next();
				ProfileVO vo = new ProfileVO(id, pw,name );
				ProfileVO info = dao.login(vo);
				if (info != null) {
					System.out.println("�α��� ����!");
					System.out.println(info.getName() + " �� ^^ ȯ���մϴ�");	
// -----------------------    �� �� �� ��   ----------------------------------------------
				while (true) {
					System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]�����Ʈ [4]���� >> ");
					int select = sc.nextInt();						
					int po=0;
// --------------------------- ���� ���� -----------------------------------------
					if (select == 1) {
						System.out.println("-- ���̵��� ���� ���ּ��� --");
						System.out.print("[1].Easy [2].Nomal [3].Hard >> ");
						int count = sc.nextInt();
// -------------------------- Easy  ------------------------------------------								
					if (count == 1) {
						System.out.println("Easy ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");							

						ArrayList<WordVO> list = dao.easyword();

					for (int i = 0; i < 10; i++) {						
						System.out.print(list.get(i).getWord() + " >> ");
						String word=list.get(i).getWord();
						System.out.print(list.get(i).getAnswer() + " >> ");
						result=sc.next();
						String answer=list.get(i).getAnswer();
						System.out.println(answer);
						if(answer.equals(result)) {
							System.out.println("�����Դϴ�");
							count++;
						}else if(!answer.equals(result)) {
							System.out.println("����");
						NoteVO vo2=new NoteVO(word, id, answer);
							int bnt=dao.wrong(vo2);
							
						}
						
						
						
						
					
						
						
					}
					
					RankVO vo1=new RankVO(name, po);
					int cnt=dao.RankPoint(vo1);
					
// -------------------------- Nomal  ------------------------------------------					
					} else if (count == 2) {
						System.out.println("Nomal ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");

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
						System.out.println("Hard ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");
						

						ArrayList<WordVO> list = dao.hardword();
						
						for (int i = 0; i < 10; i++) {						
							System.out.print(list.get(i).getWord() + " >> ");
							System.out.print(list.get(i).getAnswer() + " >> ");
							result=sc.next();
							String re=list.get(i).getAnswer();
							if(re==result) {
								System.out.println("�����Դϴ�");
							}
							
						}		
																		
					} else {
						System.out.println("�߸��������ϴ�");
					}
//		  ------------------------��ŷ -------------------------------
				} else if (select == 2) {
					System.out.println("====��ŷȮ��====");

					ArrayList<RankVO> list1 = dao.rank();
					int count=1;

					for (int i = 0; i < list1.size(); i++) {
						
						System.out.print(count+"��\t"+list1.get(i).getName()+"\t");
						System.out.println(list1.get(i).getPoint()+" ��");
						count++;
						
					}
//		 ------------------------���� ��Ʈ --------------------------------
				}else if (select == 3) {
					System.out.println("====�����Ʈ====");

					ArrayList<NoteVO> list = dao.note();
					
					for (int i = 0; i < list.size(); i++) {
						
						System.out.print(list.get(i).getWord()+"\t");
						System.out.println(list.get(i).getAnswer());
						
					}
				}else if (select == 4) {
					System.out.println("����");
					break;
				}
			}

			} else {
				System.out.println("�α��� ����.../");

			}

			}

			else if (choice == 3) {
				System.out.println("����");
				break;
			}
		}
	}
}
