package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Chancellor extends ActionCard {

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlusTreasures() {
		return CardConstants.TWO;
	}

	@Override
	public int getPlusActions() {
		// TODO Auto-generated method stub
		return 0;
	}
}
