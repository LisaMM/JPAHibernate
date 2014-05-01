package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the klassikalecursussen database table.
 * 
 */
@Entity
@Table(name="Klassikalecursussen")

public class KlassikaleCursus extends Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Temporal(TemporalType.DATE)
	private Date tot;
	@Temporal(TemporalType.DATE)
	private Date van;

	protected KlassikaleCursus() {
	}

	public Date getTot() {
		return this.tot;
	}

	public void setTot(Date tot) {
		this.tot = tot;
	}

	public Date getVan() {
		return this.van;
	}

	public void setVan(Date van) {
		this.van = van;
	}

}
