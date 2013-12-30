package dominion.cards.victory;

import dominion.cards.Card;
import dominion.cards.CardFactory;

import static dominion.game.DominionConstants.LARGE_GAME_VICTORY_CARD_COUNT;
import static dominion.game.DominionConstants.SMALL_GAME_VICTORY_CARD_COUNT;

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
            return CardFactory.province;
        }
        return null;
    }

    public Card getDuchy() {
        if (duchyCount > 0) {
            return CardFactory.duchy;
        }
        return null;
    }

    public Card getEstate() {
        if (estateCount > 0) {
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
}
