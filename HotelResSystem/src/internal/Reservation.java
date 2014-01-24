package internal;


public class Reservation {
	private int id;
	private Room room;
	private Client client;
	private String arrivalDate;
	private String departureDate;
	private double price;

	public Reservation(Room r, Client c, String ad, String dd, double pr,int id) {
		this.room = r;
		this.client = c;
		this.arrivalDate = ad;
		this.departureDate = dd;
		this.price = pr;
		this.id=id;

	}
	
	public int getId() {
		return id;
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
