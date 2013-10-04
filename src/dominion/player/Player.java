package dominion.player;

import java.util.List;

import dominion.cards.Card;

public class Player {
	private String name;
	private Integer score;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discard;
	
	public List<Card> getHand() {
		return hand;
	}
	
	public void draw() {
		if (deck.size() == 0) {
			Card.shuffle(deck, discard);
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

	public String getName() {
		return name;
	}
	public void setName(String n) {
		name = n;
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
