package com.xalero.dominion.client.model;

import com.xalero.dominion.server.model.Player;

public class SimplePlayer {
	private int index;
	private String name;
	private int numCardsInHand;
	
	public SimplePlayer(Player player) {
		index = player.getIndex();
		name = player.getPlayerName();
		numCardsInHand = player.getHand().size();
	}

	public int getIndex() {
		return index;
	}

	public String getName() {
		return name;
	}

	public int getNumCardsInHand() {
		return numCardsInHand;
	}
}
