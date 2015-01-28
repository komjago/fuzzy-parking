package fuzzypark;

import java.util.concurrent.LinkedBlockingQueue;

import fuzzypark.control.Controller;
import fuzzypark.events.Event;
import fuzzypark.view.View;


/**
 * Parking Sim with fuzzy logic
 * @author J.Komjago
 */
public class FuzzyParking {
	public static Thread thread;
	
	/**
	 * MAIN 
	 * @param args
	 */
	public static void main(String[] args){
		LinkedBlockingQueue<Event> eventQ = new LinkedBlockingQueue<Event>();
		View view = new View(eventQ);
		Controller control = new Controller(eventQ,view);
		control.run();
	}
}
