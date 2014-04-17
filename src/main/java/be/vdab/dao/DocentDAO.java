package be.vdab.dao;

import javax.persistence.EntityManager;
import be.vdab.entities.Docent;
import be.vdab.filters.JPAFilter;

public class DocentDAO {
	
	public Docent read(long docentNr) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return entityManager.find(Docent.class,  docentNr);
		} finally {
			entityManager.close();
		}
	}
}
