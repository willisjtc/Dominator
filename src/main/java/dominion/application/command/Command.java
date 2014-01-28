package dominion.application.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.TextArea;
import dominion.application.utils.Result;
import dominion.game.DominionModel;
import dominion.game.Player;

public abstract class Command {
	protected List<String> parameters;
	protected int paramsAccepted;
	
	public Command(String parameters) {
		Collection<String> params = parseParameters(parameters);
		if (params != null) {
			this.parameters = new ArrayList<>(parseParameters(parameters));
		}
	}
	
	protected Collection<String> parseParameters(String parameters) {
		List<String> params = new ArrayList<String>();
		try (Scanner scanner = new Scanner(parameters)) {
			while (scanner.hasNext()) {
				params.add(scanner.next());
			}
		}
		return params;
	}
	
	abstract protected Result execute(TextArea gameOutput, DominionModel dominionModel, Player player);
}