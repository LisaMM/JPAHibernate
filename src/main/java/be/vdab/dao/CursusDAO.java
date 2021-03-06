package be.vdab.dao;

import javax.persistence.TypedQuery;

import be.vdab.entities.Cursus;

public class CursusDAO extends AbstractDAO {
	public Iterable<Cursus> findByNaamContains(String woord) {
		TypedQuery<Cursus> query = getEntityManager().createNamedQuery(
				"Cursus.findByNaamContains", Cursus.class);
		query.setParameter("zoals", String.format("%%%s%%", woord));
		// %% staat voor 1 letterlijk % teken
		return query.getResultList();
	}
}
