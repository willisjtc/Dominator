package com.xalero.dominion.events;

public enum DominionEvent {
	DISPLAY ("display"),
	DOMINION_MODEL ("dominion_model"),
	PLAYER_MODEL ("player_model"),
	PLAY_CARD ("play_card"),
	BUY_CARD ("buy_card"),
	END_TURN ("end_turn"),
	START_GAME ("start_game"),
	KINGDOM_CARD_LIST ("get_kingdom_card_list"),
	CURSES ("get_curses"),
	DISCARD_PILE ("discard_pile"),
	HAND ("hand");
	
	private String name;
	
	DominionEvent(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
