package be.vdab.entities;

import java.io.Serializable;
import java.math.*;
import javax.persistence.*;
import be.vdab.enums.Geslacht;

/**
 * Entity implementation class for Entity: Docent
 *
 */
@Entity
@Table(name="docenten")
public class Docent implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private long docentNr;
	private String voornaam;
	private String familienaam;
	private BigDecimal wedde;
	private Geslacht geslacht;

	protected Docent() {}
	
	public Docent(String voornaam, String familienaam, BigDecimal wedde, Geslacht geslacht) {
		setVoornaam(voornaam);
		setFamilienaam(familienaam);
		setWedde(wedde);
		setGeslacht(geslacht);
	}   
	
	public void opslag(BigDecimal percentage) {
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(new BigDecimal(100)));
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

	public Geslacht getGeslacht() {
		return geslacht;
	}

	public void setGeslacht(Geslacht geslacht) {
		this.geslacht = geslacht;
	}
   
}
