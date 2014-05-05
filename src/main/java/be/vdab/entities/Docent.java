package be.vdab.entities;

import java.io.Serializable;
import java.math.*;
import java.util.*;
import javax.persistence.*;
import be.vdab.enums.Geslacht;
import be.vdab.valueobjects.EmailAdres;

/**
 * Entity implementation class for Entity: Docent
 * 
 */
@Entity
@Table(name = "docenten")
// @NamedQuery (name = "Docent.findByWeddeBetween", query =
// "select d from Docent d where d.wedde between :van and :tot order by d.wedde,d.docentNr")
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long docentNr;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private Geslacht geslacht;
	@ElementCollection
	@CollectionTable(name = "docentenbijnamen", joinColumns = @JoinColumn(name = "docentNr"))
	@Column(name = "Bijnaam")
	private Set<String> bijnamen;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CampusNr")
	private Campus campus;
	@Embedded
	private EmailAdres emailAdres;
	@ManyToMany(mappedBy = "docenten")
	private Set<Verantwoordelijkheid> verantwoordelijkheden;

	protected Docent() {
	}

	public Docent(String voornaam, String familienaam, BigDecimal wedde,
			Geslacht geslacht, EmailAdres email) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
		bijnamen = new HashSet<>();
		setEmailAdres(email);
		verantwoordelijkheden = new LinkedHashSet<>();
	}

	public void setEmailAdres(EmailAdres email) {
		emailAdres = email;
	}

	public EmailAdres getEmailAdres() {
		return emailAdres;
	}

	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage
				.divide(new BigDecimal(100)));
		wedde = wedde.multiply(factor).setScale(2, RoundingMode.HALF_UP);
	}

	public String getNaam() {
		return String.format("%s %s", voornaam, familienaam);
	}

	@Override
	public String toString() {
		return String.format("%d:%s %s", docentNr, voornaam, familienaam);
	}

	public long getDocentNr() {
		return this.docentNr;
	}

	public String getVoornaam() {
		return this.voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}

	public String getFamilienaam() {
		return this.familienaam;
	}

	public void setFamilienaam(String familienaam) {
		this.familienaam = familienaam;
	}

	public BigDecimal getWedde() {
		return this.wedde;
	}

	public void setWedde(BigDecimal wedde) {
		this.wedde = wedde;
	}

	public Campus getCampus() {
		return campus;
	}

	public void setCampus(Campus campus) {
		if (this.campus != null && this.campus.getDocenten().contains(this)) {
			this.campus.removeDocent(this);
		}
		this.campus = campus;
		if (campus != null && !campus.getDocenten().contains(this)) {
			campus.addDocent(this);
		}
	}

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}

	public Set<String> getBijnamen() {
		return Collections.unmodifiableSet(bijnamen);
	}

	public void addBijnaam(String bijnaam) {
		bijnamen.add(bijnaam);
	}

	public void removeBijnaam(String bijnaam) {
		bijnamen.remove(bijnaam);
	}

	public void addVerantwoordelijkheid(Verantwoordelijkheid verantwoordelijkheid) {
		verantwoordelijkheden.add(verantwoordelijkheid);
		if (!verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.addDocent(this);
		}
	}

	public void removeVerantwoordelijkheid(Verantwoordelijkheid verantwoordelijkheid) {
		verantwoordelijkheden.remove(verantwoordelijkheid);
		if (verantwoordelijkheid.getDocenten().contains(this)) {
			verantwoordelijkheid.removeDocent(this);
		}
	}

	public Set<Verantwoordelijkheid> getVerantwoordelijkheden() {
		return Collections.unmodifiableSet(verantwoordelijkheden);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Docent)) {
			return false;
		}
		return ((Docent) obj).emailAdres.equals(this.emailAdres);
	}

	@Override
	public int hashCode() {
		return emailAdres.hashCode();
	}
}
