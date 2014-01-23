package internal;

public class Simple extends Room {

	private int numberOfBeds;
	private boolean air_con;
	private boolean multimedia;
	private boolean wi_fi;
	private boolean tv;
	private boolean refrigerator;
	
	
	public Simple() {
		super();
	}

	public int getNumberOfBeds() {
		return numberOfBeds;
	}
	public void setNumberOfBeds(int numberOfBeds) {
		this.numberOfBeds = numberOfBeds;
	}

	public boolean getAir_con() {
		return air_con;
	}

	public void setAir_con(boolean air_con) {
		this.air_con = air_con;
	}

	public boolean getMultimedia() {
		return multimedia;
	}

	public void setMultimedia(boolean multimedia) {
		this.multimedia = multimedia;
	}

	public boolean getWi_fi() {
		return wi_fi;
	}

	public void setWi_fi(boolean wi_fi) {
		this.wi_fi = wi_fi;
	}

	public boolean getTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean getRefrigerator() {
		return refrigerator;
	}

	public void setRefrigerator(boolean refrigerator) {
		this.refrigerator = refrigerator;
	}
	
	
}
