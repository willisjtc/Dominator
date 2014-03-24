package com.xalero.dominion.events;

public class DominionMessage {
	private DominionEvent event;
	private String value;
	
	public DominionMessage(String key, String value) {
		this.event = DominionEvent.valueOf(key);
		this.value = value;
	}	
	public DominionMessage(DominionEvent key, String value) {
		this.event = key;
		this.value = value;
	}

	public DominionEvent getEvent() {
		return event;
	}
	public String getEventName() {
		return event.toString();
	}
	public void setKey(String key) {
		this.event = DominionEvent.valueOf(key);
	}
	public void setKey(DominionEvent event) {
		this.event = event;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
