package com.xalero.dominion.events;

public enum EventKey {
	DISPLAY ("display"),
	DOMINION_MODEL ("dominion_model");
	
	private String name;
	
	EventKey(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
