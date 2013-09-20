package dominion.cards;

import dominion.cards.treasure.TreasureValue;
import dominion.cards.victory.VictoryPoints;


public class Card implements CardBase, VictoryPoints, TreasureValue {
	private Card card;
	
	public int getCost() {
		return card.getCost();
	}

	@Override
	public CardType getCardType() {
		return card.getCardType();
	}

	@Override
	public int getPoints() throws NoSuchMethodException {
		if (!card.getCardType().equals(CardType.Victory)) {
			throw new NoSuchMethodException();
		}
		return card.getPoints();
	}

	@Override
	public int getValue() throws NoSuchMethodException {
		if (!card.getCardType().equals(CardType.Victory)) {
			throw new NoSuchMethodException();
		}
		return card.getValue();
	}
}
