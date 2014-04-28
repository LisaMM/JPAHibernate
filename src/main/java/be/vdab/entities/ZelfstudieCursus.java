package be.vdab.entities;

import be.vdab.entities.Cursus;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ZelfstudieCursus
 *
 */
@Entity
@Table(name="ZelfstudieCursussen")

public class ZelfstudieCursus extends Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int duurtijd;

	protected ZelfstudieCursus() {}   
	
	public int getDuurtijd() {
		return this.duurtijd;
	}

	public void setDuurtijd(int duurtijd) {
		this.duurtijd = duurtijd;
	}
   
}
