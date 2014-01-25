package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String driver = "com.mysql.jdbc.Driver";
		String url;
		String user;
		String pass;
		Connection con;
		ResultSet rs;
		Statement st;
		PreparedStatement pst;
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/hotel_db";
		user = "root";
		pass = "zaq123!@#";
		String username = "admin";
		String password="foo1";
		
		String selectUser = "SELECT id FROM passwords WHERE (username='"+username+"')AND (password=SHA1('"+password+"'))";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user,pass);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectUser);
			while(rs.next()){
				System.out.println(rs.getInt("id"));
				
				
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
