package com.xalero.dominion.manager;

import java.util.Map;
import java.util.TreeMap;

/**
 * Object for keeping track of the current players game and player ids. 
 * Each game has unique id as well as each player in the game.
 * 
 * @author jonathan
 */
public enum PlayerGameManager {
	INSTANCE;
	
	private Map<Long, Long> gamePlayerMapping = new TreeMap<Long, Long>();
	
	public void addGamePlayerKeyPair(Long game, Long playerId) {
		gamePlayerMapping.put(game, playerId);
	}
}
