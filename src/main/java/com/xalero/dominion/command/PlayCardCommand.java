package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.cards.action.ActionBase;
import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.utils.Result;

public class PlayCardCommand extends Command {

	public PlayCardCommand(String parameters) {
		super(parameters);
	}
	
	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			long playerId) {
	
		Result result = new Result(true, "Played the village");
		try {
			result = ((ActionBase)CardFactory.createCard(parameters.get(0))).playCard(dominionModel, playerId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
