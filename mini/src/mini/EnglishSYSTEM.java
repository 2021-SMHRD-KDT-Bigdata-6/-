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

		System.out.println("���� �ܾ� ���߱� ������ �����մϴ� ^^");

		while (true) {
			System.out.print("[1].ȸ������  [2].�α���  [3].����  >>  ");
			int choice = sc.nextInt();

			// ------------ ȸ�� ���� ----------------
			if (choice == 1) {
				System.out.println("--- ȸ������ ---");
				System.out.print("ID �Է� : ");
				String id = sc.next();
				System.out.print("PW �Է� : ");
				String pw = sc.next();
				System.out.print("NAME �Է� : ");
				String name = sc.next();

				vo = new EnglishVO(id, pw, name, null);

				int cnt = dao.join(vo);

				if (cnt > 0) {
					System.out.println("ȸ������ ����!");
				} else {
					System.out.println("ȸ������ ����.../");
				}

				// ----------------- �� �� �� ----------------

			} else if (choice == 2) {
				System.out.println("--- �α��� ---");
				System.out.print("ID �Է� : ");
				String id = sc.next();
				System.out.print("PW �Է� : ");
				String pw = sc.next();

				vo = new EnglishVO(id, pw, null, null);
				EnglishVO info = dao.login(vo);

				if (info != null) {
					System.out.println("�α��� ����!");

					System.out.println(info.getName() + " �� ^^ ȯ���մϴ�");

					
					
					
					
					
					
					
					while (true) {
						System.out.print("[1]���ӽ��� [2]��ŷȮ�� [3]�����Ʈ [4]���� >> ");
						int select = sc.nextInt();

						if (select == 1) {
							System.out.println("-- ���̵��� ���� ���ּ��� --");
							System.out.print("[1].Easy [2].Nomal [3].Hard >> ");
							int count = sc.nextInt();

							
							
							
							
							
							
							
							
					if (count == 1) {
						System.out.println("Easy ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");

//						ArrayList<EnglishVO> list =dao.easyword();

						list = dao.easyword();

					for (int i = 2; i < 22; i++) {
						if (i % 2 == 0) { // ���ܾ�
						System.out.print(list.get(i) + " >> ");
					}
//					if(i%2!=0) {
//						result=sc.next();
//						if(list.get(i)==result) {
//							System.out.println("����");
//					}
//							
//							
//							
//					}						
						
					else if (i % 2 != 0) { // �ѱ�
//						list.get(i);
//						result = sc.next();
//						if (list.get(i) == result) {
							System.out.print(list.get(i));							
							result = sc.next();
							
							
//						}	
								
					}

				}

						
						
						
						
						
						
						
						
						
						
					} else if (count == 2) {
						System.out.println("Nomal ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");

//				ArrayList<EnglishVO> list =dao.nomalword();
						list = dao.nomalword();
						
						for (int i = 0; i < 10; i++) {
							System.out.print(list.get(i) + " >> ");
							result = sc.next();

						}

						
						
						
						
						
						
					} else if (count == 3) {
						System.out.println("Hard ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");
						
//				ArrayList<EnglishVO> list =dao.hardword();
						list = dao.hardword();

						for (int i = 0; i < 10; i++) {
							System.out.print(list.get(i) + " >> ");
							result = sc.next();

						}

					} else {
						System.out.println("�߸��������ϴ�");
					}

					
					
				} else if (select == 2) {
					System.out.println("====��ŷȮ��====");

					list = dao.rank();

					

				for (int i = 0; i <2; i++) {
				System.out.print(list.get(i));
						
											
				}

//				System.out.println("�� ���� ��ŷ : "+ );

							
							
							
							
							
						
					
							
							
							
							
							
				} else if (select == 3) {
					System.out.println("�����Ʈ");
					
										
						
					
					
					
							
							
							
							
							
							
				} else if (select == 4) {
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
