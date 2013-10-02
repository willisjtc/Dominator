package dominion.cards.expansion;

public enum Expansions {
	original (1),
	intrigue (2),
	prosperity (3);
	
	private int id;
	
	Expansions (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
