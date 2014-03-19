package com.xalero.dominion.client.model;

import com.xalero.dominion.cards.action.KingdomCard;

public class SimpleKingdomCard {

	private String kingdomCard;
	private int count;
	
	public SimpleKingdomCard(KingdomCard kingdomCard) {
		this.kingdomCard = kingdomCard.toString();
		this.count = kingdomCard.getCardCount();
	}

	public String getKingdomCard() {
		return kingdomCard;
	}

	public int getCount() {
		return count;
	}
}
