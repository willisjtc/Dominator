package dominion.cards;

import dominion.cards.treasure.TreasureBase;
import dominion.cards.victory.VictoryBase;


public abstract class Card implements CardBase, VictoryBase, TreasureBase {
	private Card card;
	
	public int getCost() {
		return card.getCost();
	}

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
}
