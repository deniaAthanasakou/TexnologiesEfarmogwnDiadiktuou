package Entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the freedates database table.
 * 
 */
@Entity
@Table(name="freedates")
@NamedQuery(name="Freedate.findAll", query="SELECT f FROM Freedate f")
public class Freedate implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="room_id")
	private int roomId;

	@Temporal(TemporalType.DATE)
	private Date from;

	@Temporal(TemporalType.DATE)
	private Date to;

	//bi-directional one-to-one association to Apartment
	@OneToOne
	@JoinColumn(name="room_id")
	private Apartment apartment;

	public Freedate() {
	}
	
	

	public Freedate(Date from, Date to) {
		this.from = from;
		this.to = to;
	}



	public int getRoomId() {
		return this.roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public Date getFrom() {
		return this.from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return this.to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public Apartment getApartment() {
		return this.apartment;
	}

	public void setApartment(Apartment apartment) {
		this.apartment = apartment;
	}

}