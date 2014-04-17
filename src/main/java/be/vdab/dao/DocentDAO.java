package be.vdab.dao;

import javax.persistence.EntityManager;
import be.vdab.entities.Docent;

public class DocentDAO {
	
	public Docent read(long docentNr, EntityManager entityManager) {
		return entityManager.find(Docent.class,  docentNr);
	}
	
	public void create(Docent docent, EntityManager entityManager) {
		entityManager.persist(docent);
	}
}
