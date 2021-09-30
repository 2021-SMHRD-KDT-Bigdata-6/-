package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDAO {
	// DAO  --> DataBase Access Object
	// �����ͺ��̽� ������ �����ϴ� �츮�� ������ ��ü!
		
	// ���� ������  ���� null �� �Ƚᵵ �ȴ�..
	private MemberVO info;   // =null;
	private Connection conn=null;
	private PreparedStatement psmt= null;
	private ResultSet rs=null;
	
	
// -------- �ߺ� -- ����̹� �ε�   Ŀ�ؼ� ���� ��  �޼ҵ�� ���´� ---------	
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
	
	
	
	
	
	

	
	
	/////  -------------- ȸ �� �� �� ------------
	
	public int join(MemberVO vo) {
		int cnt=0;
		
		
		// JDBC
		// 0. ������Ʈ �ȿ� ����̹� ���� �ֱ�
		// 1. ����̹� �ε� (�����ε�)
		// -��� DBMS�� ����̹��� ������� ��� (����̹��� ��� �ۼ�)
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		// 2. Ŀ�ؼ� ����
		// ����̹��� ���� DB URL, DB ID, DB PW ���� Ŀ�ؼ��� �����Ѵ�
			String db_url ="jdbc:oracle:thin:@localhost:1521:xe";
			String db_id = "hr";
			String db_pw = "hr";
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
			
//			if(conn !=null) {
//				System.out.println("Ŀ�ؼ� ���� ����!");
//			}else {
//				System.out.println("Ŀ�ؼ� ���� ����../");
//			}
			
		// 3. SQL�� ����
		// - psmt ��ü�� sql ������ �ϼ��� �� �ְ� sql ������ ���� �� �� �ִ�.
			String sql="insert into big_member values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			psmt.setString(3,vo.getNick());
			psmt.setString(4,vo.getPhone());
			cnt = psmt.executeUpdate(); // �������� ���� ������ �޾ƿ´�   ����� insert into... -->1��
			                           // 
		
			
			
		} catch (ClassNotFoundException e) {  // Ŭ���� ���Ͽ� ���� ���� ��Ȳ
			e.printStackTrace();
		} catch (SQLException e) {  //sql  �� ���� ���� ��Ȳ
			e.printStackTrace();
		} 
		
		
				
		finally {
			// 4. ���� ����
			// - ��������� �������� �����Ѵ�.
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

	
	
	
	//  -------------- �� �� ��  -------------------
	
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


/// --------------- �� �� -----------------

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



	
	// ---------------------ȸ�� ���� ---------------

	public ArrayList<MemberVO> selectAll() {
		ArrayList<MemberVO> list =new ArrayList<MemberVO>();
		
		
		
		try {
			getConn();
			
			String sql="select * from big_member";
			psmt = conn.prepareStatement(sql);
			
			rs=psmt.executeQuery();
			// ResultSet
			// ����� ���̺�� ���� ���·� ������ �ִ� ��ü
			
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
	
	//------------------ȸ�� Ż�� --------------------------

		
	
	
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
