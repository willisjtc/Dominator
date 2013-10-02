package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Cellar extends ActionCard {

	@Override
	public int getCost() {
		return CardConstants.TWO;
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
		return 1;
	}
}
