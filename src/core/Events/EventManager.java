package core.Events;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
	List<EventHandler> handler = new ArrayList<EventHandler>();
	
	public void fire(Event event) {
		for(EventHandler hand : handler) {
			hand.Handle(event);
		}
	}
	
	public void registerHandler(EventHandler handl) {
		handler.add(handl);
	}
}