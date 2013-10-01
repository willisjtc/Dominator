package dominion.cards.action;

import dominion.cards.CardConstants;

public class Cellar extends ActionCard {

	@Override
	public int getCost() {
		return CardConstants.COST_TWO;
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
