package dominion.cards;

import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import dominion.cards.treasure.TreasureBase; 
import dominion.cards.victory.VictoryBase;

public abstract class Card implements CardBase, VictoryBase, TreasureBase {
	private Card card;
	
	public abstract int getCost();

	@Override
	public int getPoints() throws NoSuchMethodException {
		if (!card.isVictory()) {
			throw new NoSuchMethodException();
		}
		return card.getPoints();
	}

	@Override
	public int getValue() throws NoSuchMethodException {
		if (!card.isVictory()) {
			throw new NoSuchMethodException();
		}
		return card.getValue();
	}

	@Override
	public boolean isAction() {
		return false;
	}

	@Override
	public boolean isAttack() {
		return false;
	}

	@Override
	public boolean isReaction() {
		return false;
	}

	@Override
	public boolean isTreasure() {
		return false;
	}

	@Override
	public boolean isVictory() {
		return false;
	}

	public static List<Card> shuffle(List<Card> deck, List<Card> discard) {
		throw new NotImplementedException();
	}

}
