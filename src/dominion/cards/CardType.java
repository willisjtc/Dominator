package dominion.cards;

public enum CardType {
	Treasure (1),
	Action (2),
	Reaction (3),
	ActionReaction (4),
	Attack (5),
	Victory (6);
	
	private int id;
	
	CardType(int id) {
		this.id = id;
	}
	
	public static CardType getCardTypeById(int id) {
		return CardType.values()[id];
	}
	
	public int getId() {
		return id;
	}
}