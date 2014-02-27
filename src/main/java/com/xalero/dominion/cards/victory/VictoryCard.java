package com.xalero.dominion.cards.victory;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardBase;

public abstract class VictoryCard extends Card implements VictoryBase, CardBase {
	
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
		return true;
	}
}
