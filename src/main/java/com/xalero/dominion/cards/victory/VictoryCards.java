package com.xalero.dominion.cards.victory;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;

import static com.xalero.dominion.model.DominionConstants.LARGE_GAME_VICTORY_CARD_COUNT;
import static com.xalero.dominion.model.DominionConstants.SMALL_GAME_VICTORY_CARD_COUNT;

public class VictoryCards {
    
    private int provinceCount;
    private int duchyCount;
    private int estateCount;
    
    public VictoryCards(int numPlayers) {
        if (numPlayers > 2) {
            provinceCount = LARGE_GAME_VICTORY_CARD_COUNT;
            duchyCount = LARGE_GAME_VICTORY_CARD_COUNT;
            estateCount = LARGE_GAME_VICTORY_CARD_COUNT;
        } else {
            provinceCount = SMALL_GAME_VICTORY_CARD_COUNT;
            duchyCount = SMALL_GAME_VICTORY_CARD_COUNT;
            estateCount = SMALL_GAME_VICTORY_CARD_COUNT;
        }        
    }
    
    public Card getProvince() {
        if (provinceCount > 0) {
        	provinceCount--;
            return CardFactory.province;
        }
        return null;
    }

    public Card getDuchy() {
        if (duchyCount > 0) {
        	duchyCount--;
            return CardFactory.duchy;
        }
        return null;
    }

    public Card getEstate() {
        if (estateCount > 0) {
        	estateCount--;
            return CardFactory.estate;
        }
        return null;
    }
    
    public int getProvinceCount() {
        return provinceCount;
    }
    
    public int getDuchyCount() {
        return duchyCount;
    }
    
    public int getEstateCount() {
        return estateCount;
    }

    public boolean contains(Card card) {
    	if (CardFactory.province.equals(card) && provinceCount > 0) {
    		return true;
    	}
    	if (CardFactory.duchy.equals(card) && duchyCount > 0) {
    		return true;
    	}
    	if (CardFactory.estate.equals(card) && estateCount > 0) {
    		return true;
    	}
    	return false;
    }

    public Card getVictoryCard(Card card) {
    	if (CardFactory.province.equals(card)) {
    		return getProvince();
    	}
    	if (CardFactory.duchy.equals(card)) {
    		return getDuchy();
    	}
    	return getEstate();
    }
}
