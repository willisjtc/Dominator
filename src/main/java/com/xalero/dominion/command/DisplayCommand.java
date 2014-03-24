package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.dto.PlayerIdDto;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.utils.Result;


public class DisplayCommand extends Command {

	public DisplayCommand(String parameters) {
		super(parameters);
	}

	
	@Override
	protected Result execute(TextArea gameOutput, DominionEventHandler dominionEventHandler,
			long playerId) {
		Result result = new Result(true, "");
		Gson gson = new GsonBuilder().create();
		PlayerIdDto playerIdDto = new PlayerIdDto(playerId);
		switch (parameters.get(0)) {
		case "discard":
			if (parameters.get(1) != null && parameters.get(1).equals("pile")) {
				
				DominionMessage message = new DominionMessage(DominionEvent.DISCARD_PILE, gson.toJson(playerIdDto));
				dominionEventHandler.receiveEvent(gson.toJson(message));
			}
			break;
		case "hand":
			DominionMessage message = new DominionMessage(DominionEvent.HAND, gson.toJson(playerIdDto));
			dominionEventHandler.receiveEvent(gson.toJson(message));
			break;
		}
		return result;
	}

}
