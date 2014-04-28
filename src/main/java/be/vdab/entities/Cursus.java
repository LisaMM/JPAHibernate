package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Cursus
 *
 */
@Entity
@Table(name="cursussen")

public class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private long cursusNr;
	private String naam;

	protected Cursus() {}   
	
	public long getCursusNr() {
		return this.cursusNr;
	}

	public void setCursusNr(long cursusNr) {
		this.cursusNr = cursusNr;
	}   
	
	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%s", cursusNr, naam);
	}
}
