package dominion.cards.victory;

import dominion.cards.CardConstants;


public class EstateCard extends VictoryCard {

	@Override
	public int getPoints() {
		return CardConstants.ESTATE_POINTS;
	}

	@Override
	public int getCost() {
		return CardConstants.ESTATE_COST;
	}

}
