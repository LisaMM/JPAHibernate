package be.vdab.util;

public class VoornaamInfo {
	private final long aantal;
	private final String voornaam;

	public VoornaamInfo(String voornaam, long aantal) {
		this.aantal = aantal;
		this.voornaam = voornaam;
	}

	public long getAantal() {
		return aantal;
	}

	public String getVoornaam() {
		return voornaam;
	}
	
	@Override
	public String toString() {
		return String.format("%s:%d", voornaam, aantal);
	}

}
