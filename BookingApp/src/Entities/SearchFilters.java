package Entities;

public class SearchFilters {
	private String roomType;			//table Apartment
	private int maxCost;				//table Apartment computed with min_cost_booking
	private boolean wifi;				//table Facilities
	private boolean aircondition;		//table Facilities
	private boolean heating;				//table Facilities
	private boolean kitchen;				//table Facilities
	private boolean tv;					//table Facilities
	private boolean parking;				//table Facilities
	private boolean elevator;			//table Facilities
	private String neighborhood;		//table Location
	private String from;				//table FreeDates
	private String to;					//table FreeDates
	private String tenants;				//table Apartment
	
	public SearchFilters(String roomType, int maxCost, boolean wifi, boolean aircondition, boolean heating,
			boolean kitchen, boolean tv, boolean parking, boolean elevator, String neighborhood, String from, String to,
			String tenants) {
		super();
		this.roomType = roomType;
		this.maxCost = maxCost;
		this.wifi = wifi;
		this.aircondition = aircondition;
		this.heating = heating;
		this.kitchen = kitchen;
		this.tv = tv;
		this.parking = parking;
		this.elevator = elevator;
		this.neighborhood = neighborhood;
		this.from = from;
		this.to = to;
		this.tenants = tenants;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getMaxCost() {
		return maxCost;
	}

	public void setMaxCost(int maxCost) {
		this.maxCost = maxCost;
	}

	public boolean isWifi() {
		return wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public boolean isAircondition() {
		return aircondition;
	}

	public void setAircondition(boolean aircondition) {
		this.aircondition = aircondition;
	}

	public boolean isHeating() {
		return heating;
	}

	public void setHeating(boolean heating) {
		this.heating = heating;
	}

	public boolean isKitchen() {
		return kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isElevator() {
		return elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTenants() {
		return tenants;
	}

	public void setTenants(String tenants) {
		this.tenants = tenants;
	}
	

}
