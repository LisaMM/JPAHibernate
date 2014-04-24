package be.vdab.util;

public class VoornaamInfo {
	private final long docentNr;
	private final String voornaam;

	public VoornaamInfo(long docentNr, String voornaam) {
		this.docentNr = docentNr;
		this.voornaam = voornaam;
	}

	public long getDocentNr() {
		return docentNr;
	}

	public String getVoornaam() {
		return voornaam;
	}
	
	@Override
	public String toString() {
		return String.format("%d:%s", docentNr, voornaam);
	}

}
