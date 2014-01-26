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
		
		boolean flag=false;
		String selectRoom = "SELECT* FROM suiteRoom WHERE id_room ="+9;
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, user,
					pass);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			if (rs.first()) {
				flag = true;
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		System.out.println(flag);
	
	}

}
