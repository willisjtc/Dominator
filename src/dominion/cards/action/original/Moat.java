package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Moat extends ActionCard {

	public int getCost() {
		return CardConstants.TWO;
	}
	
	@Override
	public boolean isReaction() {
		return true;
	}
	
	@Override
	public int getPlusBuys() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlusDraws() {
		return CardConstants.TWO;
	}

	@Override
	public int getPlusTreasures() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPlusActions() {
		// TODO Auto-generated method stub
		return 0;
	}
}
