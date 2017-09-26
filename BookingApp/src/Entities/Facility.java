package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the facilities database table.
 * 
 */
@Entity
@Table(name="facilities")
@NamedQuery(name="Facility.findAll", query="SELECT f FROM Facility f")
public class Facility implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private int roomId;

	private boolean aircondition;

	private boolean elevator;

	private boolean heating;

	private boolean kitchen;

	private boolean parking;

	private boolean tv;

	private boolean wifi;

	//bi-directional one-to-one association to Apartment
	@OneToOne
	@JoinColumn(name="room_id")
	private Apartment apartment;

	public Facility() {
	}
	
	

	public Facility( boolean aircondition, boolean elevator, boolean heating, boolean kitchen,
			boolean parking, boolean tv, boolean wifi) {

		this.aircondition = aircondition;
		this.elevator = elevator;
		this.heating = heating;
		this.kitchen = kitchen;
		this.parking = parking;
		this.tv = tv;
		this.wifi = wifi;
	}



	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public boolean getAircondition() {
		return this.aircondition;
	}

	public void setAircondition(boolean aircondition) {
		this.aircondition = aircondition;
	}

	public boolean getElevator() {
		return this.elevator;
	}

	public void setElevator(boolean elevator) {
		this.elevator = elevator;
	}

	public boolean getHeating() {
		return this.heating;
	}

	public void setHeating(boolean heating) {
		this.heating = heating;
	}

	public boolean getKitchen() {
		return this.kitchen;
	}

	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}

	public boolean getParking() {
		return this.parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean getTv() {
		return this.tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean getWifi() {
		return this.wifi;
	}

	public void setWifi(boolean wifi) {
		this.wifi = wifi;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}