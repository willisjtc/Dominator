package dominion.application.command;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.game.DominionModel;
import dominion.game.Player;


public class StartGameCommand extends Command {

	public StartGameCommand() {
		super("");
	}
	
	public StartGameCommand(String parameters) {
		super(parameters);
	}
	
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, Player player) {
		Result result = dominionModel.startGame();
		gameOutput.appendText("\nPlayer: " + dominionModel.getPlayerTurn());
		return result;
	}
}
