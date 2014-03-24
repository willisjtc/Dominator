package com.xalero.dominion.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.TextArea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.dto.PlayCardDTO;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.utils.Result;

public class PlayCardCommand extends Command {

	private int cardWordLength = 0; 
	
	public PlayCardCommand(String parameters) {
		super(null);
		
		if (parameters == null) {
			return;
		}
		List<String> parsedParameters = new ArrayList<>();
		this.parameters = new ArrayList<>();
		try (Scanner scanner = new Scanner(parameters)) {
			while (scanner.hasNext()) {
				String param = scanner.next();
				Card card = CardFactory.createCard(param);
				if (card != null) {
					parsedParameters.add(param);
				} else if (scanner.hasNext()){
					String nextParam = scanner.next();
					card = CardFactory.createCard(param + " " + nextParam);
					if (card != null) {
						parsedParameters.add(param + " " + nextParam);
					} else {
						parsedParameters.add(param);
						parsedParameters.add(nextParam);
					}
				} else {
					parsedParameters.add(param);
				}
			}
		}
		this.parameters = parsedParameters;
	}
	
	@Override
	protected Result execute(TextArea gameOutput, DominionEventHandler dominionEventHandler,
			long playerId) {

        Result result = new Result(false, "");

        Card cardToPlay = getCard();
        if (cardToPlay == null) {
            result.setSuccess(false);
            result.setMessage("failed to play card");
        } else {
        	if (cardWordLength == 1) {
        		parameters.remove(0);
        	} else {
        		parameters.remove(0);
        		parameters.remove(0);
        	}
        	
        	Gson gson = new GsonBuilder().create();
        	
        	PlayCardDTO playCardDto = new PlayCardDTO(cardToPlay.toString(), playerId, parameters);
        	DominionMessage protocol = new DominionMessage(DominionEvent.PLAY_CARD, gson.toJson(playCardDto));
        	String resultStr = dominionEventHandler.receiveEvent(gson.toJson(protocol));
            result = gson.fromJson(resultStr, Result.class);
        }

		return result;
	}

    private Card getCard() {
    	cardWordLength = 1;
        Card card = CardFactory.createCard(parameters.get(0));
        if (card == null && parameters.size() > 1) {
            card = CardFactory.createCard(parameters.get(0) + " " + parameters.get(1));
            cardWordLength = 2;
        }
        return card;
    }  
}
