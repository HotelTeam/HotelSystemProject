package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Hotel {
	private Vector<Room> rooms;
	private Vector<Reservation> reservations;
	private String driver ="com.mysql.jdbc.Driver";
	private final String url;
	private final String username ;
	private final String password ;
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;
	public Hotel() {
		 driver ="com.mysql.jdbc.Driver";
		 url = "jdbc:mysql://localhost/hotel_db";
		 username = "root";
		 password = "zaq123!@#";
			
	}	
	public void modifyRooom(Room room) {
		try {
			Class.forName(driver);
			con =  (Connection) DriverManager.getConnection(url,username, password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery("");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteRoom(Room room) {
		String deleteRoom="DELETE FROM rooms WHERE id_room =?";
		try {
			Class.forName(driver);
			con =  (Connection) DriverManager.getConnection(url,username, password);
			con.setAutoCommit(false);			
			pst = (PreparedStatement) con.prepareStatement(deleteRoom);
			pst.setInt(1, room.getId_room());
			pst.execute();			
			con.commit();
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void createSimpleRoom(Simple room) {
		String insertRoom = "INSERT INTO rooms(number,price,offer)VALUES (?,?,?)";
		String selectRoom="SELECT id_room FROM rooms WHERE number="+room.getNumber()+"";
		String insertSimple="INSERT INTO simpleRoom (id_room, id_bed, air_con, multimedia, wi_fi, tv, refrigerator) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName(driver);
			con =  (Connection) DriverManager.getConnection(url,username, password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(insertRoom);
			pst.setInt(1, room.getNumber());
			pst.setInt(2, room.getPrice());
			pst.setDouble(3, room.getOffer());
			pst.execute();
			st= (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			int id_room=-1;
			if(rs.next()){
			id_room = rs.getInt("id_room");
			}
			pst =(PreparedStatement) con.prepareStatement(insertSimple);
			pst.setInt(1, id_room);
			pst.setInt(2,room.getNumberOfBeds());
			pst.setBoolean(3,room.getAir_con());
			pst.setBoolean(4,room.getMultimedia());
			pst.setBoolean(5,room.getWi_fi());
			pst.setBoolean(6,room.getTv());
			pst.setBoolean(7,room.getRefrigerator());
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
	
	public void createSuiteRoom(Suite room) {
		String insertRoom = "INSERT INTO rooms(number,price,offer)VALUES (?,?,?)";
		String selectRoom="SELECT id_room FROM rooms WHERE number="+room.getNumber()+"";
		String insertSimple="INSERT INTO simpleRoom (id_room, id_bed, air_con, multimedia, wi_fi, tv, refrigerator) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String insertSuite="INSERT INTO suiteRoom (id_room,jacuzzi,breakfast,meal,dinner) VALUES (?,?, ?, ?, ?);";
		try {
			Class.forName(driver);
			con =  (Connection) DriverManager.getConnection(url,username, password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(insertRoom);
			pst.setInt(1, room.getNumber());
			pst.setInt(2, room.getPrice());
			pst.setDouble(3, room.getOffer());
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
			pst.setInt(2,room.getNumberOfBeds());
			pst.setBoolean(3,room.getAir_con());
			pst.setBoolean(4,room.getMultimedia());
			pst.setBoolean(5,room.getWi_fi());
			pst.setBoolean(6,room.getTv());
			pst.setBoolean(7,room.getRefrigerator());
			pst.execute();
			pst.close();
			pst=(PreparedStatement)con.prepareStatement(insertSuite);
			pst.setInt(1, id_room);
			pst.setBoolean(2, room.getJacuzzi());
			pst.setBoolean(3, room.getBreakfast());
			pst.setBoolean(4, room.getMeal());
			pst.setBoolean(5, room.getDinner());
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
	
	
	public void setOffer(double offer) {

	}

	public Vector<Reservation> getReservations() {
		return reservations;
	}

	public Vector<Room> getRooms() {
		rooms = new Vector<Room>();

		return rooms;
	}


}
