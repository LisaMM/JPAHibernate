package be.vdab.theorievoorbeelden;

public class Main {
	public static final ThreadLocal<String> stripNamen = new ThreadLocal<>();
	public static void main(String[] args) throws InterruptedException {
		
		EenThread thread1 = new EenThread("Thread1", "Asterix");
		EenThread thread2 = new EenThread("Thread2", "Obelix");
		thread1.start();
		thread2.start();
		thread1.join(); // uitvoering main() pauzeren tot thread1 is afgewerkt
		thread2.join(); // uitvoering main() pauzeren tot thread2 is afgewerkt
	}
}
