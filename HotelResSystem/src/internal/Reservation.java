package internal;

import java.sql.Date;

public class Reservation {

	private Room room;
	private Client client;
	private String arrivalDate;
	private String departureDate;
	private double price;

	public Reservation(Room r, Client c, String ad, String dd, double pr) {
		this.room = r;
		this.client = c;
		this.arrivalDate = ad;
		this.departureDate = dd;
		this.price = pr;

	}

	public String getArrivalDate() {
		return arrivalDate;
	}

	public Client getClient() {
		return client;
	}

	public String getDepartureDate() {
		return departureDate;
	}

	public double getPrice() {
		return price;
	}

	public Room getRoom() {
		return room;
	}
}
