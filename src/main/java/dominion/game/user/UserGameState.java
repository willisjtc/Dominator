package dominion.game.user;

import dominion.cards.CardUtils;
import dominion.cards.Deck;
import dominion.cards.Discard;
import dominion.cards.Hand;

public class UserGameState {
	private Integer id;
	private Integer score;
	private Deck deck;
	private Hand hand;
	private Discard discard;
	
	public Hand getHand() {
		return hand;
	}
	public void draw() {
		if (deck.getSize() == 0) {
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
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer s) {
		score = s;
	}
	public void addVictoryPoints(int points) {
		score += points;
	}
}