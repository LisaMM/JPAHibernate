package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Manager
 *
 */
@Entity
@Table(name = "managers")
public class Manager implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@GeneratedValue
	private long managerNr;
	private String voornaam;
	private String familienaam;
	@OneToOne(
		fetch = FetchType.LAZY,
		mappedBy = "manager")
	private Campus campus;

	protected Manager() {}
	
	public Manager(String voornaam, String familienaam) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
	}
	
	public long getManagerNr() {
		return this.managerNr;
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
	
	public Campus getCampus() {
		return this.campus;
	}

	public void setCampus(Campus campus) {
		if (campus != this.campus) {
			Campus vorigeCampus = this.campus;
			this.campus = campus;
			if (vorigeCampus != null && vorigeCampus.getManager() == this) {
				vorigeCampus.setManager(null);
			}
			if (campus != null && campus.getManager() != this) {
				campus.setManager(this);
			}
		}
	}
   
	@Override
	public String toString() {
		return String.format("%d:%s %s", managerNr, voornaam, familienaam);
	}
}
