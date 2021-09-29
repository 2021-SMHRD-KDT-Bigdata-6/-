package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class EnglishSYSTEM {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		String result = null;
		

		

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

				EnglishVO vo = new EnglishVO(id, pw, name, null);

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

				EnglishVO vo = new EnglishVO(id, pw, null, null);
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
						
						

						ArrayList<EnglishVO> list = dao.easyword();

					for (int i = 2; i < 22; i++) {
						if (i % 2 == 0) { // ���ܾ�
						System.out.print(list.get(i) + " >> ");
					}
						
						
					else if (i % 2 != 0) { // �ѱ�
//						String res=list.get(i);
						result = sc.next();
//						if (list.get(i) == result) {
							System.out.print(list.get(i));							
							result = sc.next();
							
							//point++;
							
							
							
							
							
							
							
						}	
								
					}

								
					
					
					
					} else if (count == 2) {
						System.out.println("Nomal ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");


						ArrayList<EnglishVO> list = dao.nomalword();
						
						for (int i = 0; i < 20; i++) {
							if(i%2==0) {
							System.out.print(list.get(i) + " >> ");
							
							}else if(i%2==1) {
								System.out.println(list.get(i));
								result = sc.next();
							}

						}

						
						
						
						
						
						
					} else if (count == 3) {
						System.out.println("Hard ��� ���� �����մϴ�.");
						System.out.println("�� 10���� �ܾ �����帳�ϴ�");
						System.out.println("������ ��Ȯ�� �Է��ϼ���");
						

						ArrayList<EnglishVO> list = dao.hardword();

						for (int i = 0; i < 20; i++) {
							if(i%2==0) {
							System.out.print(list.get(i) + " >> ");
							
							}else if(i%2==1) {
								System.out.println(list.get(i));
								result = sc.next();
							}

						}

					} else {
						System.out.println("�߸��������ϴ�");
					}

					
					
				} else if (select == 2) {
					System.out.println("====��ŷȮ��====");

					ArrayList<EnglishVO> list = dao.rank();
					int count=1;

					for (int i = 0; i < 6; i++) {
						if(i%2==0) {
						
						System.out.print(count+"��\t"+list.get(i)+"\t");
						count++;
						}else if(i%2==1) {
							System.out.println(list.get(i)+"��\t");
						}

					}
					
					// �ذ��ؾߵɰ�
					//for�� �ȿ��� list.size() �� ������.
					//DAO �������� ����������� order by
					//
							
						
							
							
				} else if (select == 3) {
					System.out.println("====�����Ʈ====");

					ArrayList<EnglishVO> list = dao.note();
					
					for (int i = 0; i < 6; i++) {
						if(i%2==0) {
						
						System.out.print(list.get(i)+"\t");
						
						}else if(i%2==1) {
							System.out.println(list.get(i)+"��\t");
						}

					}
										
						
					
					
					
							
							
							
							
							
							
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
