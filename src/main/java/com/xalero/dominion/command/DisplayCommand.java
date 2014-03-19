package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.utils.Result;


public class DisplayCommand extends Command {

	public DisplayCommand(String parameters) {
		super(parameters);
	}

	
	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			long playerId) {
		Result result = new Result(true, "");
		
		switch (parameters.get(0)) {
		case "discard":
			if (parameters.get(1) != null && parameters.get(1).equals("pile")) {
				gameOutput.appendText("\nDiscard Pile: ");
				for (Card card : dominionModel.getPlayerById(playerId).getDiscardPile()) {
					gameOutput.appendText("\n\t" + card);
				}
			}
			break;
		case "hand":
			System.out.println("player from model: " + dominionModel.getCurrentPlayer());
			System.out.println("player from insertion: " + dominionModel.getPlayerById(playerId));
			gameOutput.appendText("\nHand:");
			for (Card card : dominionModel.getPlayerById(playerId).getHand()) {
				gameOutput.appendText("\n\t" + card);
			}
			break;
		}
		dominionModel.getPlayerById(playerId).getDiscardPile();
		
		return result;
	}

}
