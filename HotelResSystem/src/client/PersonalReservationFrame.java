package client;

import internal.Client;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class PersonalReservationFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private Client client;
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private TextArea area;
	private JPanel panel1;
	private String driver;
	private final String url;
	private final String username;
	private final String password;
	
	public PersonalReservationFrame(Client aClient){
		
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/hotel_db";
		username = "root";
		password = "zaq123!@#";
		
		client = aClient;
		
		area = new TextArea();
		
		panel1 = new JPanel();
		getResFromDB();
		panel1.add(area);
		
		this.setContentPane(panel1);
		this.pack();
		this.setLocation(100,100);
		this.setTitle("Welcome " +client.getFirstname()+" "+ client.getLastname());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void getResFromDB(){

		try {
			Class.forName(driver);
			
			String query = "SELECT s.id_room, s.id_res, r.arrival_date, r.departure_date, o.price, o.offer FROM specific_res s, reservation r, rooms o, client c, res_cl l WHERE (c.id_client = "+ client.getId_client()+") AND (c.id_client = l.id_client) AND (l.id_res = r.id_res) AND (r.id_res = s.id_res) AND (s.id_room = o.id_room)";
			
			con = (Connection) DriverManager.getConnection(url, username, password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(query);
			
			while (rs.next()){
				area.append("- Your room: "+rs.getInt("id_room"));
				area.append("\n");
				area.append("- Your reservation id: "+rs.getInt("id_res"));
				area.append("\n");
				area.append("- Arrival date: "+rs.getString("arrival_date"));
				area.append("\n");
				area.append("- Departure date: "+rs.getString("departure_date"));
				area.append("\n");
				area.append("- Price: "+rs.getDouble("price"));
				area.append("\n");
				area.append("- Offer: "+rs.getDouble("offer"));
				area.append("\n");
				area.append("- Total Price: "+ (rs.getDouble("price") - rs.getDouble("offer")*rs.getDouble("price")));
			}
			
		    rs.close();
			st.close();
			con.close();
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

}
