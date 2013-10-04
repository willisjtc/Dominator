package dominion.cards.expansion;

public enum Expansion {
	original (1),
	intrigue (2),
	prosperity (3);
	
	private int id;
	
	Expansion (int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public Expansion getById(int id) {
		// TODO
		return null;
	}
}
