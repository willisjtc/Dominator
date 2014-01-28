/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.game;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import dominion.application.model.PlayerType;
import dominion.application.model.SimplePlayerInfo;
import dominion.cards.Card;
import dominion.cards.CardUtils;
import dominion.game.user.User;

/**
 * 
 * @author jonathan
 */
public class Player {
	private User user;
	private int score;
	private PlayerType playerType;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discardPile;

	public Player(SimplePlayerInfo playerInfo) {
		this.user = playerInfo.getUser();

		this.playerType = playerInfo.getPlayerType();

		this.deck = new LinkedList<>();
		this.hand = new LinkedList<>();
		this.discardPile = new LinkedList<>();
	}

	/**
	 * 
	 * @return the username associated with this player
	 */
	public String getPlayerName() {
		return user.getUsername();
	}

	/**
	 * Shuffles the players deck and clears the discard pile.
	 */
	public void shuffleDeck() {
		deck = new LinkedList<>(CardUtils.shuffle(deck, discardPile));

		discardPile.clear();
	}

	/**
	 * Adds a card to the deck.
	 * 
	 * @param card
	 *            - the card added.
	 */
	public void addToDeck(Card card) {
		deck.add(card);
	}

	/**
	 * 
	 * @param card
	 *            - the card being discarded.
	 */
	public void addToDiscard(Card card) {
		discardPile.add(card);
	}

	/**
	 * @return Whether the player is a computer or a human
	 */
	public PlayerType getPlayerType() {
		return playerType;
	}

	/**
	 * @return The current cards in the players hand.
	 */
	public Collection<Card> getHand() {
		return hand;
	}

	/**
	 * @return the current deck of the player.
	 */
	public Collection<Card> getDeck() {
		return deck;
	}

	/**
	 * @return the cards in the players discard pile.
	 */
	public Collection<Card> getDiscardPile() {
		return discardPile;
	}

	/**
	 * @return the current score of the player.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Draws a number of cards from the player's deck into the player's hand.
	 * 
	 * @param numCards
	 *            - the number of cards to draw.
	 */
	public void draw(int numCards) {
		for (int i = 0; i < numCards; i++) {
			if (deck.isEmpty()) {
				CardUtils.shuffle(deck, discardPile);
			}
			hand.add(deck.get(0));
			deck.remove(0);
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Player other = (Player) obj;
		if (user == null) {
			if (other.user != null) {
				return false;
			}
		} else if (!user.equals(other.user)) {
			return false;
		}
		return true;
	}
}
