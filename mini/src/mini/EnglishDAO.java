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
	profileVO vo=null;
	
	

	//--------------------------------------------------------
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //여기부분에서 오류가 났는데
			
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
	
	public int join(profileVO vo) {
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

	public profileVO login(profileVO vo) {
		
		getConn();
		
		profileVO info=null;
		
		
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
				
				
				info =new profileVO(id, pw,  name);
			
			}
					
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
						
		finally {
			close();
		}
		
		return info;
	}

	

	// --------------------- 랭킹 포인트 넘기기 -------------------------
	public int RankPoint(rankVO vo) {
		int cnt=0;
		
		
		try {
			getConn();
			
			String sql="update rank set point=? where name=?";
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1,vo.getPoint());
			cnt = psmt.executeUpdate(); 
			
			
			
		} 

		catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		finally {
			close();
		
		}
		
		
		return cnt;
	}

	// ------------------------------- 틀린단어 넘기기 --------------------
	public int wrong(wordVO vo) {
		int cnt=0;
		
		
		try {
			getConn();
			
			String sql="update wrong_word set wrong_word=? where answer=?";
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1,vo.getWord());
			psmt.setString(2,vo.getAnswer());
			
			cnt = psmt.executeUpdate(); 
			
			
			
		} 

		catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
		finally {
			close();
		
		}
		
		
		return cnt;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//------------- 쉬움 ----------------------------
	
	public ArrayList<wordVO> easyword() {
		
		ArrayList<wordVO> list =new ArrayList<wordVO>();
		getConn();
				
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=5 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				wordVO vo = new wordVO(word,answer);
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
	
	
	
	 //---------------------------보통 -------------------------------

	public ArrayList<wordVO> nomalword() {
		
		ArrayList<wordVO> list =new ArrayList<wordVO>();
		getConn();
		
		
		
		
		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=7 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				wordVO vo=new wordVO(word);
				wordVO vo1=new wordVO(answer);
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

	public ArrayList<wordVO> hardword() {
		
		ArrayList<wordVO> list =new ArrayList<wordVO>();
		getConn();
		
		
		
		
		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)>7 ";

			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);
				
				wordVO vo=new wordVO(word);
				wordVO vo1=new wordVO(answer);
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

	
	// ------------------랭킹 ----------------------------------
		
	public ArrayList<rankVO> rank() {
		
		ArrayList<rankVO> list=new ArrayList<rankVO>();	
		getConn();
				
		try {
			String sql="select * from rank order by point desc ";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String name=rs.getString(1);
				String point=rs.getString(2);
				
				rankVO vo=new rankVO(name, point);
				
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

	//-----------------오답노트 -------------------------
	
	public ArrayList<noteVO> note() {
		
		ArrayList<noteVO> list=new ArrayList<noteVO>();	
		getConn();
				
		try {
			String sql="select * from note order by wrong_word asc ";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				String worng_word=rs.getString(1);
				String answer=rs.getString(3);
				
				noteVO vo=new noteVO(worng_word, answer);				
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
