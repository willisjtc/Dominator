package com.xalero.dominion.controller.settings;

public enum CardSet {
	RANDOM ("Random"),
	CUSTOM ("Custom");
	
	private String displayName;
	
	private CardSet(String displayName) {
		this.displayName = displayName;
	}
	
	public String toString() {
		return displayName;
	}
}
