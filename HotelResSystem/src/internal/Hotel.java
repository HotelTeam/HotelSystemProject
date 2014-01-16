package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Hotel {
	private ArrayList<Room> rooms;
	private ArrayList<Room> simple_rooms;
	private ArrayList<Room> suite_rooms;
	private ArrayList<Reservation> reservations;
	private Connection con;
	private Statement st = null;
	
	public Hotel(){
		this.rooms = new ArrayList<Room>();
		this.simple_rooms = new ArrayList<Room>();
		this.suite_rooms = new ArrayList<Room>();
		this.getRoomsFromDB();
	}
	 
	public void modifyRooom(Room room){
		
	}
	
	public void  deleteRoom(Room room) {
	
	}
	
	public void setOffer(double offer) {
		
	}
	
	public ArrayList<Reservation> getReservations() {
		return reservations;
	}
	
	public ArrayList<Room> getRooms() {
		return rooms;
	}

	public ArrayList<Room> getSimpleRooms() {
		return simple_rooms;
	}
	
	public ArrayList<Room> getSuiteRooms() {
		return suite_rooms;
	}
	
	public void getRoomsFromDB(){
		String query = "SELECT r.id_room, r.number, r.price, r.offer, s.id_room, s.id_bed, s.air_con, s.multimedia, s.wi_fi, s.tv, s.refrigerator, t.id_room, t.jacuzzi, t.breakfast, t.meal, t.dinner, b.number FROM rooms r LEFT JOIN simpleroom s ON r.id_room = s.id_room LEFT JOIN suiteroom t ON r.id_room = t.id_room LEFT JOIN bed_type b ON b.id_bed = s.id_bed";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "";
			con = (Connection) DriverManager.getConnection(url, username, password);
			st = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			 while (rs.next()) {
				 if(rs.getInt("t.id_room") != 0){
					 Suite room = new Suite();
					 
					 room.setJacuzzi(rs.getInt("jacuzzi"));
					 room.setMeal(rs.getInt("meal"));
					 room.setBreakfast(rs.getInt("breakfast"));
					 room.setDinner(rs.getInt("dinner"));
					 room.setNumber(rs.getInt("number"));
					 room.setId_bed(rs.getInt("id_bed"));
					 room.setAir_con(rs.getInt("air_con"));
					 room.setMultimedia(rs.getInt("multimedia"));
					 room.setWi_fi(rs.getInt("wi_fi"));
					 room.setTv(rs.getInt("tv"));
					 room.setRefrigerator(rs.getInt("refrigerator"));
					 room.setOffer(rs.getDouble("offer"));
					 room.setPrice(rs.getInt("price"));
					 room.setNumber(rs.getInt("number"));
					 room.setId_room(rs.getInt("id_room"));
					 room.setNumberBeds(rs.getInt("b.number"));
					 
					 rooms.add(room);
					 suite_rooms.add(room);
					 
				 }else{
					 Simple room = new Simple();
					 
					 room.setNumber(rs.getInt("number"));
					 room.setId_bed(rs.getInt("id_bed"));
					 room.setAir_con(rs.getInt("air_con"));
					 room.setMultimedia(rs.getInt("multimedia"));
					 room.setWi_fi(rs.getInt("wi_fi"));
					 room.setTv(rs.getInt("tv"));
					 room.setRefrigerator(rs.getInt("refrigerator"));
					 room.setOffer(rs.getDouble("offer"));
					 room.setPrice(rs.getInt("price"));
					 room.setNumber(rs.getInt("number"));
					 room.setId_room(rs.getInt("id_room"));
					 room.setNumberBeds(rs.getInt("b.number"));

					 rooms.add(room);
					 simple_rooms.add(room);
				 }
			 }
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	

}
