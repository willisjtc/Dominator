package com.xalero.dominion.model;

public enum PlayerType {
	NONE (0, "None"),
	HUMAN (1, "Human"),
	COMPUTER (2, "Computer");
	
	int id;
	String displayName;
	
	PlayerType(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;  
	}
	
	public int getId() {
		return id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String toString() {
		return displayName;
	}
}
