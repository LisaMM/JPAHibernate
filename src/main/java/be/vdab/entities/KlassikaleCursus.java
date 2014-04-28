package be.vdab.entities;

import be.vdab.entities.Cursus;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: KlassikaleCursus
 *
 */
@Entity
@Table(name="KlassikaleCursussen")

public class KlassikaleCursus extends Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date van;
	@Temporal(TemporalType.DATE)
	private Date tot;

	protected KlassikaleCursus() {}   
	
	public Date getVan() {
		return this.van;
	}

	public void setVan(Date van) {
		this.van = van;
	}   
	
	public Date getTot() {
		return this.tot;
	}

	public void setTot(Date tot) {
		this.tot = tot;
	}
   
}
