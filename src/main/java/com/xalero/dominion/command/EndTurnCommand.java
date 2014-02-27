package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.utils.Result;

public class EndTurnCommand extends Command {

	public EndTurnCommand() {
		super("");
	}
	
	public EndTurnCommand(String parameters) {
		super(parameters);
	}
	
	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			long playerId) {
		Result result = dominionModel.endTurn(playerId);
		gameOutput.appendText("\n" + result.getMessage());
		return result;
	}

}
