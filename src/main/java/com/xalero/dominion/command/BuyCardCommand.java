package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.utils.Result;


public class BuyCardCommand extends Command {

	public BuyCardCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, long playerId) {
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
			result = dominionModel.buyCard(playerId, CardFactory.createCard(cardToBuy));
			gameOutput.appendText("\n" + result.getMessage());
		} catch (Exception e) {
			gameOutput.appendText("\nSorry, that card doesn't exist");
		}
		
		return result;
	}
}
