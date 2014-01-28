package dominion.application.command;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.game.DominionModel;
import dominion.game.Player;

public class ClearCommand extends Command {

	public ClearCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			Player player) {
		Result result = new Result(true, "");
		
		gameOutput.clear();
		
		return result;
	}

}
