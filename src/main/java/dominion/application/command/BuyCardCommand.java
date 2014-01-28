package dominion.application.command;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.cards.CardFactory;
import dominion.game.DominionModel;
import dominion.game.Player;

public class BuyCardCommand extends Command {

	public BuyCardCommand(String parameters) {
		super(parameters);
	}

	@Override
	protected Result execute(TextArea gameOutput, DominionModel dominionModel, Player player) {
		Result result = new Result(false, "");
		try {
			String cardToBuy = parameters.get(0);
			switch (cardToBuy) {
			case "council":
			case "throne":
				if (parameters.size() == 2) {
					cardToBuy = cardToBuy.concat(" " + parameters.get(1));
				}
				break;
			}
			result = dominionModel.buyCard(player, CardFactory.createCard(cardToBuy));
			gameOutput.appendText("\n" + result.getMessage());
		} catch (Exception e) {
			gameOutput.appendText("\nSorry, that card doesn't exist");
		}
		
		return result;
	}
}
