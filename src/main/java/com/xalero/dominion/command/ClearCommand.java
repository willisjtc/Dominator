package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.utils.Result;


public class ClearCommand extends Command {

	public ClearCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			long playerId) {
		Result result = new Result(true, "");
		
		gameOutput.clear();
		
		return result;
	}

}
