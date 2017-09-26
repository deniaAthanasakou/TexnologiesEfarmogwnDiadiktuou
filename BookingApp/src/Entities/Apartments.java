package Entities;

import java.util.*;  

public class Apartments {
	
	//Apartment Table
	private String imagePath;
	private int cost_per_day;
	private String type;
	private int number_rooms;
	private int number_critics;
	private float average_critic;
	private int number_beds;
	private int number_bathrooms;
	private int number_bedrooms;
	private boolean living_room;
	private int capacity;
	private String description;
	private int max_tenants;
	private int min_cost_booking;
	private int cost_per_person;
	private ArrayList<String> more_photos;
	private int host_id;
	
	//Rule Table
	private boolean smoking_allowed;
	private boolean pets_allowed;
	private boolean events;
	private int min_days_booking;
	
	//Location Table
	private String map;
	private int address_number;
	private String street;
	private String postal_code;
	private String city;
	private String country;
	private String neighborhood;
	private String transportation;
	
	//Facilities Table
	private boolean wifi;
	private boolean aircondition;
	private boolean heating;
	private boolean kitchen;
	private boolean tv;
	private boolean parking;
	private boolean elevator;
	
	private ArrayList<Date> from_dates;
	private ArrayList<Date> to_dates;

	
	public Apartments(String imagePath,int cost_per_day,String type,int number_rooms,int number_critics,float average_critic,int number_beds,int number_bathrooms,int number_bedrooms,boolean living_room,int capacity, String description,int max_tenants,int min_cost_booking, int cost_per_person, ArrayList<String> more_photos,int host_id,boolean smoking_allowed,boolean pets_allowed,boolean events,int min_days_booking,String map,int address_number,String street, String postal_code, String city,String country,String neighborhood,String transportation,boolean wifi, boolean aircondition,boolean heating,boolean kitchen,boolean tv,boolean parking,boolean elevator,ArrayList<Date> from_dates, ArrayList<Date> to_dates) {
		this.imagePath = imagePath;
		this.cost_per_day = cost_per_day;
		this.type = type;
		this.number_rooms = number_rooms;
		this.number_critics = number_critics;
		this.average_critic = average_critic;
		this.number_beds = number_beds;
		this.number_bathrooms = number_bathrooms;
		this.number_bedrooms = number_bedrooms;
		this.living_room = living_room;
		this.capacity = capacity;
		this.description = description;
		this.max_tenants = max_tenants;
		this.min_cost_booking = min_cost_booking;
		this.cost_per_person = cost_per_person;
		if(more_photos!=null)
			this.more_photos = new ArrayList<String>(more_photos);
		this.host_id = host_id;
		
		this.smoking_allowed = smoking_allowed;
		this.pets_allowed = pets_allowed;
		this.events = events;
		this.min_days_booking = min_days_booking;
		
		this.map = map;
		this.address_number = address_number;
		this.street = street;
		this.postal_code = postal_code;
		this.city = city;
		this.country = country;
		this.neighborhood = neighborhood;
		this.transportation = transportation;
		
		this.wifi = wifi;
		this.aircondition = aircondition;
		this.heating = heating;
		this.kitchen = kitchen;
		this.tv = tv;
		this.parking = parking;
		this.elevator = elevator;
		
		if(from_dates!=null)
			this.from_dates = from_dates;
		
		if(to_dates!=null)
			this.to_dates = to_dates;

	}


	public ArrayList<Date> getFrom_dates() {
		return from_dates;
	}


	public void setFrom_dates(ArrayList<Date> from_dates) {
		this.from_dates = from_dates;
	}


	public ArrayList<Date> getTo_dates() {
		return to_dates;
	}


	public void setTo_dates(ArrayList<Date> to_dates) {
		this.to_dates = to_dates;
	}


	public String getImagePath() {
		return imagePath;
	}


	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}


	public int getCost_per_day() {
		return cost_per_day;
	}


	public void setCost_per_day(int cost_per_day) {
		this.cost_per_day = cost_per_day;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getNumber_rooms() {
		return number_rooms;
	}


	public void setNumber_rooms(int number_rooms) {
		this.number_rooms = number_rooms;
	}


	public int getNumber_critics() {
		return number_critics;
	}


	public void setNumber_critics(int number_critics) {
		this.number_critics = number_critics;
	}


	public float getAverage_critic() {
		return average_critic;
	}


	public void setAverage_critic(float average_critic) {
		this.average_critic = average_critic;
	}


	public int getNumber_beds() {
		return number_beds;
	}


	public void setNumber_beds(int number_beds) {
		this.number_beds = number_beds;
	}


	public int getNumber_bathrooms() {
		return number_bathrooms;
	}


	public void setNumber_bathrooms(int number_bathrooms) {
		this.number_bathrooms = number_bathrooms;
	}


	public int getNumber_bedrooms() {
		return number_bedrooms;
	}


	public void setNumber_bedrooms(int number_bedrooms) {
		this.number_bedrooms = number_bedrooms;
	}


	public boolean isLiving_room() {
		return living_room;
	}


	public void setLiving_room(boolean living_room) {
		this.living_room = living_room;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getMax_tenants() {
		return max_tenants;
	}


	public void setMax_tenants(int max_tenants) {
		this.max_tenants = max_tenants;
	}


	public int getMin_cost_booking() {
		return min_cost_booking;
	}


	public void setMin_cost_booking(int min_cost_booking) {
		this.min_cost_booking = min_cost_booking;
	}


	public int getCost_per_person() {
		return cost_per_person;
	}


	public void setCost_per_person(int cost_per_person) {
		this.cost_per_person = cost_per_person;
	}


	public ArrayList<String> getMore_photos() {
		return more_photos;
	}


	public void setMore_photos(ArrayList<String> more_photos) {
		this.more_photos = more_photos;
	}


	public int getHost_id() {
		return host_id;
	}


	public void setHost_id(int host_id) {
		this.host_id = host_id;
	}


	public boolean isSmoking_allowed() {
		return smoking_allowed;
	}


	public void setSmoking_allowed(boolean smoking_allowed) {
		this.smoking_allowed = smoking_allowed;
	}


	public boolean isPets_allowed() {
		return pets_allowed;
	}


	public void setPets_allowed(boolean pets_allowed) {
		this.pets_allowed = pets_allowed;
	}


	public boolean isEvents() {
		return events;
	}


	public void setEvents(boolean events) {
		this.events = events;
	}


	public int getMin_days_booking() {
		return min_days_booking;
	}


	public void setMin_days_booking(int min_days_booking) {
		this.min_days_booking = min_days_booking;
	}


	public String getMap() {
		return map;
	}


	public void setMap(String map) {
		this.map = map;
	}


	public int getAddress_number() {
		return address_number;
	}


	public void setAddress_number(int address_number) {
		this.address_number = address_number;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getPostal_code() {
		return postal_code;
	}


	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public String getNeighborhood() {
		return neighborhood;
	}


	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}


	public String getTransportation() {
		return transportation;
	}


	public void setTransportation(String transportation) {
		this.transportation = transportation;
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

}
