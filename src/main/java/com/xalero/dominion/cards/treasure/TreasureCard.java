package com.xalero.dominion.cards.treasure;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardBase;


public abstract class TreasureCard extends Card implements TreasureBase, CardBase {
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
		return true;
	}

	@Override
	public boolean isVictory() {
		return false;
	}
}
