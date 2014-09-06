package be.vdab.entities;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Verantwoordelijkheid
 * 
 */
@Entity
@Table(name = "verantwoordelijkheden")
public class Verantwoordelijkheid implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long verantwoordelijkheidNr;
	private String naam;

	protected Verantwoordelijkheid() {
	}

	public Verantwoordelijkheid(String naam) {
		setNaam(naam);
		docenten = new HashSet<>();
	}

	public long getVerantwoordelijkheidNr() {
		return this.verantwoordelijkheidNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Verantwoordelijkheid)) {
			return false;
		}
		return ((Verantwoordelijkheid) obj).naam.equalsIgnoreCase(this.naam);
	}

	@Override
	public int hashCode() {
		return naam.toUpperCase().hashCode();
	}

	@Override
	public String toString() {
		return String.format("%d:%s", verantwoordelijkheidNr, naam);
	}

	@ManyToMany
	@JoinTable(name = "docentenverantwoordelijkheden", joinColumns = @JoinColumn(name = "VerantwoordelijkheidNr"), inverseJoinColumns = @JoinColumn(name = "DocentNr"))
	private Set<Docent> docenten;

	public void addDocent(Docent docent) {
		docenten.add(docent);
		if (!docent.getVerantwoordelijkheden().contains(this)) {
			docent.addVerantwoordelijkheid(this);
		}
	}

	public void removeDocent(Docent docent) {
		docenten.remove(docent);
		if (docent.getVerantwoordelijkheden().contains(this)) {
			docent.removeVerantwoordelijkheid(this);
		}
	}

	public Set<Docent> getDocenten() {
		return Collections.unmodifiableSet(docenten);
	}
}
