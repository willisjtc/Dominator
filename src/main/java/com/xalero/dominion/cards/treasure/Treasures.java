package com.xalero.dominion.cards.treasure;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.model.DominionConstants;


public class Treasures {

    private int goldCount;
    private int silverCount;
    private int copperCount;

    public Treasures() {
        goldCount = DominionConstants.GOLD_CARD_COUNT;
        silverCount = DominionConstants.SILVER_CARD_COUNT;
        copperCount = DominionConstants.COPPER_CARD_COUNT;
    }

    public Card getGold() {
        if (goldCount > 0) {
            goldCount--;
            return CardFactory.gold;
        }
        return null;
    }

    public Card getSilver() {
        if (silverCount > 0) {
            silverCount--;
            return CardFactory.silver;
        }
        return null;
    }

    public Card getCopper() {
        if (copperCount > 0) {
            copperCount--;
            return CardFactory.copper;
        }
        return null;
    }
    
    public int getGoldCount() {
        return goldCount;
    }
    
    public int getSilverCount() {
        return silverCount;
    }
    
    public int getCopperCount() {
        return copperCount;
    }

    public boolean contains(Card card) {
    	if (CardFactory.gold.equals(card) && goldCount > 0) {
    		return true;
    	} 
    	if (CardFactory.silver.equals(card) && silverCount > 0) {
    		return true;
    	}
    	if (CardFactory.copper.equals(card) && copperCount > 0) {
    		return true;
    	}
    	return false;
    }

    public Card getTreasure(Card card) {
		if (CardFactory.gold.equals(card)) {
			return getGold();
		} 
		if (CardFactory.silver.equals(card)) {
			return getSilver();
		}
		
		return getCopper();
	}
}
