package be.vdab.dao;

import java.math.BigDecimal;

import javax.persistence.*;

import be.vdab.entities.Docent;
import be.vdab.util.VoornaamInfo;

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
	
	public Iterable<Docent> findByWeddeBetween(BigDecimal van, BigDecimal tot, 
			int vanafRij, int aantalRijen) {
		TypedQuery<Docent> query = getEntityManager().createQuery(
				"select d from Docent d where d.wedde between :van and :tot "
				+ "order by d.wedde, d.docentNr", Docent.class);
		query.setParameter("van", van);
		query.setParameter("tot", tot);
		query.setFirstResult(vanafRij);
		query.setMaxResults(aantalRijen);
		return query.getResultList();
	}
	
	public Iterable<VoornaamInfo> findVoornamen() {
		TypedQuery<VoornaamInfo> query = getEntityManager().createQuery(
				"select new be.vdab.util.VoornaamInfo(d.voornaam, count(d)) "
				+ "from Docent d group by d.voornaam", VoornaamInfo.class);
		return query.getResultList();
	}
	
	public BigDecimal findMaxWedde() {
		TypedQuery<BigDecimal> query = getEntityManager().createQuery(
				"select max(d.wedde) from Docent d", BigDecimal.class);
		return query.getSingleResult();
	}
}
