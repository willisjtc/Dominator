package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.dto.PlayerIdDto;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.utils.Result;

public class EndTurnCommand extends Command {

	public EndTurnCommand() {
		super("");
	}
	
	public EndTurnCommand(String parameters) {
		super(parameters);
	}
	
	@Override
	protected Result execute(TextArea gameOutput, DominionEventHandler dominionEventHandler,
			long playerId) {
		
		Gson gson = new GsonBuilder().create();
		
		PlayerIdDto endTurnDto = new PlayerIdDto(playerId);
		DominionMessage message = new DominionMessage(DominionEvent.END_TURN, gson.toJson(endTurnDto));
		
		String resultStr = dominionEventHandler.receiveEvent(gson.toJson(message));
		Result result = gson.fromJson(resultStr, Result.class);
		
		return result;
	}

}
