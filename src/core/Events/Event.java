package core.Events;

public class Event {
	public EventType type = EventType.Unknown;
	
	
	public enum EventType {
		Exit,
		Unknown
	}
}