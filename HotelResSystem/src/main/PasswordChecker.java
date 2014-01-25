package main;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PasswordChecker {
	private static String driver = "com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost/hotel_db";
	private static final String user="root";
	private static final String pass="zaq123!@#";
	private static Connection con;
	private static ResultSet rs;
	private static Statement st;
;
	
	public static boolean checkPassword(String username,String password){
		boolean flag=false;
		String selectUser = "SELECT id FROM passwords WHERE username='"+username+"'AND password=SHA1('"+password+"')";
			try {
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url, user,pass);
				st = (Statement) con.createStatement();
				rs = st.executeQuery(selectUser);
				if (rs.first()) {
					flag=true;
				}
			} catch (ClassNotFoundException e) {
			 JOptionPane.showMessageDialog(null, "Check your SQL Driver");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Check your sql validation(username, password)");
			}
		return flag;
	}

}
