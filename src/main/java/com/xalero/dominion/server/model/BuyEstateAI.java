package com.xalero.dominion.server.model;

import com.google.gson.Gson;
import com.xalero.dominion.ai.IDominionAI;
import com.xalero.dominion.client.model.SimpleModel;
import com.xalero.dominion.client.model.SimpleSpecificPlayer;
import com.xalero.dominion.dto.BuyCardDTO;
import com.xalero.dominion.dto.PlayerIdDto;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;

public class BuyEstateAI implements IDominionAI {

	private DominionEventHandler dominionEventHandler;
	private long playerId;
	private Gson gson;
	private int money;
	private int myTurnNumber;
	
	public BuyEstateAI(long playerId, int myTurnNumber) {
		this.playerId = playerId;
		this.gson = new Gson();
		this.myTurnNumber = myTurnNumber;
	}
	
	@Override
	public void setDominionEventHandler(
			DominionEventHandler dominionEventHandler) {
		this.dominionEventHandler = dominionEventHandler;
	}

	@Override
	public void update(String event) {
		DominionMessage message = gson.fromJson(event, DominionMessage.class);
		switch (message.getEvent()) {
		case PLAYER_MODEL:
			SimpleSpecificPlayer me = gson.fromJson(message.getValue(), SimpleSpecificPlayer.class);
			money = me.getMoney();
			break;
		case DOMINION_MODEL:
			SimpleModel simpleModel = gson.fromJson(message.getValue(), SimpleModel.class);
			if (simpleModel.getPlayerTurn() == myTurnNumber) {
				takeTurn();
			}
			break;
		default:
				break;
		}
	}
	
	public void takeTurn() {
		if (money > 1) {
			BuyCardDTO buyCardDto = new BuyCardDTO("estate", playerId);
			DominionMessage message = new DominionMessage(DominionEvent.BUY_CARD, gson.toJson(buyCardDto));
			dominionEventHandler.receiveEvent(gson.toJson(message));
			message = new DominionMessage(DominionEvent.END_TURN, gson.toJson(new PlayerIdDto(playerId)));
			dominionEventHandler.receiveEvent(gson.toJson(message));
		}
	}
}
