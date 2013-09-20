package dominion.cards.treasure;

import dominion.cards.CardBase;
import dominion.cards.CardType;


public abstract class TreasureCard implements TreasureValue, CardBase {
	public CardType getCardType() {
		return CardType.Treasure;
	}
}
