package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Village extends ActionCard {

	public int getCost() {
		return CardConstants.THREE;
	}
	
	@Override
	public int getPlusBuys() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlusDraws() {
		return CardConstants.ONE;
	}

	@Override
	public int getPlusTreasures() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlusActions() {
		return CardConstants.TWO;
	}
}
