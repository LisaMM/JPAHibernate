package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Boek
 *
 */
@Entity
@Table(name="boeken")

public class Boek implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	private long isbnnr;
	private String titel;

	public Boek(long isbnnr, String titel) {
		this.isbnnr = isbnnr;
		this.titel = titel;
	}
	
	public Boek() {
		super();
	}
	
	public long getIsbnnr() {
		return this.isbnnr;
	}

	public void setIsbnnr(long isbnnr) {
		this.isbnnr = isbnnr;
	}   
	
	public String getTitel() {
		return this.titel;
	}
	

	public void setTitel(String titel) {
		this.titel = titel;
	}
}
