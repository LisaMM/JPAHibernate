package be.vdab.services;

import java.math.BigDecimal;

import be.vdab.dao.DocentDAO;
import be.vdab.entities.Docent;
import be.vdab.exceptions.DocentNietGevondenException;
import be.vdab.filters.JPAFilter;

import javax.persistence.EntityManager;

public class DocentService {
	private final DocentDAO docentDAO = new DocentDAO();

	public Docent read(long docentNr) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return docentDAO.read(docentNr, entityManager);
		} finally {
			entityManager.close();
		}
	}

	public void create(Docent docent) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			docentDAO.create(docent, entityManager);
			entityManager.getTransaction().commit();
		} catch (RuntimeException ex) {
			entityManager.getTransaction().rollback();
		} finally {
			entityManager.close();
		}
	}

	public void delete(long docentNr) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			docentDAO.delete(docentNr, entityManager);
			entityManager.getTransaction().commit();
		} catch (RuntimeException ex) {
			entityManager.getTransaction().rollback();
			throw ex;
		} finally {
			entityManager.close();
		}
	}

	public void opslag(long docentNr, BigDecimal percentage) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			Docent docent = docentDAO.read(docentNr, entityManager);
			if (docent == null) {
				throw new DocentNietGevondenException();
			}
			docent.opslag(percentage);
			entityManager.getTransaction().commit();
		} catch (RuntimeException ex) {
			entityManager.getTransaction().rollback();
			throw ex;
		} finally {
			entityManager.close();
		}
	}
}
