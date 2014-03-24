package com.xalero.dominion.client.model;

import java.util.ArrayList;
import java.util.List;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.server.model.Player;

public class SimpleSpecificPlayer {
	
	private SimplePlayer simplePlayer;

	private List<String> hand;
	private List<String> deck;
	private List<String> discardPile;
	private int money;
	
	public SimpleSpecificPlayer(Player player) {
		simplePlayer = new SimplePlayer(player);
				
		hand = new ArrayList<>();
		for (Card card : player.getHand()) {
			hand.add(card.toString());
		}
		deck = new ArrayList<>();
		for (Card card : player.getDeck()) {
			deck.add(card.toString());
		}
		discardPile = new ArrayList<>();
		for (Card card : player.getDiscardPile()) {
			discardPile.add(card.toString());
		}
		money = player.getMoneyCount();
	}
	
	public int getTurnNumber() {
		return simplePlayer.getTurnNumber();
	}
	
	public int getActionCount() {
		return simplePlayer.getActionCount();
	}
	
	public int getBuyCount() {
		return simplePlayer.getBuyCount();
	}
	
	public int getNumCardsInHand() {
		return simplePlayer.getNumCardsInHand();
	}
	
	public String getName() {
		return simplePlayer.getName();
	}

	public List<String> getHand() {
		return hand;
	}

	public List<String> getDeck() {
		return deck;
	}

	public List<String> getDiscardPile() {
		return discardPile;
	}
	
	public int getMoney() {
		return money;
	}
}
