package be.vdab.services;

import be.vdab.dao.CampusDAO;
import be.vdab.entities.Campus;

public class CampusService {
	private final CampusDAO campusDAO = new CampusDAO();

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
}
