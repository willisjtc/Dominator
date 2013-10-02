package dominion.cards.action;

import dominion.cards.Card;
import dominion.cards.CardBase;

public abstract class ActionCard extends Card implements ActionBase, CardBase {
	
	protected int buys;
	protected int draws;
	protected int treasures;
	protected int actions;
	
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
	
	public void setPlusBuys(int buys) {
		this.buys = buys;
	}
	public void setPlusDraws(int draws) {
		this.draws = draws;
	}
	public void setPlusTreasures(int treasures) {
		this.treasures = treasures;
	}
	public void setPlusActions(int actions) {
		this.actions = actions;
	}
}
