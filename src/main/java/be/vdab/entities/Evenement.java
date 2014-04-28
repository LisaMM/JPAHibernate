package be.vdab.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Evenement
 *
 */
@Entity
@Table(name="evenementen")

public class Evenement implements Serializable {
	private static final long serialVersionUID = 1L;
	   
	@Id
	@TableGenerator(name = "EvenementGenerator", table = "hoogstevolgnrs", 
		pkColumnName = "Naam", valueColumnName = "HoogsteVolgNr",
		pkColumnValue = "EvenementNr")
	@GeneratedValue(strategy = GenerationType.TABLE, 
		generator = "EvenementGenerator")
	private long id;
	@Temporal(TemporalType.DATE)
	private Date datum;
	private String titel;

	public Evenement() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}   
	public String getTitel() {
		return this.titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}
   
}
