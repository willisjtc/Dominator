package dominion.cards.treasure;

import dominion.cards.CardConstants;

public class Silver extends TreasureCard {

	@Override
	public int getValue() {
		return CardConstants.SILVER_WORTH;
	}

	@Override
	public int getCost() {
		return CardConstants.THREE;
	}
}
