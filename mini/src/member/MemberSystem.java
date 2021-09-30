package member;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;





public class MemberSystem {

	public static void main(String[] args) {
		// ��� �ý���(ȸ������, �α���, ȸ����������, ȸ�� ��Ϻ���)
		Scanner sc=new Scanner(System.in);
		
		MemberDAO dao =new MemberDAO();
		//���۷���             ������		    ��ü ����
		
		
		System.out.println("��� �ý��� ���α׷��� �����մϴ�");
		
		while(true) {
			System.out.print("1.ȸ������ 2.�α��� 3.ȸ���������� 4.ȸ����Ϻ��� 5. ȸ��Ż�� 6.���� >> ");
			int choice=sc.nextInt();
			
			if(choice == 1) {
				System.out.println("--ȸ������--");
				System.out.print("ID �Է� : ");
				String id=sc.next();
				System.out.print("PW �Է� : ");
				String pw=sc.next();
				System.out.print("NICK �Է� : ");
				String nick=sc.next();
				System.out.print("PHONE �Է� : ");
				String phone=sc.next();
				
				MemberVO vo = new MemberVO(id, pw, nick, phone);
				// ĸ��ȭ
				int cnt = dao.join(vo);
				// cnt == ȸ������ �������� ���θ� Ȯ���ϱ�����
				if(cnt >0 ) {
					System.out.println("ȸ������ ����!");
				}else {
					System.out.println("ȸ������ ����.../");
				}
				
				
			}else if(choice ==2) {
				
				System.out.println("-- �α��� --");
				System.out.print("ID �Է� : ");
				String id=sc.next();
				System.out.print("PW �Է� : ");
				String pw=sc.next();
				
				MemberVO vo=new MemberVO(id,pw);
				MemberVO info=dao.login(vo);
				
				if(info!=null) {
					System.out.println("�α��� ����!");

					System.out.println(info.getNick()+" �� ^&^ ȯ���մϴ�");									
					System.out.println(info);									
				}else {
					System.out.println("�α��� ����.../");
				}
				
								 
				
			}else if(choice ==3) {
				// id�� ��ġ�ϴ°� ã�Ƽ� pw,nick,phone�� �Է��� ������ ����
				System.out.println("--ȸ����������--");
				System.out.print("ID �Է� : ");
				String id=sc.next();
				System.out.print("PW �Է� : ");
				String pw=sc.next();
				System.out.print("NICK �Է� : ");
				String nick=sc.next();
				System.out.print("PHONE �Է� : ");
				String phone=sc.next();
				
				MemberVO vo = new MemberVO(id, pw, nick, phone);
				
				int cnt=dao.update(vo);
				
				if(cnt>0) {
					System.out.println("ȸ���������� ����!");
				}else {
					System.out.println("ȸ���������� ����../");
				}
				
				
				
				
				
				
				
				
			}else if(choice ==4) {
				System.out.println("--ȸ����Ϻ���--");
				
				ArrayList<MemberVO> list =dao.selectAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
					// System.out.println(list.get(t).getId());
				}
		
				
				
				
				
				
				
				
				
				
			}else if(choice ==5) {
				System.out.println("--ȸ��Ż��--");
				
				System.out.print("ID �Է� : ");
				String id=sc.next();
				System.out.print("PW �Է� : ");
				String pw=sc.next();
				
				MemberVO vo=new MemberVO(id,pw);				
				
				int cnt=dao.delete(vo);
				
				if(cnt>0) {
					System.out.println("ȸ��Ż�� ����!");
				}else {
					System.out.println("ȸ��Ż�� ����../");
				}
				
				
				
				
			}
			
			
			
			
			
			
			
			else if(choice ==6) {
				System.out.println("���α׷��� �����մϴ�../");
				sc.close();
				break;
			}
			
	
		
		
		
		
		
		}
		
		
		

	}

}
