package English;

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
	//-------------------- ȸ�� ���� --------------------------------------	
	public int join(ProfileVO vo) {
		
		int cnt=0;
		
		getConn();
		
		try {
			String sql="insert into PROFILE values(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			psmt.setString(3,vo.getName());					
			psmt.setLong(4,vo.getPoint());
			cnt = psmt.executeUpdate();						
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		finally {			
			close();
		}		
		return cnt;
	}	
	
//   ----------------------  �� �� �� ----------------------

	public ProfileVO login(ProfileVO vo) {		
		getConn();		
		ProfileVO info=null;				
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
				info =new ProfileVO(id, pw,  name,0);			
			}					
		} catch (SQLException e) {			
			e.printStackTrace();
		}						
		finally {
			close();
		}		
		return info;
	}	

	// --------------------- ��ŷ ����Ʈ �ѱ�� -------------------------
	public int RankPoint(ProfileVO vo) {
		int cnt=0;	
		try {
			getConn();			
//			String sql="insert into rank values(?,?)";
			String sql="update profile set point=point+? where name=?";
			psmt = conn.prepareStatement(sql);			
//			psmt.setString(1,vo.getName());
			psmt.setLong(1,vo.getPoint());
			psmt.setString(2,vo.getName());
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

	// ------------------------------- Ʋ���ܾ� �ѱ�� --------------------
	public int wrong(NoteVO vo) {
		int bnt=0;				
		try {
			getConn();			
			String sql="insert into note values(?,?,?)";
			psmt = conn.prepareStatement(sql);			
			psmt.setString(1,vo.getWord());
			psmt.setString(2,vo.getId());
			psmt.setString(3,vo.getAnswer());
			bnt = psmt.executeUpdate(); 									
		} 
		catch (SQLException e) {			
			e.printStackTrace();
		}		
		finally {
			close();		
		}				
		return bnt;
	}
	//------------- ���� ----------------------------	
	public ArrayList<WordVO> easyword() {		
		ArrayList<WordVO> list =new ArrayList<WordVO>();
		getConn();				
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=5 ";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);				
				WordVO vo =new WordVO(word, 0, answer);								
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
	 //---------------------------���� -------------------------------
	public ArrayList<WordVO> nomalword() {		
		ArrayList<WordVO> list =new ArrayList<WordVO>();
		getConn();		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=7 ";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);				
				WordVO vo =new WordVO(word, 0, answer);								
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
	// -------------------- ����� --------------------------
	public ArrayList<WordVO> hardword() {		
		ArrayList<WordVO> list =new ArrayList<WordVO>();
		getConn();		
		try {
			String sql="select word,answer from(select word,answer from word order by dbms_random.value)where length(word)>7 ";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(2);				
				WordVO vo=new WordVO(word, 0, answer);
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
	// ------------------��ŷ ----------------------------------		
	public ArrayList<RankVO> rank() {		
		ArrayList<RankVO> list=new ArrayList<RankVO>();	
		getConn();				
		try {
			String sql="select name,point from profile order by point desc";
			psmt = conn.prepareStatement(sql);						
			rs = psmt.executeQuery();
			while(rs.next()) {
				String name=rs.getString(1);
				int point=rs.getInt(2);			
				RankVO vo=new RankVO(name, point);		
				list.add(vo);
					
			}			
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		finally {			
			close();
		}return list;
	}
	//-----------------�����Ʈ -------------------------	
	public ArrayList<NoteVO> note() {		
		ArrayList<NoteVO> list=new ArrayList<NoteVO>();	
		getConn();				
		try {
			String sql="select * from note  order by wrong_word asc ";
			
			psmt = conn.prepareStatement(sql);
//			psmt.setString(1,vo.getId());
			rs = psmt.executeQuery(sql);			
			while(rs.next()) {
				String word=rs.getString(1);
				String answer=rs.getString(3);				
				NoteVO vo=new NoteVO(word,answer);				
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
