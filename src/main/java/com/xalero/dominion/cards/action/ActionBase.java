package com.xalero.dominion.cards.action;

import java.util.List;

import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.utils.Result;

public interface ActionBase {
	public int getPlusBuys();
	public int getPlusDraws();
	public int getPlusTreasures();
	public int getPlusActions();
	public Result playCard(List<String> parameters, DominionModel dominionModel, long playerId);
}
