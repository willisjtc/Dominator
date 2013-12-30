package dominion.cards.treasure;

import dominion.cards.Card;
import dominion.cards.CardFactory;
import dominion.game.DominionConstants;

public class Treasures {

    private int goldCount;
    private int silverCount;
    private int copperCount;

    public Treasures() {
        goldCount = DominionConstants.GOLD_CARD_COUNT;
        silverCount = DominionConstants.SILVER_CARD_COUNT;
        copperCount = DominionConstants.COPPER_CARD_COUNT;
    }

    public Card getGold(int numGold) {
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
}
