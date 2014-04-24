package be.vdab.dao;

import java.math.BigDecimal;
import javax.persistence.*;
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
	
	public Iterable<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot) {
		TypedQuery<Docent> query = getEntityManager().createQuery(
				"select d from Docent d", Docent.class);
		return query.getResultList();
	}
}
