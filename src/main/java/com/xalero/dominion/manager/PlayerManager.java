package com.xalero.dominion.manager;

import java.util.logging.Logger;

import com.xalero.dominion.server.model.Player;
import com.xalero.dominion.server.model.PlayerType;
import com.xalero.dominion.server.model.SimplePlayerInfo;

public enum PlayerManager {
	INSTANCE;
	
	private static final Logger log = Logger.getLogger(PlayerManager.class.getName());
		
	public Player getCurrentUserPlayer() {
		SimplePlayerInfo spi = new SimplePlayerInfo(0, null, PlayerType.HUMAN);
		Player currentPlayer = new Player(spi, -1);
		return currentPlayer;
	}
}
