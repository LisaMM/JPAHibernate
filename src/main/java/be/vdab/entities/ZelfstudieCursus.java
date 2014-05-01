package be.vdab.entities;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the zelfstudiecursussen database table.
 * 
 */
@Entity
@Table(name="Zelfstudiecursussen")

public class ZelfstudieCursus extends Cursus implements Serializable {
	private static final long serialVersionUID = 1L;
	private int duurtijd;

	protected ZelfstudieCursus() {
	}
	
	public int getDuurtijd() {
		return this.duurtijd;
	}

	public void setDuurtijd(int duurtijd) {
		this.duurtijd = duurtijd;
	}
}
