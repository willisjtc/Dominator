package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Adventurer extends ActionCard {

	public int getCost() {
		return CardConstants.SIX;
	}
	
	@Override
	public int getPlusBuys() {
		return 0;
	}

	@Override
	public int getPlusDraws() {
		return 0;
	}

	@Override
	public int getPlusTreasures() {
		return 0;
	}

	@Override
	public int getPlusActions() {
		return 0;
	}
}
