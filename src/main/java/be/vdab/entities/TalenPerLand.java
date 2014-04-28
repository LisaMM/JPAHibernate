package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import be.vdab.valuepbjects.LandTaal;

/**
 * Entity implementation class for Entity: TalenPerLand
 *
 */
@Entity
@IdClass(LandTaal.class)
public class TalenPerLand implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private String landCode;   
	@Id
	private String taalCode;
	private int aantalSprekers;

	public TalenPerLand() {
		super();
	}
	
	public String getLandCode() {
		return this.landCode;
	}

	public void setLandCode(String landCode) {
		this.landCode = landCode;
	}
	
	public String getTaalCode() {
		return this.taalCode;
	}

	public void setTaalCode(String taalCode) {
		this.taalCode = taalCode;
	}
	
	public int getAantalSprekers() {
		return this.aantalSprekers;
	}

	public void setAantalSprekers(int aantalSprekers) {
		this.aantalSprekers = aantalSprekers;
	}
   
}
