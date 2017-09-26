package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the location database table.
 * 
 */
@Entity
@NamedQuery(name="Location.findAll", query="SELECT l FROM Location l")
public class Location implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private int roomId;

	@Column(name="address_number")
	private int addressNumber;

	private String city;

	private String country;

	private String map;

	private String neighborhood;

	@Column(name="postal_code")
	private String postalCode;

	private String street;

	private String transportation;

	//bi-directional one-to-one association to Apartment
	@OneToOne
	@JoinColumn(name="room_id")
	private Apartment apartment;

	public Location() {
	}
	
	

	public Location(int addressNumber, String city, String country, String map, String neighborhood, String postalCode,
			String street, String transportation) {
		this.addressNumber = addressNumber;
		this.city = city;
		this.country = country;
		this.map = map;
		this.neighborhood = neighborhood;
		this.postalCode = postalCode;
		this.street = street;
		this.transportation = transportation;
	}



	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getAddressNumber() {
		return this.addressNumber;
	}

	public void setAddressNumber(int addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return this.country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMap() {
		return this.map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getNeighborhood() {
		return this.neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTransportation() {
		return this.transportation;
	}

	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}