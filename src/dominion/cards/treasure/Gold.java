package dominion.cards.treasure;

import dominion.cards.CardConstants;

public class Gold extends TreasureCard {

	@Override
	public int getValue() {
		return CardConstants.GOLD_WORTH;
	}

	@Override
	public int getCost() {
		return CardConstants.SIX;
	}
}
