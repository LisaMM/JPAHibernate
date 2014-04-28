package be.vdab.entities;

import java.io.Serializable;
import java.lang.String;

/**
 * ID class for entity: TalenPerLand
 *
 */ 
public class TalenPerLandPK  implements Serializable {   
   
	         
	private String landCode;         
	private String taalCode;
	private static final long serialVersionUID = 1L;

	public TalenPerLandPK() {}

	

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
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof TalenPerLandPK)) {
			return false;
		}
		TalenPerLandPK other = (TalenPerLandPK) o;
		return true
			&& (getLandCode() == null ? other.getLandCode() == null : getLandCode().equals(other.getLandCode()))
			&& (getTaalCode() == null ? other.getTaalCode() == null : getTaalCode().equals(other.getTaalCode()));
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getLandCode() == null ? 0 : getLandCode().hashCode());
		result = prime * result + (getTaalCode() == null ? 0 : getTaalCode().hashCode());
		return result;
	}
   
   
}
