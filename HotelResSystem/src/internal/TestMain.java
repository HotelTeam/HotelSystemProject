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
		String username;
		String password;
		Connection con;
		ResultSet rs;
		Statement st;
		PreparedStatement pst;
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/hotel_db";
		username = "root";
		password = "zaq123!@#";

		String insertRoom = "INSERT INTO rooms(number,price,offer)VALUES (?,?,?)";
		String selectRoom="SELECT id_room FROM rooms WHERE number=5";
		String insertSimple="INSERT INTO simpleRoom (id_room, id_bed, air_con, multimedia, wi_fi, tv, refrigerator) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String insertSuite="INSERT INTO suiteRoom (id_room,jacuzzi,breakfast,meal,dinner) VALUES (?,?, ?, ?, ?);";
		try {
			Class.forName(driver);
			con =  (Connection) DriverManager.getConnection(url,username, password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(insertRoom);
			pst.setInt(1, 5);
			pst.setInt(2, 130);
			pst.setDouble(3, 0.2);
			pst.execute();
			pst.close();
			st= (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			int id_room=-1;
			if(rs.next()){
			id_room = rs.getInt("id_room");
			}
			pst =(PreparedStatement) con.prepareStatement(insertSimple);
			pst.setInt(1, id_room);
			pst.setInt(2,3);
			pst.setBoolean(3,true);
			pst.setBoolean(4,true);
			pst.setBoolean(5,true);
			pst.setBoolean(6,true);
			pst.setBoolean(7,true);
			pst.execute();
			pst.close();
			pst=(PreparedStatement)con.prepareStatement(insertSuite);
			pst.setInt(1, id_room);
			pst.setBoolean(2, true);
			pst.setBoolean(3, true);
			pst.setBoolean(4, true);
			pst.setBoolean(5, true);
			pst.execute();
			con.commit();
			rs.close();
			st.close();
			pst.close();
			con.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
