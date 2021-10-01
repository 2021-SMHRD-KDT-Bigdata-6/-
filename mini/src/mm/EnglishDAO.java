package mm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;                                        // Ŀ�ؼǿ��� �����ϸ鼭 SQL ���� DB�� ���۵Ǿ�����. Statement Ŭ������ ����ϰ� �ִ�.
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.Random;




public class EnglishDAO {
	private Random ran = new Random();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// --------------------------------------------------------         // DBMS - ������ ���̽� ���� �ý���
	private void getConn() {
		try {                                                         //  0.������Ʈ �ȿ� ����̹� ���� �ֱ�
			Class.forName("oracle.jdbc.driver.OracleDriver");          // 1. ����̹� �ε� (���� �ε�)
			                                                           // ��� DBMS�� ����̹��� ������� ���(����̹� ����ۼ�) 
			                                                           // ���� ó�� ����� �Ѵ�.
			String db_url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";  //2. Ŀ�ؼ� ���� (���)
			String db_id = "cgi_5_5";                                            // ����̹��� ���� DB URL, DB ID, DB PW ���� Ŀ�ؼ��� �����Ѵ�.
			String db_pw = "smhrd5";                                             // ����ó�� �ؿ��� �Ѵ�.
			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------
	private void close() {                                              // 4. ���� ����
		try {                                                          // ���� ����� �������� �����Ѵ�.
			if (rs != null) {
				rs.close();
			}
			if (psmt != null) {
				psmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// -------------------- ȸ�� ���� --------------------------------------
	public int join(ProfileVO vo) {

		int cnt = 0;

		getConn();

		try {                                                       //   sql �� (id , pw , name , point) �� �ֱ�
			String sql = "insert into PROFILE values(?,?,?,?)";     
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());                         //  ? ����ǥ ù��° ĭ�� ��..��
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getName());
			psmt.setLong(4, vo.getPoint());
			cnt = psmt.executeUpdate();                           //  executeUpdate - �����͸� �߰� ,���� ,���� �ϴ� sql�� ���� 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

//   ----------------------  �� �� �� ----------------------

	public ProfileVO login(ProfileVO vo) {
		getConn();
		ProfileVO info = null;
		try {
			String sql = "select * from profile where id =? and pw =?";     // sql �� (profile ���̺���  id�� pw ��ġ ã��)
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			rs = psmt.executeQuery();                                 // executeQuery - db���� �����͸� ������   select ���� ����?
			if (rs.next()) {
				String id = rs.getString(1);                          //  profile ���̺�  ù���� �� ������ �� 
				String pw = rs.getString(2);
				String name = rs.getString(3);
				info = new ProfileVO(id, pw, name, 0);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return info;
	}

	// --------------------- ��ŷ ����Ʈ �ѱ�� -------------------------
	public int RankPoint(ProfileVO vo) {
		int cnt = 0;
		try {
			getConn();
//			String sql="insert into rank values(?,?)";
			String sql = "update profile set point=point+? where name=?";     // profile ���̺�  name�� �°� point ����
			psmt = conn.prepareStatement(sql);
//			psmt.setString(1,vo.getName());
			psmt.setLong(1, vo.getPoint());
			psmt.setString(2, vo.getName());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

	// ------------------------------- Ʋ���ܾ� �ѱ�� --------------------
	public int wrong(NoteVO vo) {
		int bnt = 0;
		try {
			getConn();
			String sql = "insert into note values(?,?,?)";                   //  note ���̺�  word , id, answer  �ֱ�
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getWord());
			psmt.setString(2, vo.getId());
			psmt.setString(3, vo.getAnswer());
			bnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bnt;
	}

	// ------------- ���� ----------------------------
	public ArrayList<WordVO> easyword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();                     // �迭 ����
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=5 ";
			psmt = conn.prepareStatement(sql);                             //   ���̰� 5������ word   �������� �ҷ����� 
			rs = psmt.executeQuery();
			while (rs.next()) {
				String word = rs.getString(1);
				String answer = rs.getString(2);
				WordVO vo = new WordVO(word, 0, answer);                      // word ,  �� word�� �ο��� ����, answer
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ---------------------------���� -------------------------------
	public ArrayList<WordVO> nomalword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=7 ";
			psmt = conn.prepareStatement(sql);                              //   ���̰� 7������ word   �������� �ҷ����� 
			rs = psmt.executeQuery();
			while (rs.next()) {
				String word = rs.getString(1);
				String answer = rs.getString(2);
				WordVO vo = new WordVO(word, 0, answer);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// -------------------- ����� --------------------------
	public ArrayList<WordVO> hardword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)>7 ";
			psmt = conn.prepareStatement(sql);                                //   ���̰� 7�ʰ��� word   �������� �ҷ����� 
			rs = psmt.executeQuery();
			while (rs.next()) {
				String word = rs.getString(1);
				String answer = rs.getString(2);
				WordVO vo = new WordVO(word, 0, answer);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ------------------��ŷ ----------------------------------
	public ArrayList<ProfileVO> rank() {
		ArrayList<ProfileVO> list = new ArrayList<ProfileVO>();
		getConn();
		try {
			String sql = "select name,point from profile order by point desc";    // profile ���̺��� name,point  desc(��������) ���� �ҷ�����
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				int point = rs.getInt(2);
				ProfileVO vo = new ProfileVO(null, null, name, point);           //  id, pw  , name, point
				list.add(vo);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// -----------------�����Ʈ -------------------------
	public ArrayList<NoteVO> note(ProfileVO vo) {
		ArrayList<NoteVO> list = new ArrayList<NoteVO>();
		getConn();
		try {
			String sql = "select * from note where id=? order by wrong_word asc ";      //note ���̺��� id ���´�  Ʋ���ܾ����  asc(��������) ���� �ҷ�����
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();

			while (rs.next()) {
				String word = rs.getString(1);             // note ���̺� 1��
				String answer = rs.getString(3);           // note ���̺� 3��
				NoteVO vo2 = new NoteVO(word, answer); 
				list.add(vo2);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
	//---------------note ����------------------
	public int delete1(NoteVO vo) {
		int dnt=0;
		getConn();	
				
		
		try {						
			String sql="delete note where id =? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());			
			dnt=psmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		finally {			
			close();				
		}
		
		return dnt;
		
	}
	//------------------profile ����----------------------
	public int delete2(ProfileVO vo) {
		int ent=0;
		getConn();	
						
		try {						
			String sql="delete profile where id =? and pw =? ";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1,vo.getId());
			psmt.setString(2,vo.getPw());
			ent=psmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}		
		finally {			
			close();				
		}		
		return ent;		
	}	
	// ---------------------ȸ�� ���� ---------------

		public ArrayList<ProfileVO> selectAll() {
			ArrayList<ProfileVO> list =new ArrayList<ProfileVO>();
									
			try {
				getConn();
				
				String sql="select * from profile";
				psmt = conn.prepareStatement(sql);
				
				rs=psmt.executeQuery();					
				while(rs.next()) {
					String id=rs.getString(1);					
					String name=rs.getString(3);
					int point=rs.getInt(4);					
					ProfileVO vo=new ProfileVO(id, name, point);
					list.add(vo);				
				}								
			} 	
	     	catch (SQLException e) {		
				e.printStackTrace();
			}
			finally {
				close();
			
			}		
			
			return list;
		}
	
}
