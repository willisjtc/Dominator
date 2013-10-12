package dominion.cards.trash;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dominion.cards.Card;

public class Trash {
	private List<Card> cards;
	
	public Trash() {
		cards = new ArrayList<Card>();
	}
	
	public void addCard(Card c) {
		cards.add(c);
	}

	public Collection<Card> getTrashCards() {
		return cards;
	}
}
