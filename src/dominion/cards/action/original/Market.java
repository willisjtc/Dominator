package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Market extends ActionCard {

	public int getCost() {
		return CardConstants.FIVE;
	}
	
	@Override
	public int getPlusBuys() {
		return CardConstants.ONE;
	}

	@Override
	public int getPlusDraws() {
		return CardConstants.ONE;
	}

	@Override
	public int getPlusTreasures() {
		return CardConstants.ONE;
	}

	@Override
	public int getPlusActions() {
		return CardConstants.ONE;
	}
}
