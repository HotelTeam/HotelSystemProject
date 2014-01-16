package internal;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Reservation {
	
	private Room room;
	private Client client;
	private int day;
	private int month;
	private int year;
	private Date arrival_date;
	private Date departure_date;
	private PreparedStatement preparedStatement = null;
	private Connection con;
	private boolean reservationAddedDB;
	
	public Reservation(){
		reservationAddedDB = false;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public void addReservationToDB(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "";
			con = (Connection) DriverManager.getConnection(url, username, password);
			
			Statement st = (Statement) con.createStatement();

			preparedStatement = (PreparedStatement) con.prepareStatement("insert into reservation (arrival_date, departure_date) values (default, ?,?)");
			
		    preparedStatement.setString(1, "" + this.getArrival_date());
		    preparedStatement.setString(2,  "" + this.getDeparture_date());

		    preparedStatement.executeUpdate();
		    
			String query = "SELECT id_res FROM reservation";
			ResultSet rs = st.executeQuery(query);
		    
			preparedStatement = (PreparedStatement) con.prepareStatement("insert into specific_res (id_room, id_month, id_year, price, id_res) values (default, ?,?,?,?,?)");
			
			preparedStatement.setInt(1, this.getRoom().getId_room());
			preparedStatement.setInt(2, this.getMonth());
			preparedStatement.setInt(3, this.getYear());
			preparedStatement.setDouble(4, this.getRoom().getPrice());
			preparedStatement.setInt(5, rs.getInt("id_res"));
			
			preparedStatement.executeUpdate();
			
			preparedStatement = (PreparedStatement) con.prepareStatement("insert into res_cl (id_res, id_client) values (default, ?,?)");
			preparedStatement.setInt(1, rs.getInt("id_res"));
			preparedStatement.setInt(2, rs.getInt(this.getClient().getIdClient()));
			
		    preparedStatement.close();
			st.close();
			con.close();
			
			reservationAddedDB = true;
			
		} catch(Exception e) {
			System.out.println(e);
			reservationAddedDB = false;
		}
	}
	
}
