package dominion.application.command;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.game.DominionModel;
import dominion.game.Player;

public class DoNothingCommand extends Command {

	public DoNothingCommand() {
		super("");
	}
	
	public DoNothingCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, Player player) {
		// Does nothing
		return new Result(true, "");
	}
}
