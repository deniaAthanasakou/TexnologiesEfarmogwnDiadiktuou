package Entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the apartment database table.
 * 
 */
@Entity
@NamedQuery(name="Apartment.findAll", query="SELECT a FROM Apartment a")
public class Apartment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private int roomId;

	@Column(name="average_critic")
	private BigDecimal averageCritic;

	private int capacity;

	@Column(name="cost_per_day")
	private int costPerDay;

	@Column(name="cost_per_person")
	private int costPerPerson;

	private String description;

	@Column(name="host_id")
	private int hostId;

	private byte livingroom;

	@Column(name="max_tenants")
	private int maxTenants;

	@Column(name="min_cost_booking")
	private int minCostBooking;

	@Column(name="number_bathrooms")
	private int numberBathrooms;

	@Column(name="number_bedrooms")
	private int numberBedrooms;

	@Column(name="number_beds")
	private int numberBeds;

	@Column(name="number_critics")
	private int numberCritics;

	@Column(name="number_rooms")
	private int numberRooms;


	@Lob
	@Column(name="room_photo")
	private byte[] roomPhoto;

	private String type;

	//bi-directional one-to-one association to Facility
	@OneToOne(mappedBy="apartment")
	private Facility facility;

	//bi-directional one-to-one association to Freedate
	@OneToOne(mappedBy="apartment")
	private Freedate freedate;

	//bi-directional one-to-one association to Location
	@OneToOne(mappedBy="apartment")
	private Location location;

	private String imagePath;
	private boolean livingRoom;
	private float average_critic;
	
	public void setImagePath(String imagePath) {
		this.imagePath=imagePath;
	}
	
	public String getImagePath() {
		return this.imagePath;
	}
	
	public void setLivingRoom(boolean livingRoom) {
		this.livingRoom=livingRoom;
	}
	
	public boolean getLivingRoom() {
		return this.livingRoom;
	}
	
	public void setAverage_critic(float average_critic) {
		this.average_critic=average_critic;
	}
	
	public float getAverage_critic() {
		return this.average_critic;
	}
	
	public Apartment() {}

	public Apartment(float average_critic, int capacity, int costPerDay, int costPerPerson,
			String description, int hostId, boolean livingRoom, int maxTenants, int minCostBooking, int numberBathrooms,
			int numberBedrooms, int numberBeds, int numberCritics, int numberRooms, String imagePath,
			String type) {
		
		this.average_critic = average_critic;
		this.capacity = capacity;
		this.costPerDay = costPerDay;
		this.costPerPerson = costPerPerson;
		this.description = description;
		this.hostId = hostId;
		this.livingRoom = livingRoom;
		this.maxTenants = maxTenants;
		this.minCostBooking = minCostBooking;
		this.numberBathrooms = numberBathrooms;
		this.numberBedrooms = numberBedrooms;
		this.numberBeds = numberBeds;
		this.numberCritics = numberCritics;
		this.numberRooms = numberRooms;
		this.imagePath = imagePath;
		this.type = type;
	}


	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public BigDecimal getAverageCritic() {
		return this.averageCritic;
	}

	public void setAverageCritic(BigDecimal averageCritic) {
		this.averageCritic = averageCritic;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getCostPerDay() {
		return this.costPerDay;
	}

	public void setCostPerDay(int costPerDay) {
		this.costPerDay = costPerDay;
	}

	public int getCostPerPerson() {
		return this.costPerPerson;
	}

	public void setCostPerPerson(int costPerPerson) {
		this.costPerPerson = costPerPerson;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getHostId() {
		return this.hostId;
	}

	public void setHostId(int hostId) {
		this.hostId = hostId;
	}

	public byte getLivingroom() {
		return this.livingroom;
	}

	public void setLivingroom(byte livingroom) {
		this.livingroom = livingroom;
	}

	public int getMaxTenants() {
		return this.maxTenants;
	}

	public void setMaxTenants(int maxTenants) {
		this.maxTenants = maxTenants;
	}

	public int getMinCostBooking() {
		return this.minCostBooking;
	}

	public void setMinCostBooking(int minCostBooking) {
		this.minCostBooking = minCostBooking;
	}

	public int getNumberBathrooms() {
		return this.numberBathrooms;
	}

	public void setNumberBathrooms(int numberBathrooms) {
		this.numberBathrooms = numberBathrooms;
	}

	public int getNumberBedrooms() {
		return this.numberBedrooms;
	}

	public void setNumberBedrooms(int numberBedrooms) {
		this.numberBedrooms = numberBedrooms;
	}

	public int getNumberBeds() {
		return this.numberBeds;
	}

	public void setNumberBeds(int numberBeds) {
		this.numberBeds = numberBeds;
	}

	public int getNumberCritics() {
		return this.numberCritics;
	}

	public void setNumberCritics(int numberCritics) {
		this.numberCritics = numberCritics;
	}

	public int getNumberRooms() {
		return this.numberRooms;
	}

	public void setNumberRooms(int numberRooms) {
		this.numberRooms = numberRooms;
	}


	public byte[] getRoomPhoto() {
		return this.roomPhoto;
	}

	public void setRoomPhoto(byte[] roomPhoto) {
		this.roomPhoto = roomPhoto;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Facility getFacility() {
		return this.facility;
	}

	public void setFacility(Facility facility) {
		this.facility = facility;
	}

	public Freedate getFreedate() {
		return this.freedate;
	}

	public void setFreedate(Freedate freedate) {
		this.freedate = freedate;
	}

	public Location getLocation() {
		return this.location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

}