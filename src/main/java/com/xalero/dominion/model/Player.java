/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardUtils;
import com.xalero.dominion.utils.Result;



/**
 * 
 * @author jonathan
 */
public class Player {
	private long uniqueIdentifier;
	private int score;
	private int buys;
	private int actions;
	private int money;
	private String displayName;
	private PlayerType playerType;
	private List<Card> deck;
	private List<Card> hand;
	private List<Card> discardPile;
	private Phase phase;
	
	private static final Logger log = LogManager.getLogManager().getLogger(Player.class.getName());

	/**
	 * @param playerInfo Player information to transfer from the
	 * controller to the model.
	 */
	public Player(SimplePlayerInfo playerInfo) {
		this.displayName = playerInfo.getDisplayName();
		this.playerType = playerInfo.getPlayerType();

		this.deck = new ArrayList<>();
		this.hand = new ArrayList<>();
		this.discardPile = new ArrayList<>();
		
		this.score = 0;
		this.buys = 0;
		this.actions = 0;
		this.money = 0;
		
		System.out.println("player constructor id: " + playerInfo.getIdentifier());
		this.uniqueIdentifier = playerInfo.getIdentifier();
	}
	
	/**
	 * Gets an id that uniquely identifies this player with 
	 * the game.
	 * @return a long representing the player's unique id
	 */
	public long getUniqueIdentifier() {
		return uniqueIdentifier;
	}
	
	/**
	 * Sets the player's phase to Action
	 */
	public void turnStarted() {
		// for debugging
		buys = 100;
		actions = 100;
		money = 100;
		
		// Correct numbers
//		buys = 1;
//		actions = 1;
		phase = Phase.ACTION;
	}
	
	/**
	 * @return the phase the player is currently in
	 */
	public Phase getPhase() {
		return phase;
	}
	
	/**
	 * ends the player's turn, sets the buys and
	 * actions, and draws the player's hand
	 */
	public void turnEnded() {
		buys = 0;
		actions = 0;
		money = 0;
		discardHand();
		draw(5);
	}
	
	/**
	 * Increment the player's buy count by one
	 */
	public void addBuy() {
		buys++;
	}
	
	/**
	 * Increases the number of buys the player has by the given amount.
	 * 
	 * @param buys the number to increase the buy count.
	 */
	public void addBuys(int buys) {
		this.buys += buys;
	}

	/**
	 * @return The number of buys the player has
	 */
	public int getBuyCount() {
		return buys;
	}
	
	/**
	 * Increment the player's action count by one.
	 */
	public void addAction() {
		actions++;
	}
	
	/**
	 * Increases the number of actions the player has by the given amount.
	 * @param actions the number to increase the action count.
	 */
	public void addActions(int actions) {
		this.actions += actions;
	}
	
	/**
	 * Removes an action from the players count of actions
	 */
	public void removeAction() {
		actions--;
	}
	
	/**
	 * @return the amount of actions the player has the left
	 */
	public int getActionCount() {
		return actions;
	}

	/**
	 * @return the amount of money the player has left
	 */
	public int getMoneyCount() {
		return money;
	}
	
	/**
	 * Determines if the player can buy the card supplied
	 * 
	 * @param card
	 * @return Result object with a true or false value of whether
	 * the player can buy the card and a message if the player cannot
	 */
	public Result canBuyCard(Card card) {
		Result result = new Result(true, "");
		if (buys == 0) {
			result.setSuccess(false);
			result.setMessage("Player is out of buys.");
			return result;
		}
		
		if (money >= card.getCost()) {
			return result;
		} else {
			result.setSuccess(false);
			result.setMessage("Player doesn't have enough money to purchase card");
			return result;
		}
	}
	
	/**
	 * Given the card, this player will buy this card and lose the money
	 * that the card costs.
	 * @param card
	 * @return A Result object for whether or not the purchase was successful
	 */
	public Result buyCard(Card card) {
		Result result = new Result(true, "");
		if (card == null) {
			result.setSuccess(false);
			result.setMessage("No more of that card");
			return result;
		}
		if (!canBuyCard(card).isSuccess()) {
			result.setSuccess(false);
			result.setMessage("Player can't buy card");
			return result;
		}
		
		phase = Phase.BUY;
		
		money -= card.getCost();
		
		if (card.isVictory()) {
			try {
				score += card.getPoints();
			} catch (NoSuchMethodException e) {
				log.log(Level.WARNING, "This is not a victory card", e);
			}
		}
		
		addToDiscard(card);
		
		buys -= 1;
		return result;
	}
	
	/**
	 * @param displayName The name that will be shown in the game.
	 */
	public void setPlayerName(String displayName) {
		this.displayName = displayName;
	}
	
	/**
	 * 
	 * @return the username associated with this player
	 */
	public String getPlayerName() {
		return displayName;
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
     * Adds a card to the deck.
     *
     * @param card
     *            - the card added.
     */
    public void addToDeck(Card card, int index) {
        deck.add(index, card);
    }

	/**
	 * Adds a card to the discard pile.
	 * 
	 * @param card The card being discarded.
	 */
	public void addToDiscard(Card card) {
		discardPile.add(card);
	}
	
	/**
	 * Removes a card from the player's hand and adds it to the 
	 * player's discard pile.
	 * 
	 * @param card The card being discarded from the player's hand
	 */
	public void addToDiscardFromHand(Card card) {
		boolean cardRemoved = hand.remove(card);
		if (cardRemoved) {
			discardPile.add(card);
			removeMoney(card);
		}
	}

	/**
	 * Removes a card from the player's hand. This is used
	 * in the rare instances that a card needs to go to the
	 * trash and the player doesn't have access to the trash.
	 * @param card The card to be removed
	 */
	public void removeCardFromHand(Card card) {
		hand.remove(card);
		removeMoney(card);
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
	 * Discards the hand into the discard pile.
	 * 
	 * @return A result object determining if the discard was successful.
	 */
	public Result discardHand() {
		boolean added = discardPile.addAll(hand);
		hand.clear();
		return new Result(added, "Added to had to discard");
	} 
	
	/**
	 * Given a card this method determines if the card
	 * is in the player's hand.
	 * @param card The card that will be checked against the
	 * player's hand
	 * @return true if the card is in the player's hand and false
	 * otherwise.
	 */
	public boolean hasCardInHand(Card card) {
		for (Card c : hand) {
			if (c.equals(card)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if a player can play a given action card.
	 * Checks by seeing if the player is in his buy phase, has
	 * any actions left, and if the card is in the player's hand.
	 * @param card The action card that the player may or may not 
	 * be able to play.
	 * @return a result object
	 */
	public Result canPlayAction(Card card) {
		Result result = new Result(true, "");
		if (!card.isAction()) {
			result.setSuccess(false);
			result.setMessage("Card is not an action card.");
		}
		if (getPhase().equals(Phase.BUY)) {
			result.setSuccess(false);
			result.setMessage("Cannot play action card during buy phase. ");;
		}
		if (getActionCount() == 0) {
			result.setSuccess(false);
			result.setMessage(result.getMessage() + "No more actions to play. ");
		}
		
		if (!hasCardInHand(card)) {
			result.setSuccess(false);
			result.setMessage(result.getMessage() + "Card isn't in your hand. ");
		}
		return result;
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
	public Collection<Card> draw(int numCards) {
        List<Card> cards = new ArrayList<>();
		for (int i = 0; i < numCards; i++) {
			if (deck.isEmpty()) {
				CardUtils.shuffle(deck, discardPile);
			}
			Card card = deck.get(0);
			addMoney(card);
			hand.add(card);
			deck.remove(0);
            cards.add(card);
		}
        return cards;
	}
	
	/**
	 * Draw one card from the player's deck into the player's hand
	 */
	public Card drawCard() {
		if (deck.isEmpty()) {
			CardUtils.shuffle(deck, discardPile);
		}
		Card card = deck.get(0);
		addMoney(card);
		hand.add(card);
		deck.remove(0);
        return card;
	}
	
	/**
	 * Adds one value to the player's money.
	 */
	public void addMoney() {
		money += 1;
	}

	/**
	 * Adds a certai amount of money to the player's money.
	 * 
	 * @param value the value of money to be added to the player's
	 * money
	 */
	public void addMoney(int value) {
		money += value;
	}
	
	/**
	 * Adds the value of the treasure card to the player's money.
	 * @param card A treasure card.
	 */
	private void addMoney(Card card) {
		if (card.isTreasure()) {
			try {
				money += card.getValue();
			} catch (Exception e) {
				log.log(Level.WARNING, "Card does not have getValue() method", e);
			}
		}
	}

	/**
	 * Removes an amount of money from a player's hand according to the
	 * value of the card
	 * @param card a card that is being subtracted from the player's money
	 */
	private void removeMoney(Card card) {
		if (card.isTreasure()) {
			try {
				money -= card.getValue();
			} catch (Exception e) {
				log.log(Level.WARNING, "Card does not have a getValue() method", e);
			}
		}
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerType == null) ? 0 : playerType.hashCode());
		result = prime * result + new Long(uniqueIdentifier).intValue();
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
		if (playerType != other.playerType) {
			return false;
		}
		if (uniqueIdentifier != other.uniqueIdentifier) {
			return false;
		}
		return true;
	}

}
