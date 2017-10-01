package Entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the criticshost database table.
 * 
 */
@Entity
@NamedQuery(name="Criticshost.findAll", query="SELECT c FROM Criticshost c")
public class Criticshost implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CriticshostPK id;

	private int critic;

	public Criticshost() {
	}

	public CriticshostPK getId() {
		return this.id;
	}

	public void setId(CriticshostPK id) {
		this.id = id;
	}

	public int getCritic() {
		return this.critic;
	}

	public void setCritic(int critic) {
		this.critic = critic;
	}

	public Criticshost(CriticshostPK id, int critic) {
		super();
		this.id = id;
		this.critic = critic;
	}
	
	
}