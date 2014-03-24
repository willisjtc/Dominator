package com.xalero.dominion.client.model;

import com.xalero.dominion.server.model.Player;

public class SimplePlayer {
	protected int turnNumber;
	protected String name;
	protected int numCardsInHand;
	protected int actionCount;
	protected int buyCount;
	
	public SimplePlayer(Player player) {
		turnNumber = player.getIndex();
		name = player.getPlayerName();
		numCardsInHand = player.getHand().size();
		actionCount = player.getActionCount();
		buyCount = player.getBuyCount();
	}

	public int getTurnNumber() {
		return turnNumber;
	}

	public String getName() {
		return name;
	}

	public int getNumCardsInHand() {
		return numCardsInHand;
	}
	
	public int getActionCount() {
		return actionCount;
	}
	
	public int getBuyCount() {
		return buyCount;
	}
}
