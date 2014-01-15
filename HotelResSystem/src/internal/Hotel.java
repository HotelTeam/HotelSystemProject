package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Hotel {
	private Vector<Room> rooms;
	private Vector<Reservation> reservations;
	 
	public void modifyRooom(Room room){
		
	}
	
	public void  deleteRoom(Room room) {
	
	}
	
	public void setOffer(double offer) {
		
	}
	
	public Vector<Reservation> getReservations() {
		return reservations;
	}
	
	public Vector<Room> getRooms() {
		return rooms;
	}

	/*public void getRoomsFromDB(){
		String query = "select id_room, number, price, offer from rooms";
		
		SELECT `r`.`id_room`, `r`.`number`, `r`.`price`, `r`.`offer`, `s`.`id_bed`, `s`.`air_con`, `s`.`multimedia`, `s`.`wi_fi`, `s`.`tv`, `s`.`refrigerator`, `t`.`jacuzzi`, `t`.`breakfast`, `t`.`meal`, `t`.`dinner` FROM `rooms` `r` 
		LEFT JOIN `simpleroom` `s` ON `r`.`id_room` = `s`.`id_room`
				LEFT JOIN `suiteroom` `t` ON `r`.`id_room` = `t`.`id_room`
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "";
			con = (Connection) DriverManager.getConnection(url, username, password);
			Statement st = (Statement) con.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			 while (rs.next()) {
				 String coffeeName = rs.getString("COF_NAME");
			        int supplierID = rs.getInt("SUP_ID");
			        float price = rs.getFloat("PRICE");
			        int sales = rs.getInt("SALES");
			        int total = rs.getInt("TOTAL");
			        System.out.println(coffeeName + "\t" + supplierID + "\t" + price + "\t" + sales + "\t" + total);
			 }
			
		} catch(Exception e) {
			System.out.println(e);
		}
		
	}*/
	
	
	

}
