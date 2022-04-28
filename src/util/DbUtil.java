/* 2018  2018年9月17日  下午2:14:17
 * DbUtil.java
 * guorf
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author guorf
 * @date   2018年9月17日 下午2:14:17
 **/
public class DbUtil {
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = 
			DriverManager.getConnection
			("jdbc:mysql://127.0.0.1:3306/rj02?"+
					"useUnicode=true&characterEncoding=utf-8",
					"root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return conn;		
	}
	public static void closeAll
	(Connection conn,Statement stmt,ResultSet rs){
		try{
			if(rs!=null){
				rs.close();
			}
			if(stmt!=null){
				stmt.close();
			}
			if(conn!=null){
				conn.close();
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

}
