package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.utils.Result;


public class DoNothingCommand extends Command {

	public DoNothingCommand() {
		super("");
	}
	
	public DoNothingCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, long playerId) {
		// Does nothing
		return new Result(true, "");
	}
}
