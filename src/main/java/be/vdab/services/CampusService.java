package be.vdab.services;

import be.vdab.dao.*;
import be.vdab.entities.*;
import be.vdab.exceptions.*;

public class CampusService {
	private final CampusDAO campusDAO = new CampusDAO();
	private final ManagerDAO managerDAO = new ManagerDAO();

	public Campus read(long campusNr) {
		return campusDAO.read(campusNr);
	}

	public void create(Campus campus) {
		campusDAO.beginTransaction();
		campusDAO.create(campus);
		campusDAO.commit();
	}

	public void delete(long campusNr) {
		campusDAO.beginTransaction();
		campusDAO.delete(campusNr);
		campusDAO.commit();
	}

	public Iterable<Campus> findByGemeente(String gemeente) {
		return campusDAO.findByGemeente(gemeente);
	}

	public Iterable<Campus> findAll() {
		return campusDAO.findAll();
	}

	public void kenManagerToe(long campusNr, long managerNr) {
		campusDAO.beginTransaction();
		Campus campus = campusDAO.read(campusNr);
		if (campus == null) {
			throw new CampusNietGevondenException();
		}
		Manager manager = managerDAO.read(managerNr);
		if (manager == null) {
			throw new ManagerNietGevondenException();
		}
		campus.setManager(manager);
		campusDAO.commit();
	}
}
