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
	private String driver;
	private final String url;
	private final String username;
	private final String password;
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private PreparedStatement pst;

	public Hotel() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/hotel_db";
		username = "root";
		password = "zaq123!@#";

	}

	public boolean updateSuiteRoom(Suite room) {
		boolean flag = false;
		String update1 = "UPDATE rooms SET price = ?, offer =? WHERE id_room =?";
		String update2 = "UPDATE simpleRoom SET id_bed=? , air_con = ?,multimedia = ?,wi_fi =?,tv = ?,refrigerator =?" +
				" WHERE id_room =?";
		String update3 = "UPDATE suiteRoom SET jacuzzi = ? , breakfast= ? ,meal = ?,dinner =? WHERE id_room =?";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(update1);
			pst.setInt(3, room.getId_room());
			pst.setDouble(2, room.getOffer());
			pst.setDouble(1, room.getPrice());
			int val = pst.executeUpdate();
			if(val!=Statement.EXECUTE_FAILED)flag=true;
			if (flag) {
				flag=false;
				PreparedStatement pst1 = (PreparedStatement) con
						.prepareStatement(update2);
				pst1.setInt(7, room.getId_room());
				pst1.setInt(1,room.getNumberOfBeds());
				pst1.setBoolean(2, room.getAir_con());
				pst1.setBoolean(3, room.getMultimedia());
				pst1.setBoolean(4, room.getWi_fi());
				pst1.setBoolean(5, room.getTv());
				pst1.setBoolean(6, room.getRefrigerator());
				val = pst1.executeUpdate();
				if(val!=Statement.EXECUTE_FAILED)flag=true;
				pst1.close();
				if (flag) {
					flag=false;
					PreparedStatement pst2 = (PreparedStatement) con
							.prepareStatement(update3);
					pst2.setBoolean(1, room.getJacuzzi());
					pst2.setBoolean(2, room.getBreakfast());
					pst2.setBoolean(3, room.getMeal());
					pst2.setBoolean(4, room.getDinner());
					pst2.setInt(5, room.getId_room());	
					val = pst2.executeUpdate();
					if(val!=Statement.EXECUTE_FAILED)flag=true;
					pst2.close();
				}
			}
			if (flag) {
				con.commit();
			} else {
				con.rollback();
			}
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean updateSimpleRoom(Simple room) {
		boolean flag = false;
		String update1 = "UPDATE rooms SET price = ?, offer =? WHERE id_room =?";
		String update2 = "UPDATE simpleRoom SET id_bed=? , air_con = ?,multimedia = ?,wi_fi =?,tv = ?,refrigerator =?" +
				" WHERE id_room =?";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(update1);
			pst.setInt(3, room.getId_room());
			pst.setDouble(2, room.getOffer());
			pst.setDouble(1, room.getPrice());
			int val = pst.executeUpdate();
			if(val!=Statement.EXECUTE_FAILED)flag=true;
			if (flag) {
				flag=false;
				PreparedStatement pst1 = (PreparedStatement) con
						.prepareStatement(update2);
				pst1.setInt(7, room.getId_room());
				pst1.setInt(1,room.getNumberOfBeds());
				pst1.setBoolean(2, room.getAir_con());
				pst1.setBoolean(3, room.getMultimedia());
				pst1.setBoolean(4, room.getWi_fi());
				pst1.setBoolean(5, room.getTv());
				pst1.setBoolean(6, room.getRefrigerator());
				val = pst1.executeUpdate();
				if(val!=Statement.EXECUTE_FAILED)flag=true;
				pst1.close();
			}
			if (flag) {
				con.commit();
			} else {
				con.rollback();
			}
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean isSuite(Room room) {
		boolean flag = false;
		String selectRoom = "SELECT* FROM suiteRoom WHERE id_room ="
				+ room.getId_room();
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
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

		return flag;
	}

	public void deleteRoom(Room room) {
		String deleteRoom = "DELETE FROM rooms WHERE id_room =?";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
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
		String selectRoom = "SELECT id_room FROM rooms WHERE number="
				+ room.getNumber() + "";
		String insertSimple = "INSERT INTO simpleRoom (id_room, id_bed, air_con, multimedia, wi_fi, tv, refrigerator) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(insertRoom);
			pst.setInt(1, room.getNumber());
			pst.setDouble(2, room.getPrice());
			pst.setDouble(3, room.getOffer());
			pst.execute();
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			int id_room = -1;
			if (rs.next()) {
				id_room = rs.getInt("id_room");
			}
			pst = (PreparedStatement) con.prepareStatement(insertSimple);
			pst.setInt(1, id_room);
			pst.setInt(2, room.getNumberOfBeds());
			pst.setBoolean(3, room.getAir_con());
			pst.setBoolean(4, room.getMultimedia());
			pst.setBoolean(5, room.getWi_fi());
			pst.setBoolean(6, room.getTv());
			pst.setBoolean(7, room.getRefrigerator());
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
		String selectRoom = "SELECT id_room FROM rooms WHERE number="
				+ room.getNumber() + "";
		String insertSimple = "INSERT INTO simpleRoom (id_room, id_bed, air_con, multimedia, wi_fi, tv, refrigerator) VALUES (?, ?, ?, ?, ?, ?, ?)";
		String insertSuite = "INSERT INTO suiteRoom (id_room,jacuzzi,breakfast,meal,dinner) VALUES (?,?, ?, ?, ?);";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(insertRoom);
			pst.setInt(1, room.getNumber());
			pst.setDouble(2, room.getPrice());
			pst.setDouble(3, room.getOffer());
			pst.execute();
			pst.close();
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			int id_room = -1;
			if (rs.next()) {
				id_room = rs.getInt("id_room");
			}
			pst = (PreparedStatement) con.prepareStatement(insertSimple);
			pst.setInt(1, id_room);
			pst.setInt(2, room.getNumberOfBeds());
			pst.setBoolean(3, room.getAir_con());
			pst.setBoolean(4, room.getMultimedia());
			pst.setBoolean(5, room.getWi_fi());
			pst.setBoolean(6, room.getTv());
			pst.setBoolean(7, room.getRefrigerator());
			pst.execute();
			pst.close();
			pst = (PreparedStatement) con.prepareStatement(insertSuite);
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

	public void setOffer(double offer, Room room) {
		String setOffer = "UPDATE rooms SET offer=? WHERE id_room =?";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			con.setAutoCommit(false);
			pst = (PreparedStatement) con.prepareStatement(setOffer);
			pst.setDouble(1, offer);
			pst.setInt(2, room.getId_room());
			pst.execute();
			con.commit();
			pst.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean hasRoom(Room room) {
		boolean flag = false;
		String selectRoom = "SELECT id_room FROM rooms WHERE number="
				+ room.getNumber() + "";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			if (rs.first()) {
				flag = true;
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	public Vector<Reservation> getReservations() {
		reservations = new Vector<Reservation>();
		Reservation res;
		Client cl;
		Simple roomSimple;
		String selectReservation = "SELECT r.id_res, r.arrival_date, r.departure_date, c.id_client, "
				+ "c.username, c.password, c.age, c.creditcard, c.firstname,"
				+ " c.lastname, sr.price, ro.id_room, ro.number, ro.price, ro.offer "
				+ "FROM reservation r, res_cl rc, client c, specific_res sr, rooms ro "
				+ "WHERE(rc.id_res = r.id_res)AND(rc.id_client = c.id_client)AND(ro.id_room = sr.id_room)AND(r.id_res = sr.id_res)";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectReservation);
			while (rs.next()) {
				cl = new Client();
				cl.setAge(rs.getInt("c.age"));
				cl.setCreditCard(rs.getString("c.creditcard"));
				cl.setFirstname(rs.getString("c.firstname"));
				cl.setLastname(rs.getString("c.lastname"));
				cl.setId_client(rs.getInt("c.id_client"));
				cl.setUsername(rs.getString("c.username"));
				cl.setPassword(rs.getString("c.password"));
				roomSimple = new Simple();
				roomSimple.setId_room(rs.getInt("ro.id_room"));
				roomSimple.setPrice(rs.getDouble("ro.price"));
				roomSimple.setNumber(rs.getInt("ro.number"));
				roomSimple.setOffer(rs.getDouble("ro.offer"));
				res = new Reservation(roomSimple, cl,
						rs.getString("r.arrival_date"),
						rs.getString("r.departure_date"),
						rs.getDouble("sr.price"), rs.getInt("r.id_res"));
				reservations.add(res);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reservations;
	}

	public Vector<Room> getRooms() {
		rooms = new Vector<Room>();
		Simple simpleRoom;
		Suite suiteRoom;
		String selectRoom = "SELECT * FROM rooms";
		String selectSimple = "SELECT *FROM simpleRoom WHERE id_room=?";
		String selectSuite = "SELECT * FROM suiteRoom WHERE id_room=?";
		PreparedStatement pst1;
		PreparedStatement pst2;
		ResultSet rs1;
		ResultSet rs2;
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectRoom);
			while (rs.next()) {
				pst1 = (PreparedStatement) con.prepareStatement(selectSimple);
				pst1.setInt(1, rs.getInt("id_room"));
				rs1 = pst1.executeQuery();
				pst2 = (PreparedStatement) con.prepareStatement(selectSuite);
				pst2.setInt(1, rs.getInt("id_room"));
				rs2 = pst2.executeQuery();
				if (rs2.next()) {
					suiteRoom = new Suite();
					suiteRoom.setId_room(rs2.getInt("id_room"));
					suiteRoom.setJacuzzi(rs2.getBoolean("jacuzzi"));
					suiteRoom.setBreakfast(rs2.getBoolean("breakfast"));
					suiteRoom.setDinner(rs2.getBoolean("dinner"));
					suiteRoom.setMeal(rs2.getBoolean("meal"));
					if (rs1.next()) {
						suiteRoom.setAir_con(rs1.getBoolean("air_con"));
						suiteRoom.setMultimedia(rs1.getBoolean("multimedia"));
						suiteRoom.setNumberOfBeds(rs1.getInt("id_bed"));
						suiteRoom.setRefrigerator(rs1
								.getBoolean("refrigerator"));
						suiteRoom.setWi_fi(rs1.getBoolean("wi_fi"));
						suiteRoom.setTv(rs1.getBoolean("tv"));
					}
					suiteRoom.setPrice(rs.getDouble("price"));
					suiteRoom.setOffer(rs.getDouble("offer"));
					suiteRoom.setNumber(rs.getInt("number"));
					rooms.add(suiteRoom);
				} else {
					simpleRoom = new Simple();
					if (rs1.next()) {
						simpleRoom.setId_room(rs1.getInt("id_room"));
						simpleRoom.setAir_con(rs1.getBoolean("air_con"));
						simpleRoom.setMultimedia(rs1.getBoolean("multimedia"));
						simpleRoom.setNumberOfBeds(rs1.getInt("id_bed"));
						simpleRoom.setRefrigerator(rs1
								.getBoolean("refrigerator"));
						simpleRoom.setWi_fi(rs1.getBoolean("wi_fi"));
						simpleRoom.setTv(rs1.getBoolean("tv"));
					}
					simpleRoom.setPrice(rs.getDouble("price"));
					simpleRoom.setOffer(rs.getDouble("offer"));
					simpleRoom.setNumber(rs.getInt("number"));
					rooms.add(simpleRoom);
				}
				rs1.close();
				rs2.close();
				pst1.close();
				pst2.close();
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return rooms;
	}

}
