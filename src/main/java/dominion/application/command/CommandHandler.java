package dominion.application.command;

import java.util.Scanner;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.game.DominionModel;
import dominion.game.Player;

public class CommandHandler {
	
	private TextArea gameOutput;
	private DominionModel dominionModel;
	
	public CommandHandler(TextArea gameOutput, DominionModel dominionModel) {
		this.gameOutput = gameOutput;
		this.dominionModel = dominionModel;
	}
	
	public void setGameOutput(TextArea gameOutput) {
		this.gameOutput = gameOutput;
	}
	
	public void setDominionModel(DominionModel dominionModel) {
		this.dominionModel = dominionModel;
	}
	
	public Result handleUserInput(String userInput, Player player) {
		Command command = parseInput(userInput);
		Result result = command.execute(gameOutput, dominionModel, player);
		
		return result;
	}
	
	public Command parseInput(String userInput) {
		try (Scanner scanner = new Scanner(userInput)) {
			if (scanner.hasNext()) {
				String input = scanner.next();
				switch(input) {
				case "start":
					if (scanner.hasNext() && scanner.next().equals("game")) {
						return new StartGameCommand();
					}
					break;
				case "buy":
					return new BuyCardCommand(scanner.nextLine());
				case "display":
					return new DisplayCommand(scanner.nextLine());
				case "clear":
					return new ClearCommand(scanner.nextLine());
				}
			}
		}
		
		return new DoNothingCommand();
	}
}
