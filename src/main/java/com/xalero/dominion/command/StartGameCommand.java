package com.xalero.dominion.command;

import javafx.scene.control.TextArea;

import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.utils.Result;



public class StartGameCommand extends Command {

	public StartGameCommand() {
		super("");
	}
	
	public StartGameCommand(String parameters) {
		super(parameters);
	}
	
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, long playerId) {
		Result result = dominionModel.startGame();
		gameOutput.appendText("\nPlayer Turn: " + dominionModel.getCurrentPlayer().getPlayerName());
		return result;
	}
}
