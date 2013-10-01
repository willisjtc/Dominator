package dominion.cards.treasure;

import dominion.cards.CardConstants;

public class Copper extends TreasureCard {

	@Override
	public int getValue() {
		return CardConstants.COPPER_WORTH;
	}

	@Override
	public int getCost() {
		return CardConstants.COST_ZERO;
	}
}
