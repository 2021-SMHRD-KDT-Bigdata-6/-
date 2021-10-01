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
		System.out.println("���� �ܾ� ���߱� ������ �����մϴ� ^^");

		while (true) {
			System.out.print("[1].ȸ������  [2].�α��� [3].ȸ�� ��� ���� [4].ȸ��Ż�� [5].����  >>  ");
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
				ProfileVO vo = new ProfileVO(id, pw, name, point);         // �Է��� (id ,pw , name)  , point =0���� ����
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
				ProfileVO vo = new ProfileVO(id, pw, name, 0);             
				info = dao.login(vo);
				if (info != null) {
					System.out.println("�α��� ����!");
					System.out.println(info.getName() + " �� ^^ ȯ���մϴ�");
// -----------------------    �� �� �� ��   ----------------------------------------------
					while (true) {
						System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]�����Ʈ [4]���� >> ");
						int select = sc.nextInt();

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

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.easyword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();                     //  ���� �� 5���� ���� word 
//						System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("�����Դϴ�");
										point = 1;                                                // easy��� ������ ������ ����Ʈ 1�� �߰�

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);   // ����Ʈ �ѱ�� (�α����� name �� ����)
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {                // Ʋ���� �� 
										System.out.println("����");
										NoteVO vo2 = new NoteVO(word, id, answer);        // ������ 5���� ���� word . �α����� id .  �ܾ� ��
										int bnt = dao.wrong(vo2);                         // Ʋ������  word  id answer ��   �ѱ�� 

									}

								}

// -------------------------- Nomal  ------------------------------------------					
							} else if (count == 2) {
								System.out.println("Nomal ��� ���� �����մϴ�.");
								System.out.println("�� 10���� �ܾ �����帳�ϴ�");
								System.out.println("������ ��Ȯ�� �Է��ϼ���");

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.nomalword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();
//							System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("�����Դϴ�");
										point = 2;

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {
										System.out.println("����");
										NoteVO vo2 = new NoteVO(word, id, answer);
										int bnt = dao.wrong(vo2);
									}

								}

// -------------------------- Hard  ------------------------------------------				

							} else if (count == 3) {
								System.out.println("Hard ��� ���� �����մϴ�.");
								System.out.println("�� 10���� �ܾ �����帳�ϴ�");
								System.out.println("������ ��Ȯ�� �Է��ϼ���");

								ProfileVO vo4 = new ProfileVO(name);

								ArrayList<WordVO> list = dao.hardword();

								for (int i = 0; i < 10; i++) {
									System.out.print(list.get(i).getWord() + " >> ");
									String word = list.get(i).getWord();
//							System.out.print(list.get(i).getAnswer() + " >> ");
									result = sc.next();
									String answer = list.get(i).getAnswer();
									if (answer.equals(result)) {
										System.out.println("�����Դϴ�");
										point = 3;

										ProfileVO vo1 = new ProfileVO(null, null, info.getName(), point);
										int cnt = dao.RankPoint(vo1);

									} else if (!answer.equals(result)) {
										System.out.println("����");
										NoteVO vo2 = new NoteVO(word, id, answer);
										int bnt = dao.wrong(vo2);

									}
								}
							}

							else {
								System.out.println("�߸��������ϴ�");
							}
//		  ------------------------��ŷ -------------------------------
						} else if (select == 2) {
							System.out.println("====��ŷȮ��====");

							ArrayList<ProfileVO> list1 = dao.rank();                 // 
							int count = 1;

							for (int i = 0; i < list1.size(); i++) {

								System.out.print(count + "��\t" + list1.get(i).getName() + "\t");
								System.out.println(list1.get(i).getPoint() + " ��");
								count++;

							}
//		 ------------------------���� ��Ʈ --------------------------------
						} else if (select == 3) {
							System.out.println("====�����Ʈ====");

							ProfileVO vo4 = new ProfileVO(info.getId(), null);         // �α����� id �ҷ�����

							ArrayList<NoteVO> list = dao.note(vo4);                    // id�� �´� ������ ���� list��

							for (int i = 0; i < list.size(); i++) {
								System.out.print(list.get(i).getWord() + "\t");
								System.out.println(list.get(i).getAnswer());
							}
//		-------------------------���� ---------------------------------
						} else if (select == 4) {
							System.out.println("����");
							point = 0;
							break;
						}
					}
				} else {
					System.out.println("�α��� ����.../");

				}
//      --------------------ȸ����� ���� -----------------------------------------
			}else if(choice ==3) {
				System.out.println("ȸ�� ��� ����");
				
				ArrayList<ProfileVO> list =dao.selectAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));					
				}
//	     --------------------ȸ�� Ż�� -------------------------------------------
			}else if(choice ==4) {
				System.out.print("ȸ�� Ż��");
				System.out.println("ID �Է� : ");
				String id=sc.next();
				System.out.print("PW �Է� : ");
				String pw=sc.next();
				
				NoteVO vo=new NoteVO(id);           // id�� �ҷ��ͼ� note ���̺� ����    fk ������
				ProfileVO vo1=new ProfileVO(id, pw);  // id pw �ҷ��ͼ� profile ���̺� ����
				
				int dnt=dao.delete1(vo);										
				int ent=dao.delete2(vo1);
				
				if (ent > 0) {
					System.out.println("ȸ��Ż�� ����!");
				} else {
					System.out.println("ȸ��Ż ����.../");
				}
				}
//       ------------------���� ---------------------------------------
			else if (choice == 5) {
				System.out.println("����");
				break;
			}
		}
	}
}
