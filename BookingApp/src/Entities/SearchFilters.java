package Entities;

public class SearchFilters {
	private String roomType;			//table Apartment
	private int maxCost;				//table Apartment computed with min_cost_booking
	private String wifi;				//table Facilities
	private String aircondition;		//table Facilities
	private String heating;				//table Facilities
	private String kitchen;				//table Facilities
	private String tv;					//table Facilities
	private String parking;				//table Facilities
	private String elevator;			//table Facilities
	private String neighborhood;		//table Location
	private String from;
	private String to;
	
	public SearchFilters(String roomType, int maxCost, String wifi, String aircondition, String heating,
			String kitchen, String tv, String parking, String elevator, String neighborhood,String from, String to) {
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

	public String getWifi() {
		return wifi;
	}

	public void setWifi(String wifi) {
		this.wifi = wifi;
	}

	public String getAircondition() {
		return aircondition;
	}

	public void setAircondition(String aircondition) {
		this.aircondition = aircondition;
	}

	public String getHeating() {
		return heating;
	}

	public void setHeating(String heating) {
		this.heating = heating;
	}

	public String getKitchen() {
		return kitchen;
	}

	public void setKitchen(String kitchen) {
		this.kitchen = kitchen;
	}

	public String getTv() {
		return tv;
	}

	public void setTv(String tv) {
		this.tv = tv;
	}

	public String getParking() {
		return parking;
	}

	public void setParking(String parking) {
		this.parking = parking;
	}

	public String getElevator() {
		return elevator;
	}

	public void setElevator(String elevator) {
		this.elevator = elevator;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	
}
