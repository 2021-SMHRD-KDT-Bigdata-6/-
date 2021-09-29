package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;




public class EnglishDAO {
	
	private Random ran=new Random();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	EnglishVO vo=null;
	
	

	//--------------------------------------------------------
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
						
			String db_url ="jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";

			                                  			
			String db_id = "cgi_5_5";
			String db_pw = "smhrd5";

			
			
			conn = DriverManager.getConnection(db_url,db_id,db_pw);
						
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
	}
	//------------------------------------------------------------
	
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
	
	
	
	
	
	
	//-------------------- 회원 가입 --------------------------------------
	
	public int join(EnglishVO vo) {
		int cnt=0;
		
		getConn();
		
		
		try {
			String sql="insert into PROFILE values(?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			psmt.setString(3,vo.getName());								
			cnt = psmt.executeUpdate();
			
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		finally {
			
			close();
		}
		
		
		return cnt;
	}
	
	
//   ----------------------  로 그 인 ----------------------

	public EnglishVO login(EnglishVO vo) {
		
		getConn();
		
		EnglishVO info=null;
		
		
		try {
			
			String sql="select * from profile where id =? and pw =?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			rs=psmt.executeQuery();
			
			if(rs.next()) {
				String id=rs.getString(1);
				String pw=rs.getString(2);
				String name=rs.getString(3);
				
				
				info =new EnglishVO(id, pw,  name,null);
			
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
						
		finally {
			close();
		}
		
		return info;
	}

	
	
	//------------- 쉬움 ----------------------------
	
	public ArrayList<EnglishVO> easyword() {
		
		ArrayList<EnglishVO> list =new ArrayList<EnglishVO>();
		getConn();
		
		
		
		
		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=5 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				EnglishVO vo=new EnglishVO(word);
				EnglishVO vo1=new EnglishVO(answer);
				list.add(vo);
				list.add(vo1);
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		finally {
			
			close();
		}
		
		
		
		
		
		return list;
	}
	
	
	
	 //---------------------------보통 -------------------------------

	public ArrayList<EnglishVO> nomalword() {
		
		ArrayList<EnglishVO> list =new ArrayList<EnglishVO>();
		getConn();
		
		
		
		
		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=7 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				EnglishVO vo=new EnglishVO(word);
				EnglishVO vo1=new EnglishVO(answer);
				list.add(vo);
				list.add(vo1);
				
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		finally {
			
			close();
		}
		
		
		
		
		
		return list;
		
		
		
	
	}
	
	
	// -------------------- 어려움 --------------------------

	public ArrayList<EnglishVO> hardword() {
		
		ArrayList<EnglishVO> list =new ArrayList<EnglishVO>();
		getConn();
		
		
		
		
		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)>7 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				EnglishVO vo=new EnglishVO(word);
//				EnglishVO vo1=new EnglishVO(answer);
				list.add(vo);
//				list.add(vo1);
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		finally {
			
			close();
		}
		
		
		
		
		
		return list;
		
		
		
		
		
		
		
		
		
		
		
	}

	
	// ------------------랭킹 ----------------------------------
	
	
	
	
	public ArrayList<EnglishVO> rank() {
		
		
		ArrayList<EnglishVO> list=new ArrayList<EnglishVO>();	
		getConn();
		
		
		
		
		
		try {
			String sql="select * from rank ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString(1);
				String point=rs.getString(2);
				
				EnglishVO vo=new EnglishVO(name, point);
				
				list.add(vo);
				
				
			}
			
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		finally {
			
			close();
		}
		
		
		
		
		
		return list;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
