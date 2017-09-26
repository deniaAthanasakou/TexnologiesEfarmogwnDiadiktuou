package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rule database table.
 * 
 */
@Entity
@NamedQuery(name="Rule.findAll", query="SELECT r FROM Rule r")
public class Rule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private String roomId;

	private boolean events;

	@Column(name="min_days_booking")
	private int minDaysBooking;

	@Column(name="pets_allowed")
	private boolean petsAllowed;

	@Column(name="smoking_allowed")
	private boolean smokingAllowed;

	public Rule() {
	}
	
	

	public Rule(boolean events, int minDaysBooking, boolean petsAllowed, boolean smokingAllowed) {
		super();
		this.events = events;
		this.minDaysBooking = minDaysBooking;
		this.petsAllowed = petsAllowed;
		this.smokingAllowed = smokingAllowed;
	}



	public String getRoomId() {
		return this.roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public boolean getEvents() {
		return this.events;
	}

	public void setEvents(boolean events) {
		this.events = events;
	}

	public int getMinDaysBooking() {
		return this.minDaysBooking;
	}

	public void setMinDaysBooking(int minDaysBooking) {
		this.minDaysBooking = minDaysBooking;
	}

	public boolean getPetsAllowed() {
		return this.petsAllowed;
	}

	public void setPetsAllowed(boolean petsAllowed) {
		this.petsAllowed = petsAllowed;
	}

	public boolean getSmokingAllowed() {
		return this.smokingAllowed;
	}

	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

}