package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	// DAO  --> DataBase Access Object
	// 데이터베이스 접근을 관리하는 우리가 설계한 객체!
		
	// 전역 변수에  굳이 null 을 안써도 된다..
	private MemberVO info;   // =null;
	private Connection conn=null;
	private PreparedStatement psmt= null;
	private ResultSet rs=null;
	
	
// -------- 중복 -- 드라이버 로딩   커넥션 연결 을  메소드로 묶는다 ---------	
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			String db_url ="jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
						
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	
	private void close() {
		try {
			if(rs !=null) {
			rs.close();
			}
			if(psmt != null) {
				psmt.close();
			}
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		
	}
	
	
	
	
	
	

	
	
	/////  -------------- 회 원 가 입 ------------
	
	public int join(MemberVO vo) {
		int cnt=0;
		
		
		// JDBC
		// 0. 프로젝트 안에 드라이버 파일 넣기
		// 1. 드라이버 로딩 (동적로딩)
		// -어떠한 DBMS의 드라이버를 사용할지 명시 (드라이버의 경로 작성)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. 커넥션 연결
		// 드라이버를 통해 DB URL, DB ID, DB PW 보내 커넥션을 연결한다
			String db_url ="jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
//			if(conn !=null) {
//				System.out.println("커넥션 연결 성공!");
//			}else {
//				System.out.println("커넥션 연결 실패../");
//			}
			
		// 3. SQL문 실행
		// - psmt 객체가 sql 문장을 완성할 수 있고 sql 문장을 실행 할 수 있다.
			String sql="insert into big_member values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			psmt.setString(3,vo.getNick());
			psmt.setString(4,vo.getPhone());
			cnt = psmt.executeUpdate(); // 실행중인 문자 개수를 받아온다   현재는 insert into... -->1개
			                           // 
		
			
			
		} catch (ClassNotFoundException e) {  // 클래스 파일에 따른 예외 상황
			e.printStackTrace();
		} catch (SQLException e) {  //sql  에 따른 예외 상황
			e.printStackTrace();
		} 
		
		
				
		finally {
			// 4. 연결 종료
			// - 연결종료는 역순으로 수행한다.
			try {
				if(psmt != null) {
					psmt.close();
				}
				if(conn !=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
		}
		
		return cnt;
		
		
	}

	
	
	
	//  -------------- 로 그 인  -------------------
	
	public MemberVO login(MemberVO vo) {
		
		getConn();	
				
		
		try {
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			String db_url ="jdbc:oracle:thin:@localhost:1521:xe";
//			String db_id = "hr";
//			String db_pw = "hr";
//			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
			
			String sql="select * from big_member where id =? and pw =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			rs=psmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString(1);
				String pw=rs.getString(2);
				String nick=rs.getString(3);
				String phone=rs.getString(4);
//				String id=rs.getString("id");  
//				String pw=rs.getString("pw");
//				String nick=rs.getString("nick");
//				String phone=rs.getString("phone");
				
				info = new MemberVO(id, pw, nick, phone);
			}
		} 
		
		
//		catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			
			close();
			
//			try {
//				if(rs !=null) {
//				rs.close();
//				}
//				if(psmt != null) {
//					psmt.close();
//				}
//				if(conn !=null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
						
		}
				
		
		return info;
	}


/// --------------- 수 정 -----------------

	public int update(MemberVO vo) {
		int cnt=0;
		
		
		try {
			getConn();
			
			String sql="update big_member set pw=?,nick=?,phone=?  where id=?";
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1,vo.getPw());
			psmt.setString(2,vo.getNick());
			psmt.setString(3,vo.getPhone());
			psmt.setString(4,vo.getId());
			cnt = psmt.executeUpdate(); 
			
			
			
			
		} 
//		catch (ClassNotFoundException e) {			
//			e.printStackTrace();
//		} 
		catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		finally {
			close();
		
		}
		
		
		return cnt;
	}



	
	// ---------------------회원 정보 ---------------

	public ArrayList<MemberVO> selectAll() {
		ArrayList<MemberVO> list =new ArrayList<MemberVO>();
		
		
		
		try {
			getConn();
			
			String sql="select * from big_member";
			psmt = conn.prepareStatement(sql);
			
			rs=psmt.executeQuery();
			// ResultSet
			// 결과를 테이블과 같은 형태로 가지고 있는 객체
			
			while(rs.next()) {
				String id=rs.getString(1);
				String pw=rs.getString(2);
				String nick=rs.getString(3);
				String phone=rs.getString(4);
				
				MemberVO vo=new MemberVO(id, pw, nick, phone);
				list.add(vo);
			
				
			}
			
			
			
			
		} 
		
//		catch (ClassNotFoundException e) {			
//			e.printStackTrace();
//		}
     	catch (SQLException e) {		
			e.printStackTrace();
		}
		finally {
			close();
		
		}
						
		
		return list;
	}
	
	//------------------회원 탈퇴 --------------------------

		
	
	
	public int delete(MemberVO vo) {
		int cnt=0;
		getConn();	
				
		
		try {		
						
			String sql="delete big_member where id =? and pw =? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			cnt=psmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		finally {			
			close();
				
		}
		
		return cnt;
		
	}
	
	
	

}
