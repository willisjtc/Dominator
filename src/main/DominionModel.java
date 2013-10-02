package main;

import java.io.File;
import java.util.List;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import dominion.cards.Card;
import dominion.player.Player;

public class DominionModel {
	private File saveFile;
	private List<Card> treasureCards;
	private List<Card> victoryCards;
	private List<Card> kingdomCards;
	private Integer playerTurn;
	private List<Integer> scores;
	private List<Player> players;
	private List<Card> trashCards;
	
	public Integer getPlayerTurn() {
		return playerTurn;
	}
	
	public void setPlayerTurn(int playerTurn) {
		playerTurn = new Integer(playerTurn);
	}

	public List<Player> getPlayers() {
		return players;
	}

	public List<Card> getTrashCards() {
		return trashCards;
	}

	public List<Card> getTreasureCards() {
		return treasureCards;
	}

	public List<Card> getVictoryCards() {
		return victoryCards;
	}
	
	public void removeCard() {
		
	}

	public List<Card> getKingdomCards() {
		return kingdomCards;
	}

	public List<Integer> getScores() {
		return scores;
	}
	
	public void setFileName(String fileName) {
		throw new NotImplementedException();
	}
	
	public String getFileName() {
		return saveFile.getName();
	}
	
	public void save() {
		
	}
}
