package dominion.cards.action.original;

import dominion.cards.CardConstants;
import dominion.cards.action.ActionCard;

public class CouncilRoom extends ActionCard {

	public int getCost() {
		return CardConstants.FIVE;
	}
	
	@Override
	public int getPlusBuys() {
		return CardConstants.ONE;
	}

	@Override
	public int getPlusDraws() {
		return CardConstants.FOUR;
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
