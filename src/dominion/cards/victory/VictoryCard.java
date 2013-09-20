package dominion.cards.victory;

import dominion.cards.CardBase;
import dominion.cards.CardType;

public abstract class VictoryCard implements VictoryPoints, CardBase{
	public CardType getCardType() {
		return CardType.Victory;
	}	
}
