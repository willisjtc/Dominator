package dominion.cards.action;

import dominion.cards.Card;

import static dominion.game.DominionConstants.LARGE_GAME_VICTORY_CARD_COUNT;
import static dominion.game.DominionConstants.SMALL_GAME_VICTORY_CARD_COUNT;

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
            cardCount--;
            return kingdomCard;
        }
        
        public int getCardCount() {
            return cardCount;
        }
	
	public Card getKingdomCard() {
		return kingdomCard;
	}
}
