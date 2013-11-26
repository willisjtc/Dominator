package dominion.cards;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import dominion.game.DominionConstants;

public class CardUtils {
	public static Collection<Card> shuffle(Deck deck, Discard discard) {
		throw new NotImplementedException();
	}

	public static Collection<Card> getRandomCardSet() {
		List<Card> cardsToChooseFrom = new LinkedList<Card>(Arrays.asList(CardFactory.getBaseKingdomCards()));
		List<Card> cardsRandomlySelected = new LinkedList<Card>();
		Random rand = new Random();
		for (int i = 0; i < DominionConstants.NUM_KINGDOM_CARDS; i++) {
			int index = rand.nextInt(cardsToChooseFrom.size());
			cardsRandomlySelected.add(cardsToChooseFrom.get(index));
			cardsToChooseFrom.remove(index);
		}
		return cardsRandomlySelected;
	}
	
	public static Card getRandomCard(Card[] completeList) {
		List<Card> completeCardList = Arrays.asList(completeList);
		Random rand = new Random();
		List<Card> cardsToChooseFrom = new LinkedList<Card>(Arrays.asList(CardFactory.getBaseKingdomCards()));
		cardsToChooseFrom.removeAll(completeCardList);
		Card replacement = cardsToChooseFrom.get(rand.nextInt(cardsToChooseFrom.size()));
		return replacement;
	}
}
