package com.xalero.dominion.client.model;

import com.xalero.dominion.cards.treasure.Treasures;

public class SimpleTreasures {

	private int golds;
	private int silvers;
	private int coppers;
	
	public SimpleTreasures(Treasures treasures) {
		golds = treasures.getGoldCount();
		silvers = treasures.getSilverCount();
		coppers = treasures.getCopperCount();
	}
	
	public int getGolds() {
		return golds;
	}

	public int getCoppers() {
		return coppers;
	}

	public int getSilvers() {
		return silvers;
	}
}
