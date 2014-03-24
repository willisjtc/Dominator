package com.xalero.dominion.command;

import java.util.Scanner;

import javafx.scene.control.TextArea;

import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.utils.Result;


public class CommandHandler {
	
	private TextArea gameOutput;
	private DominionEventHandler dominionEventHandler;
	
	public CommandHandler(TextArea gameOutput, DominionEventHandler dominionEventHandler, Long playerId) {
		this.gameOutput = gameOutput;
		this.dominionEventHandler = dominionEventHandler;
	}
	
	public void setGameOutput(TextArea gameOutput) {
		this.gameOutput = gameOutput;
	}
	
	public void setDominionEventHandler(DominionEventHandler dominionEventHandler) {
		this.dominionEventHandler = dominionEventHandler;
	}
	
	public Result handleUserInput(String userInput, long playerId) {
		Command command = parseInput(userInput);
		Result result = command.execute(gameOutput, dominionEventHandler, playerId);
		
		return result;
	}
	
	public Command parseInput(String userInput) {
		try (Scanner scanner = new Scanner(userInput)) {
			if (scanner.hasNext()) {
				String input = scanner.next();
				switch(input) {
				case "buy":
					return new BuyCardCommand(scanner.nextLine());
				case "display":
					return new DisplayCommand(scanner.nextLine());
				case "clear":
					return new ClearCommand(scanner.nextLine());
				case "end":
					if (scanner.hasNext() && scanner.next().equals("turn")) {
						return new EndTurnCommand("");	
					}
				case "play":
					return new PlayCardCommand(scanner.nextLine());
				}
			}
		}
		
		return new DoNothingCommand();
	}
}
