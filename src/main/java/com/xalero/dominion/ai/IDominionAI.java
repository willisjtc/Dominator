package com.xalero.dominion.ai;

import java.util.List;

import com.xalero.dominion.cards.Card;

public interface IDominionAI {

	public void setSupplyPile();
	public void setCurses();
	public void setKingdomCards();
	public void setTreasures();
	public void setHand(List<Card> hand);
}
