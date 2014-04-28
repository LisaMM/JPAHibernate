package be.vdab.dao;

import javax.persistence.EntityManager;

import be.vdab.entities.Docent;

public class DocentDAO extends AbstractDAO {

	public Docent read(long docentNr) {
		return getEntityManager().find(Docent.class, docentNr);
	}

	public void create(Docent docent) {
		getEntityManager().persist(docent);
	}

	public void delete(long docentNr) {
		EntityManager entityManager = getEntityManager();
		Docent docent = entityManager.find(Docent.class, docentNr);
		if (docent != null) {
			entityManager.remove(docent);
		}
	}
}
