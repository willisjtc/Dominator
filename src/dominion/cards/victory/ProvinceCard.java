package dominion.cards.victory;

import dominion.cards.CardConstants;


public class ProvinceCard extends VictoryCard {

	@Override
	public int getPoints() {
		return CardConstants.PROVINCE_POINTS;
	}
	
	@Override
	public int getCost() {
		return CardConstants.COST_EIGHT;
	}
}
