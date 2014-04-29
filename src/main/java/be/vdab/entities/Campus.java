package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.*;

import be.vdab.valueobjects.Adres;

/**
 * The persistent class for the campussen database table.
 * 
 */
@Entity
@Table(name = "campussen")
public class Campus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long campusNr;
	private String naam;
	@Embedded
	private Adres adres;

	public Campus(String naam, Adres adres) {
		setNaam(naam);
		setAdres(adres);
	}

	protected Campus() {
	}

	public long getCampusNr() {
		return this.campusNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

}