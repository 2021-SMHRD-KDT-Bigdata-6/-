package mm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;                                        // 커넥션에서 생성하면서 SQL 문이 DB에 전송되어진다. Statement 클래스를 상속하고 있다.
import java.sql.ResultSet;
import java.sql.SQLException; 
import java.util.ArrayList;
import java.util.Random;




public class EnglishDAO {
	private Random ran = new Random();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// --------------------------------------------------------         // DBMS - 데이터 베이스 관리 시스템
	private void getConn() {
		try {                                                         //  0.프로젝트 안에 드라이버 파일 넣기
			Class.forName("oracle.jdbc.driver.OracleDriver");          // 1. 드라이버 로딩 (동적 로딩)
			                                                           // 어떠한 DBMS의 드라이버를 사용할지 명시(드라이버 경로작성) 
			                                                           // 예외 처리 해줘야 한다.
			String db_url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";  //2. 커넥션 연결 (통로)
			String db_id = "cgi_5_5";                                            // 드라이버를 통해 DB URL, DB ID, DB PW 보내 커넥션을 연결한다.
			String db_pw = "smhrd5";                                             // 예외처리 해워야 한다.
			conn = DriverManager.getConnection(db_url, db_id, db_pw);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ------------------------------------------------------------
	private void close() {                                              // 4. 연결 종료
		try {                                                          // 연결 종료는 역순으로 수행한다.
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

	// -------------------- 회원 가입 --------------------------------------
	public int join(ProfileVO vo) {

		int cnt = 0;

		getConn();

		try {                                                       //   sql 문 (id , pw , name , point) 값 넣기
			String sql = "insert into PROFILE values(?,?,?,?)";     
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());                         //  ? 물음표 첫번째 칸에 들어갈..거
			psmt.setString(2, vo.getPw());
			psmt.setString(3, vo.getName());
			psmt.setLong(4, vo.getPoint());
			cnt = psmt.executeUpdate();                           //  executeUpdate - 데이터를 추가 ,삭제 ,수정 하는 sql문 실행 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}

//   ----------------------  로 그 인 ----------------------

	public ProfileVO login(ProfileVO vo) {
		getConn();
		ProfileVO info = null;
		try {
			String sql = "select * from profile where id =? and pw =?";     // sql 문 (profile 테이블에서  id와 pw 일치 찾기)
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			rs = psmt.executeQuery();                                 // executeQuery - db에서 데이터를 가져옴   select 문만 가능?
			if (rs.next()) {
				String id = rs.getString(1);                          //  profile 테이블  첫번쨰 열 데이터 값 
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

	// --------------------- 랭킹 포인트 넘기기 -------------------------
	public int RankPoint(ProfileVO vo) {
		int cnt = 0;
		try {
			getConn();
//			String sql="insert into rank values(?,?)";
			String sql = "update profile set point=point+? where name=?";     // profile 테이블  name에 맞게 point 수정
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

	// ------------------------------- 틀린단어 넘기기 --------------------
	public int wrong(NoteVO vo) {
		int bnt = 0;
		try {
			getConn();
			String sql = "insert into note values(?,?,?)";                   //  note 테이블  word , id, answer  넣기
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

	// ------------- 쉬움 ----------------------------
	public ArrayList<WordVO> easyword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();                     // 배열 생성
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=5 ";
			psmt = conn.prepareStatement(sql);                             //   길이가 5이하인 word   랜덤으로 불러오기 
			rs = psmt.executeQuery();
			while (rs.next()) {
				String word = rs.getString(1);
				String answer = rs.getString(2);
				WordVO vo = new WordVO(word, 0, answer);                      // word ,  각 word에 부여된 숫자, answer
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	// ---------------------------보통 -------------------------------
	public ArrayList<WordVO> nomalword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)<=7 ";
			psmt = conn.prepareStatement(sql);                              //   길이가 7이하인 word   랜덤으로 불러오기 
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

	// -------------------- 어려움 --------------------------
	public ArrayList<WordVO> hardword() {
		ArrayList<WordVO> list = new ArrayList<WordVO>();
		getConn();
		try {
			String sql = "select word,answer from(select word,answer from word order by dbms_random.value)where length(word)>7 ";
			psmt = conn.prepareStatement(sql);                                //   길이가 7초과인 word   랜덤으로 불러오기 
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

	// ------------------랭킹 ----------------------------------
	public ArrayList<ProfileVO> rank() {
		ArrayList<ProfileVO> list = new ArrayList<ProfileVO>();
		getConn();
		try {
			String sql = "select name,point from profile order by point desc";    // profile 테이블에서 name,point  desc(내림차순) 으로 불러오기
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

	// -----------------오답노트 -------------------------
	public ArrayList<NoteVO> note(ProfileVO vo) {
		ArrayList<NoteVO> list = new ArrayList<NoteVO>();
		getConn();
		try {
			String sql = "select * from note where id=? order by wrong_word asc ";      //note 테이블에서 id 에맞는  틀린단어들을  asc(오름차순) 으로 불러오기
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();

			while (rs.next()) {
				String word = rs.getString(1);             // note 테이블 1행
				String answer = rs.getString(3);           // note 테이블 3행
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
	//---------------note 삭제------------------
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
	//------------------profile 삭제----------------------
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
	// ---------------------회원 정보 ---------------

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
