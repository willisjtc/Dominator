package dominion.application.command;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.cards.Card;
import dominion.game.DominionModel;
import dominion.game.Player;

public class DisplayCommand extends Command {

	public DisplayCommand(String parameters) {
		super(parameters);
	}

	
	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel,
			Player player) {
		Result result = new Result(true, "");
		
		switch (parameters.get(0)) {
		case "discard":
			if (parameters.get(1) != null && parameters.get(1).equals("pile")) {
				gameOutput.appendText("\nDiscard Pile: ");
				for (Card card : player.getDiscardPile()) {
					gameOutput.appendText("\n\t" + card);
				}
			}
			break;
		case "hand":
			gameOutput.appendText("\nHand:");
			for (Card card : player.getHand()) {
				gameOutput.appendText("\n\t" + card);
			}
			break;
		}
		player.getDiscardPile();
		
		return result;
	}

}
