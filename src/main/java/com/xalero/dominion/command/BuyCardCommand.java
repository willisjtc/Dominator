package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.dto.BuyCardDTO;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.utils.Result;


public class BuyCardCommand extends Command {

	public BuyCardCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionEventHandler dominionEventHandler, long playerId) {
		Result result = new Result(false, "");
		try {
			String cardToBuy = parameters.get(0);
			switch (cardToBuy) {
			case "council":
			case "throne":
				if (parameters.size() == 2) {
					cardToBuy = cardToBuy.concat(" " + parameters.get(1));
				}
				break;
			}
			
			Gson gson = new GsonBuilder().create();
			
			BuyCardDTO buyDto = new BuyCardDTO(cardToBuy, playerId);
			DominionMessage message = new DominionMessage(DominionEvent.BUY_CARD, gson.toJson(buyDto));
			String resultStr = dominionEventHandler.receiveEvent(gson.toJson(message));
			
			result = gson.fromJson(resultStr, Result.class);
		} catch (Exception e) {
			gameOutput.appendText("\nSorry, that card doesn't exist");
		}
		
		return result;
	}
}
