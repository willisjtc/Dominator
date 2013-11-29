package dominion.application.model;

public enum PlayerOptions {
	NONE (0, "None"),
	HUMAN (1, "Human"),
	COMPUTER (2, "Computer");
	
	int id;
	String displayName;
	
	PlayerOptions(int id, String displayName) {
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
