package dominion.cards;

import java.util.Collection;
import java.util.List;

public class Hand {
	private Integer id;
	private List<Card> cards;
	
	public Collection<Card> getCards() {
		return cards;
	}
	
	public void add(Card card) {
		cards.add(card);
	}
	
	public void remove(Card card) {
		cards.remove(card);
	}
}
