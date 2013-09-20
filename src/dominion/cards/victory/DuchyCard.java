package dominion.cards.victory;

import dominion.cards.CardConstants;


public class DuchyCard extends VictoryCard {

	@Override
	public int getPoints() {
		return CardConstants.DUCHY_POINTS;
	}

	@Override
	public int getCost() {
		return CardConstants.DUCHY_COST;
	}

}
