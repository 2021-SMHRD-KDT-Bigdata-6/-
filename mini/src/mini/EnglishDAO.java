package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EnglishDAO {
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	
		//-------------------------------------------------------
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
		
		
		
		//------------------------------------------------------------
		//오답노트 확인
		
		public ArrayList<EnglishVO> selectAll() {
		ArrayList<EnglishVO> list = new ArrayList<EnglishVO>();
			
		getConn();
		
		String sql = "select * from note";
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(sql);
			
			while(rs.next()) {
			String word = rs.getNString("word");
			String answer = rs.getNString("answer");
			
			EnglishVO vo = new EnglishVO(word, answer);		
			list.add(vo);
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
		
		close();
		}
		
		return list;
		}
		
		
		
		
		
		
		
		
		
}
		
		