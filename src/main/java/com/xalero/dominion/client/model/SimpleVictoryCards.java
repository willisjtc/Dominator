package com.xalero.dominion.client.model;

import com.xalero.dominion.cards.victory.VictoryCards;

public class SimpleVictoryCards {

	private int provinces;
	private int duchies;
	private int estates;
	
	public SimpleVictoryCards(VictoryCards victoryCards) {
		provinces = victoryCards.getProvinceCount();
		duchies = victoryCards.getDuchyCount();
		estates = victoryCards.getEstateCount();
	}

	public int getProvinces() {
		return provinces;
	}

	public int getDuchies() {
		return duchies;
	}

	public int getEstates() {
		return estates;
	}
}
