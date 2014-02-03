package internal;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Client {

	private int id_client;
	private String username;
	private String password;
	private int age;
	private String email;
	private String firstname;
	private String lastname;
	private String creditCard;
	private PreparedStatement preparedStatement = null;
	private Connection con;
	private boolean clientAddedDB;

	public Client() {
		clientAddedDB = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		String pass = new String(password);
		return pass;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public void setId_client(int val) {
		this.id_client = val;
	}

	public int getIdClient() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "";
			con = (Connection) DriverManager.getConnection(url, username,
					password);

			Statement st = (Statement) con.createStatement();

			String query = "SELECT id_client FROM client";
			ResultSet rs = st.executeQuery(query);

			this.id_client = rs.getInt("id_client");
			return id_client;

		} catch (Exception e) {
			System.out.println(e);
			return 0;
		}
	}

	public void addClientToDB() {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			String url = "jdbc:mysql://localhost/hotel_db";
			String username = "root";
			String password = "";
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			Statement st = (Statement) con.createStatement();

			preparedStatement = (PreparedStatement) con
					.prepareStatement("insert into client (id_client, username, password, age, mail, creditcard, firstname, lastname) values (default, ?,?,?,?,?,?,?)");

			preparedStatement.setString(1, this.getUsername());
			preparedStatement.setString(2, this.getPassword());
			preparedStatement.setInt(3, this.getAge());
			preparedStatement.setString(4, this.getEmail());
			preparedStatement.setString(5, this.getCreditCard());
			preparedStatement.setString(6, this.getFirstname());
			preparedStatement.setString(7, this.getLastname());

			preparedStatement.executeUpdate();

			preparedStatement.close();
			st.close();
			con.close();

			clientAddedDB = true;

		} catch (Exception e) {
			System.out.println(e);
			clientAddedDB = false;
		}
	}

	public boolean getClientAddedDB() {
		return clientAddedDB;
	}

}
