package be.vdab.theorievoorbeelden;
public class EenThread extends Thread {

	private final String stripNaam;

	public EenThread(String threadNaam, String stripNaam) {
		super(threadNaam); // naam thread doorgeven aan Thread constructor
		this.stripNaam = stripNaam;
	}

	@Override
	public void run() {
		Main.stripNamen.set(stripNaam);
		System.out.println(String.format("Thread %s:%s", this.getName(),
				Main.stripNamen.get()));
		Main.stripNamen.remove();
	}
}
