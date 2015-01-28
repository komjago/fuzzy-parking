package fuzzypark.control;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.LinkedBlockingQueue;

import fuzzypark.events.Event;
import fuzzypark.view.View;

public class Controller implements Runnable{
	View view;
	private TimerTask timerTask;
	private Timer timer;
	LinkedBlockingQueue<Event> eventQ;
	public Controller(LinkedBlockingQueue<Event> eventQ, View view) {
		this.view = view;
		this.eventQ=eventQ;
	}

	@Override
	public void run() {
		timerTask=new TimerEventTask(eventQ);
		timer = new Timer();
		timer.schedule(timerTask,40,40);
		while(true)
		{
			Event event;
			try {
				event = eventQ.take();
			} 
			catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			if(event != null)
				if(view.panel.simmode)
					view.sim();
				else 
					eventQ.clear();
					
		}
	}
}
