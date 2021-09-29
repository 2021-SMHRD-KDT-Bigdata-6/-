package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnglishDAO {
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	
		//-------------------------------------------------------
	// 테스트 //테스트 //테스트으으으~~~~~//되라되라되랏
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
		
//<<<<<<< HEAD
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
}
		
		