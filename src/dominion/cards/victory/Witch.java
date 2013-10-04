package dominion.cards.victory;

import dominion.cards.CardConstants;

public class Witch extends VictoryCard {

	@Override
	public int getPoints() {
		return CardConstants.CURSE_POINTS;
	}
	
	@Override
	public int getCost() {
		return 0;
	}

}
