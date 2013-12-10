package dominion.application.model;

public enum CardSet {
	RANDOM (1, "Random"),
	CUSTOM (2, "Custom");
	
	private int id;
	private String displayName;
	
	private CardSet(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}
	
	public String toString() {
		return displayName;
	}
}
