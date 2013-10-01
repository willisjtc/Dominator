package dominion.cards.action;

import dominion.cards.Card;
import dominion.cards.CardBase;

public abstract class ActionCard extends Card implements ActionBase, CardBase {
	
	public boolean isAction() {
		return true;
	}
	public boolean isAttack() {
		return false;
	}
	public boolean isReaction() {
		return false;
	}
	public boolean isTreasure() {
		return false;
	}
	public boolean isVictory() {
		return false;
	}
}
