package com.xalero.dominion.cards.action;

import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.utils.Result;

public interface ActionBase {
	public int getPlusBuys();
	public int getPlusDraws();
	public int getPlusTreasures();
	public int getPlusActions();
	public Result playCard(DominionModel dominionModel, long playerId);
}
