package be.vdab.dao;

import javax.persistence.*;
import be.vdab.entities.Campus;

public class CampusDAO extends AbstractDAO {

	public Campus read(long campusNr) {
		return getEntityManager().find(Campus.class, campusNr);
	}

	public void create(Campus campus) {
		getEntityManager().persist(campus);
	}

	public void delete(long campusNr) {
		EntityManager entityManager = getEntityManager();
		Campus campus = entityManager.find(Campus.class, campusNr);
		if (campus != null) {
			entityManager.remove(campus);
		}
	}

	public Iterable<Campus> findByGemeente(String gemeente) {
		TypedQuery<Campus> query = getEntityManager().createNamedQuery(
				"Campus.findByGemeente", Campus.class);
		query.setParameter("gemeente", gemeente);
		return query.getResultList();
	}

	public Iterable<Campus> findAll() {
		TypedQuery<Campus> query = getEntityManager().createNamedQuery(
				"Campus.findAll", Campus.class);
		return query.getResultList();
	}
}
