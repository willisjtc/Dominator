package dominion.cards;

import java.util.List;

public class Deck {
	private Integer id;
	private List<Card> cards;
	
	public void add(Card card) {
		cards.add(card);
	}
	
	public void remove(int index) {
		cards.remove(index);
	}
	
	public void remove(Card card) {
		cards.remove(card);
	}

	public int getSize() {
		return cards.size();
	}

	public Card get(int index) {
		return cards.get(index);
	}
}
