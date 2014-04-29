package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the cursussen database table.
 * 
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public abstract class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "CursusGenerator",
	table = "hoogstevolgnrs",
	pkColumnName = "Naam",
	valueColumnName = "HoogsteVolgNr",
	pkColumnValue = "CursussenNr ")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CursusGenerator")
	@Id
	private long cursusNr;
	private String naam;

	protected Cursus() {
	}

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