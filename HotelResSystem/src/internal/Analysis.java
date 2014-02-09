package internal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Analysis {

	private String driver;
	private final String url;
	private final String username;
	private final String password;
	private Connection con;
	private ResultSet rs;
	private Statement st;
	private Vector<String> months;
	private Vector<String> years;
	
	public Analysis() {
		driver = "com.mysql.jdbc.Driver";
		url = "jdbc:mysql://localhost/hotel_db";
		username = "root";
		password = "zaq123!@#";
	}

	public Vector<String> getMonths() {
		months = new Vector<String>();
		String selectMonths = "SELECT * FROM month";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectMonths);
			while (rs.next()) {
				months.add(rs.getString("month"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return months;
	}
	public Vector<String> getYears() {
		years = new Vector<String>();
		String selectMonths = "SELECT * FROM year";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectMonths);
			while (rs.next()) {
				years.add(rs.getString("year"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return years;
	}
	public double getYearCost(String year) {
		double cost=0;
		String selectCost = "SELECT sum(a.cost) FROM analysis a, year y WHERE (a.id_year=y.id_year)AND(y.year="+year+")";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectCost);
			while (rs.next()) {
				cost=rs.getDouble(1);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return cost;
	}
	public double getYearProfit(String year) {
		double profit=0;
		String selectProfit = "SELECT sum(a.profit) FROM analysis a, year y WHERE (a.id_year=y.id_year)AND(y.year="+year+")";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectProfit);
			while (rs.next()) {
				profit=rs.getDouble(1);
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return profit;
	}
	public Map<String, Double> getMonthlyPrCo(String year,String month) {
		Map<String,Double> data=new HashMap<String, Double>();
		String selectdata = "SELECT * FROM analysis a, year y,month m WHERE (a.id_year=y.id_year)AND(a.id_month=m.id_month)AND(y.year="+year+")AND(m.month='"+month+"')";
		try {
			Class.forName(driver);
			con = (Connection) DriverManager.getConnection(url, username,
					password);
			st = (Statement) con.createStatement();
			rs = st.executeQuery(selectdata);
			while (rs.next()) {
				data.put("profit", rs.getDouble("a.profit"));
				data.put("cost", rs.getDouble("a.cost"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}