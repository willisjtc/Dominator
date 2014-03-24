package com.xalero.dominion.dto;

import java.util.List;

public class PlayCardDTO {
	private String cardName;
	private long playerId;
	private List<String> parameters;
	
	public PlayCardDTO(String cardName, long playerId, List<String> parameters) {
		this.cardName = cardName;
		this.playerId = playerId;
		this.parameters = parameters;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public long getPlayerId() {
		return playerId;
	}
	
	public List<String> getParameters() {
		return parameters;
	}
}
