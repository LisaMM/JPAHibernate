package be.vdab.entities;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import be.vdab.valueobjects.*;

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
	@ElementCollection
	@CollectionTable(name = "campussentelefoonnrs", joinColumns = @JoinColumn(name = "campusNr"))
	@OrderBy("fax")
	private Set<TelefoonNr> telefoonNrs;
	@OneToMany
	@OrderBy("voornaam, familienaam")
	private Set<Docent> docenten;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ManagerNr")
	private Manager manager;

	public Campus(String naam, Adres adres) {
		setNaam(naam);
		setAdres(adres);
		telefoonNrs = new LinkedHashSet<>();
		docenten = new LinkedHashSet<>();
	}

	protected Campus() {
	}

	public Set<TelefoonNr> getTelefoonNrs() {
		return Collections.unmodifiableSet(telefoonNrs);
	}

	public void addTelefoonNr(TelefoonNr telefoonNr) {
		telefoonNrs.add(telefoonNr);
	}

	public void removeTelefoonNr(TelefoonNr telefoonNr) {
		telefoonNrs.remove(telefoonNr);
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

	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		if (manager != this.manager) {
			Manager vorigeManager = this.manager;
			this.manager = manager;
			if (vorigeManager != null && vorigeManager.getCampus() == this) {
				vorigeManager.setCampus(null);
			}
			if (manager != null && manager.getCampus() != this) {
				this.manager.setCampus(this);
			}
		}
	}

	public void addDocent(Docent docent) {
		docenten.add(docent);
		if (docent.getCampus() != this) {
			docent.setCampus(this);
		}
	}

	public void removeDocent(Docent docent) {
		docenten.remove(docent);
		if (docent.getCampus() == this) {
			docent.setCampus(null);
		}
	}

}