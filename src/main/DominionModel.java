package main;

import java.io.File;
import java.util.Collection;
import java.util.List;

import dominion.cards.Card;
import dominion.cards.action.KingdomCards;
import dominion.cards.expansion.Expansion;
import dominion.cards.trash.Trash;
import dominion.cards.treasure.Treasures;
import dominion.cards.victory.VictoryCards;
import dominion.player.Player;

public class DominionModel {
	private File saveFile;
	private Treasures treasureCards;
	private VictoryCards victoryCards;
	private KingdomCards kingdomCards;
	private Trash trash;
	private List<Expansion> expansions;
	private List<Player> players;
	private Integer playerTurn;
	
	public DominionModel(List<Expansion> exps) {
		expansions = exps;
	}
	
	public void setPlayerTurn(int playerTurn) {
		playerTurn = new Integer(playerTurn);
	}

	public Integer getPlayerTurn() {
		return playerTurn;
	}
	
	public Collection<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(String name) {
		// TODO 
		return null;
	}
	
	public Player getPlayer(Integer index) {
		// TODO
		return null;
	}
	
	public Card getVictoryCard(String name) {
		// TODO
		return victoryCards.getVictoryCard(name);
	}
	
	public Trash getTrashCards() {
		return trash;
	}
	
	public VictoryCards getVictoryCards() {
		return victoryCards;
	}

	public KingdomCards getKingdomCards() {
		return kingdomCards;
	}
	
	public Treasures getTreasures() {
		return treasureCards;
	}

	public String getFileName() {
		return saveFile.getName();
	}
	
	public void save() {
		// TODO	
	}
	
	public void setFileName(String fileName) {
		// TODO
	}
	
}
