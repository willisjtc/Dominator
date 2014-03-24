package com.xalero.dominion.client.model;

import java.util.ArrayList;
import java.util.List;

import com.xalero.dominion.cards.action.KingdomCard;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.server.model.Player;

public class SimpleModel {
	
	private SimpleTreasures simpleTreasures;
	private SimpleVictoryCards simpleVictoryCards;
	
	private List<SimpleKingdomCard> kingdomCards;
	private List<SimplePlayer> simplePlayers;
	
	private int curses;
	
	private int playerTurn;

	public SimpleModel(DominionModel dominionModel) {
		simpleTreasures = new SimpleTreasures(dominionModel.getTreasures());
		simpleVictoryCards = new SimpleVictoryCards(dominionModel.getVictoryCards());
		
		kingdomCards = new ArrayList<>();
		for (KingdomCard kingdomCard : dominionModel.getKingdomCards()) {
			kingdomCards.add(new SimpleKingdomCard(kingdomCard));
		}
		
		simplePlayers = new ArrayList<>();
		for (Player player : dominionModel.getPlayers()) {
			simplePlayers.add(new SimplePlayer(player));
		}
		
		curses = dominionModel.getCurses().size();
		
		playerTurn = dominionModel.getPlayerTurn();
	}

	public SimpleTreasures getSimpleTreasures() {
		return simpleTreasures;
	}

	public SimpleVictoryCards getSimpleVictoryCards() {
		return simpleVictoryCards;
	}
	
	public List<SimpleKingdomCard> getKingdomCards() {
		return kingdomCards;
	}
	
	public List<SimplePlayer> getSimplePlayers() {
		return simplePlayers;
	}

	public int getCurses() {
		return curses;
	}

	public int getPlayerTurn() {
		return playerTurn;
	}
	
}
