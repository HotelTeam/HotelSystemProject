package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String query = "SELECT r.id_room, b.number, r.price, r.offer FROM rooms r LEFT JOIN simpleroom s ON r.id_room = s.id_room LEFT JOIN bed_type b ON b.id_bed = s.id_bed";
			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "zaq123!@#";
			Connection con =  (Connection) DriverManager.getConnection(url, username, password);
			Statement st = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery(query);	
			
			
			 while (rs.next()) {
				 
				System.out.println("Room" + rs.getString("id_room") + "    number of beds:" + rs.getInt("number") + "    price:" + rs.getInt("price") + "    offer:" + rs.getDouble("offer"));
				 
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
