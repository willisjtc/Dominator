package com.xalero.dominion.events;

public class ProtocolEvent {
	private EventKey event;
	private String value;
	
	public ProtocolEvent(String key, String value) {
		this.event = EventKey.valueOf(key);
		this.value = value;
	}	
	public ProtocolEvent(EventKey key, String value) {
		this.event = key;
		this.value = value;
	}

	public EventKey getEvent() {
		return event;
	}
	public String getEventName() {
		return event.toString();
	}
	public void setKey(String key) {
		this.event = EventKey.valueOf(key);
	}
	public void setKey(EventKey event) {
		this.event = event;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
