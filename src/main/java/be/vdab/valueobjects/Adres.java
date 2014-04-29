package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;
	private String straat;
	private String huisNr;
	private String postcode;
	private String gemeente;

	public Adres(String straat, String huisNr, String postcode,
			String gemeente) {
		this.straat = straat;
		this.postcode = postcode;
		this.huisNr = huisNr;
		this.gemeente = gemeente;
	}

	protected Adres() {
	}

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getgemeente() {
		return gemeente;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s %s", straat, huisNr, 
				postcode, gemeente);
	}
}
