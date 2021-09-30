package member;

import java.lang.reflect.Member;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;





public class MemberSystem {

	public static void main(String[] args) {
		// 멤버 시스템(회원가입, 로그인, 회원정보수정, 회원 목록보기)
		Scanner sc=new Scanner(System.in);
		
		MemberDAO dao =new MemberDAO();
		//래퍼런스             생성자		    객체 생성
		
		
		System.out.println("멤버 시스템 프로그램을 시작합니다");
		
		while(true) {
			System.out.print("1.회원가입 2.로그인 3.회원정보수정 4.회원목록보기 5. 회원탈퇴 6.종료 >> ");
			int choice=sc.nextInt();
			
			if(choice == 1) {
				System.out.println("--회원가입--");
				System.out.print("ID 입력 : ");
				String id=sc.next();
				System.out.print("PW 입력 : ");
				String pw=sc.next();
				System.out.print("NICK 입력 : ");
				String nick=sc.next();
				System.out.print("PHONE 입력 : ");
				String phone=sc.next();
				
				MemberVO vo = new MemberVO(id, pw, nick, phone);
				// 캡슐화
				int cnt = dao.join(vo);
				// cnt == 회원가입 성공실패 여부를 확인하기위해
				if(cnt >0 ) {
					System.out.println("회원가입 성공!");
				}else {
					System.out.println("회원가입 실패.../");
				}
				
				
			}else if(choice ==2) {
				
				System.out.println("-- 로그인 --");
				System.out.print("ID 입력 : ");
				String id=sc.next();
				System.out.print("PW 입력 : ");
				String pw=sc.next();
				
				MemberVO vo=new MemberVO(id,pw);
				MemberVO info=dao.login(vo);
				
				if(info!=null) {
					System.out.println("로그인 성공!");

					System.out.println(info.getNick()+" 님 ^&^ 환영합니다");									
					System.out.println(info);									
				}else {
					System.out.println("로그인 실패.../");
				}
				
								 
				
			}else if(choice ==3) {
				// id와 일치하는걸 찾아서 pw,nick,phone을 입력한 것으로 수정
				System.out.println("--회원정보수정--");
				System.out.print("ID 입력 : ");
				String id=sc.next();
				System.out.print("PW 입력 : ");
				String pw=sc.next();
				System.out.print("NICK 입력 : ");
				String nick=sc.next();
				System.out.print("PHONE 입력 : ");
				String phone=sc.next();
				
				MemberVO vo = new MemberVO(id, pw, nick, phone);
				
				int cnt=dao.update(vo);
				
				if(cnt>0) {
					System.out.println("회원정보수정 성공!");
				}else {
					System.out.println("회원정보수정 실패../");
				}
				
				
				
				
				
				
				
				
			}else if(choice ==4) {
				System.out.println("--회원목록보기--");
				
				ArrayList<MemberVO> list =dao.selectAll();
				
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i));
					// System.out.println(list.get(t).getId());
				}
		
				
				
				
				
				
				
				
				
				
			}else if(choice ==5) {
				System.out.println("--회원탈퇴--");
				
				System.out.print("ID 입력 : ");
				String id=sc.next();
				System.out.print("PW 입력 : ");
				String pw=sc.next();
				
				MemberVO vo=new MemberVO(id,pw);				
				
				int cnt=dao.delete(vo);
				
				if(cnt>0) {
					System.out.println("회원탈퇴 성공!");
				}else {
					System.out.println("회원탈퇴 실패../");
				}
				
				
				
				
			}
			
			
			
			
			
			
			
			else if(choice ==6) {
				System.out.println("프로그램을 종료합니다../");
				sc.close();
				break;
			}
			
	
		
		
		
		
		
		}
		
		
		

	}

}
