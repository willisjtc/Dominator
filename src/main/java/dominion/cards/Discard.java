package dominion.cards;

import java.util.List;

public class Discard {
	private Integer id;
	private List<Card> cards;
	
	public void add(Card card) {
		cards.add(card);
	}
	
	public void remove(Card card) {
		cards.remove(card);
	}
	
	public int getSize() {
		return cards.size();
	}
}
