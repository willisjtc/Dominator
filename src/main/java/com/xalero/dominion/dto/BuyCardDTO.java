package com.xalero.dominion.dto;

public class BuyCardDTO {
	private String cardName;
	private long playerId;
	
	public BuyCardDTO(String cardName, long playerId) {
		this.cardName = cardName;
		this.playerId = playerId;
	}
	
	public String getCardName() {
		return cardName;
	}
	
	public long getPlayerId() {
		return playerId;
	}
}
