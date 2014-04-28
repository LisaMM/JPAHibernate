package be.vdab.valuepbjects;

import java.io.Serializable;

public class LandTaal implements Serializable {
	private static final long serialVersionUID = 1L;
	private String landCode;
	private String taalCode;

	public LandTaal() {}
	
	public LandTaal(String landCode, String taalCode) {
		this.landCode = landCode;
		this.taalCode = taalCode;
	}
	
	@Override
	public int hashCode() {
		return (landCode + taalCode).toUpperCase().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof LandTaal)) {
			return false;
		}
		LandTaal landTaal = (LandTaal) o;
		return ((landTaal.landCode + landTaal.taalCode)
				.equalsIgnoreCase(landCode + taalCode));
	}
} 
