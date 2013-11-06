package dominion.application.model;

public enum DashboardOptions {
	PROFILE_MANAGEMENT (1, "Profile Management"),
	SINGLE_PLAYER (2, "Single Player"),
	MULTIPLAYER (3, "Multiplayer");
	
	int id;
	String displayName;
	
	DashboardOptions(int id, String displayName) {
		this.id = id;
		this.displayName = displayName;
	}

	public int getId() {
		return id;
	}

	public String getDisplayName() {
		return displayName;
	}
}
