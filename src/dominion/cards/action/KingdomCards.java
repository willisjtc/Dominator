package dominion.cards.action;

import static dominion.cards.CardConstants.ELEVEN;
import static dominion.cards.CardConstants.TEN;
import dominion.cards.Card;

public class KingdomCards {
	private Card[][] kingdomCards;
	
	public KingdomCards() {
		kingdomCards = new Card[TEN][ELEVEN];
	}
	
	public void populateKingdomCards() {
		// TODO
	}
	
	public Card getKingdomCard(Card card) {
		// TODO
		return null;
	}
	
	public Card[][] getCards() {
		return kingdomCards;
	}
}
