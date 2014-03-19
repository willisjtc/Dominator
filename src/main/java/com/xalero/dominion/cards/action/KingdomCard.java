package com.xalero.dominion.cards.action;

import com.xalero.dominion.cards.Card;

import static com.xalero.dominion.server.model.DominionConstants.LARGE_GAME_VICTORY_CARD_COUNT;
import static com.xalero.dominion.server.model.DominionConstants.SMALL_GAME_VICTORY_CARD_COUNT;

public class KingdomCard {
	private Card kingdomCard;
	private int cardCount = 10;

	public KingdomCard(Card kingdomCard, int playerCount) {
		if (kingdomCard.isVictory()) {
			if (playerCount > 2) {
				cardCount = LARGE_GAME_VICTORY_CARD_COUNT;
			} else {
				cardCount = SMALL_GAME_VICTORY_CARD_COUNT;
			}
		}
		this.kingdomCard = kingdomCard;
	}

	public Card drawCard() {
		if (cardCount <= 0) {
			return null;
		}
		cardCount--;
		return kingdomCard;
	}

	public int getCardCount() {
		return cardCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((kingdomCard == null) ? 0 : kingdomCard.hashCode());
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
		KingdomCard other = (KingdomCard) obj;
		if (kingdomCard == null) {
			if (other.kingdomCard != null) {
				return false;
			}
		} else if (!kingdomCard.equals(other.kingdomCard)) {
			return false;
		}
		return true;
	}
	
	public boolean equals(Card card) {
		if (kingdomCard.equals(card)) return true;
		return false;
	}

	public String toString() {
		return kingdomCard.toString();
	}
}
