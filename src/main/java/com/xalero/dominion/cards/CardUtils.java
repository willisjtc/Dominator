package com.xalero.dominion.cards;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.xalero.dominion.model.DominionConstants;


public class CardUtils {
	public static Collection<Card> shuffle(List<Card> deck, List<Card> discard) {
            if (discard != null) {
                for (Card card : discard) {
                    deck.add(card);
                }
            }
            discard.clear();
            
            // The modern version of the Fisher?Yates shuffle (wikipedia)s
            Random random = new Random();
            for (int i = deck.size() - 1; i > 0; i--) {
                int j = random.nextInt(i);
                Card card = deck.get(i);
                deck.set(i, deck.get(j));
                deck.set(j, card);
            }
            return deck;
	}

	public static Collection<Card> getRandomCardSet() {
		List<Card> cardsToChooseFrom = new LinkedList<>(Arrays.asList(CardFactory.getBaseKingdomCards()));
		List<Card> cardsRandomlySelected = new LinkedList<>();
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
