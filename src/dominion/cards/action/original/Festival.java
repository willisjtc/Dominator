package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class Festival extends ActionCard {

	public int getCost() {
		return CardConstants.FIVE;
	}
	
	@Override
	public int getPlusBuys() {
		return CardConstants.ONE;
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
		return CardConstants.FIVE;
	}
}
