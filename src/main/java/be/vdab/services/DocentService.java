package be.vdab.services;

import java.math.BigDecimal;
import java.util.Collections;

import be.vdab.dao.*;
import be.vdab.entities.*;
import be.vdab.exceptions.*;
import be.vdab.util.VoornaamInfo;

public class DocentService {
	private final DocentDAO docentDAO = new DocentDAO();
	private final CampusDAO campusDAO = new CampusDAO();

	public Docent read(long docentNr) {
		return docentDAO.read(docentNr);
	}

	public void create(Docent docent) {
		if (docentDAO.findByEmailAdres(docent.getEmailAdres()) != null) {
			throw new EmailAdresAlInGebruikException();
		}
		docentDAO.beginTransaction();
		docentDAO.create(docent);
		docentDAO.commit();
	}

	public void delete(long docentNr) {
		docentDAO.beginTransaction();
		docentDAO.delete(docentNr);
		docentDAO.commit();
	}

	public void opslag(long docentNr, BigDecimal percentage) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		docent.opslag(percentage);
		docentDAO.commit();
	}

	public Iterable<Docent> findByWedde(BigDecimal van, BigDecimal tot,
			int vanafRij, int aantalRijen) {
		return docentDAO.findByWeddeBetween(van, tot, vanafRij, aantalRijen);
	}

	public Iterable<Docent> findByFamilienaamEnCampus(String beginFamilienaam,
			long campusNr) {
		Campus campus = campusDAO.read(campusNr);
		if (campus == null) {
			return Collections.emptyList();
		}
		return docentDAO.findByFamilienaamEnCampus(beginFamilienaam, campus);
	}

	public Iterable<VoornaamInfo> findVoornamen() {
		return docentDAO.findVoornamen();
	}

	public BigDecimal findMaxWedde() {
		return docentDAO.findMaxWedde();
	}

	public int algemeneOpslag(BigDecimal percentage, BigDecimal totEnMetWedde) {
		BigDecimal factor = BigDecimal.ONE.add(percentage
				.divide(new BigDecimal(100)));
		docentDAO.beginTransaction();
		int aantalDocentenAangepast = docentDAO.algemeneOpslag(factor,
				totEnMetWedde);
		docentDAO.commit();
		return aantalDocentenAangepast;
	}

	public void bijnaamToevoegen(long docentNr, String bijnaam) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		docent.addBijnaam(bijnaam);
		docentDAO.commit();
	}

	public void bijnamenVerwijderen(long docentNr, String[] bijnamen) {
		docentDAO.beginTransaction();
		Docent docent = docentDAO.read(docentNr);
		if (docent == null) {
			throw new DocentNietGevondenException();
		}
		for (String bijnaam : bijnamen) {
			docent.removeBijnaam(bijnaam);
		}
		docentDAO.commit();
	}
}
