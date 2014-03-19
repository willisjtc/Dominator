package com.xalero.dominion.server.model;

import com.xalero.dominion.cards.Card;

public class InteractiveCard {

	private Card attack;
	private Player player;
	
	public InteractiveCard(Card attack, Player player) {
		this.attack = attack;
		this.player = player;
	}

	public Card getAttack() {
		return attack;
	}

	public void setAttack(Card attack) {
		this.attack = attack;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
