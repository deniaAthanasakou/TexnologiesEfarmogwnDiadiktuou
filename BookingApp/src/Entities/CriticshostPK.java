package Entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the criticshost database table.
 * 
 */
@Embeddable
public class CriticshostPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private java.util.Date date;

	@Column(name="host_id", insertable=false, updatable=false)
	private int hostId;

	@Column(name="tenant_id", insertable=false, updatable=false)
	private int tenantId;

	public CriticshostPK() {
	}
	public java.util.Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public int getHostId() {
		return this.hostId;
	}
	public void setHostId(int hostId) {
		this.hostId = hostId;
	}
	public int getTenantId() {
		return this.tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof CriticshostPK)) {
			return false;
		}
		CriticshostPK castOther = (CriticshostPK)other;
		return 
			this.date.equals(castOther.date)
			&& (this.hostId == castOther.hostId)
			&& (this.tenantId == castOther.tenantId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.date.hashCode();
		hash = hash * prime + this.hostId;
		hash = hash * prime + this.tenantId;
		
		return hash;
	}
	public CriticshostPK(Date date, int hostId, int tenantId) {
		super();
		this.date = date;
		this.hostId = hostId;
		this.tenantId = tenantId;
	}
	
}