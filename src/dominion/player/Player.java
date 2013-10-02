package dominion.player;

import java.util.List;

import dominion.cards.Card;
import dominion.cards.CardUtils;

public class Player {
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;
	
	public void draw() {
		if (deck.size() == 0) {
			CardUtils.shuffle(deck, discard);
		}
		hand.add(deck.get(0));
		deck.remove(0);
	}
	
	public void draw(int numCards) {
		// check for amount of cards in deck
		for (int i = 0; i < numCards; i++) {
			hand.add(deck.get(0));
			deck.remove(0);
		}
	}
}
