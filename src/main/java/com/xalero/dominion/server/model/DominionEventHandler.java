package com.xalero.dominion.server.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.cards.action.ActionBase;
import com.xalero.dominion.cards.action.KingdomCard;
import com.xalero.dominion.dto.BuyCardDTO;
import com.xalero.dominion.dto.PlayCardDTO;
import com.xalero.dominion.dto.PlayerIdDto;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.utils.Result;

public class DominionEventHandler {

	private DominionModel dominionModel;
	
	public DominionEventHandler(DominionModel dominionModel) {
		this.dominionModel = dominionModel;
	}
	
	public String receiveEvent(String event) {
		String resultStr = "";
		
		Gson gson = new GsonBuilder().create();
		DominionMessage playerMessage = gson.fromJson(event, DominionMessage.class);
		switch (playerMessage.getEvent()) {
		case PLAY_CARD:
			PlayCardDTO playCardDto = gson.fromJson(playerMessage.getValue(), PlayCardDTO.class);
			Card cardToPlay = CardFactory.createCard(playCardDto.getCardName());
            Result result = ((ActionBase) cardToPlay).playCard(playCardDto.getParameters(), 
            												   dominionModel, 
            												   playCardDto.getPlayerId());
            resultStr = gson.toJson(result);
            
            DominionMessage message = new DominionMessage(DominionEvent.DISPLAY, result.getMessage());
//            dominionModel.notifyObserver(playCardDto.getPlayerId(), gson.toJson(message));
			break;
		case BUY_CARD :
			BuyCardDTO buyDto = gson.fromJson(playerMessage.getValue(), BuyCardDTO.class);
			result = dominionModel.buyCard(buyDto.getPlayerId(), buyDto.getCardName());
			resultStr = gson.toJson(result);
			
			message = new DominionMessage(DominionEvent.DISPLAY, result.getMessage());
			dominionModel.notifyObserver(buyDto.getPlayerId(), gson.toJson(message));
//			dominionModel.notifyObservers();
			break;
		case END_TURN:
			PlayerIdDto playerIdDto = gson.fromJson(playerMessage.getValue(), PlayerIdDto.class);
			result = dominionModel.endTurn(playerIdDto.getPlayerId());
			resultStr = gson.toJson(result);
			message = new DominionMessage(DominionEvent.DISPLAY, "Player Turn: " + dominionModel.getCurrentPlayer().getPlayerName());
			dominionModel.notifyObservers(new Gson().toJson(message));
			dominionModel.notifyObservers();
			break;
		case KINGDOM_CARD_LIST:
			Collection<KingdomCard> kingdomCards = dominionModel.getKingdomCards();
			List<String> kingdomCardNames = new ArrayList<>();
			for (KingdomCard card : kingdomCards) {
				kingdomCardNames.add(card.toString());
			}
			resultStr = gson.toJson(kingdomCardNames);
			break;
		case CURSES:
			resultStr = "" + dominionModel.getCurses().size();
			break;
		case DISCARD_PILE:
			playerIdDto = gson.fromJson(playerMessage.getValue(), PlayerIdDto.class);
			StringBuilder cardNames = new StringBuilder();
			Collection<Card> discardPile = dominionModel.getPlayerById(playerIdDto.getPlayerId()).getDiscardPile();
			for (Card discard : discardPile) {
				cardNames.append(discard.toString() + "\n");
			}
			message = new DominionMessage(DominionEvent.DISPLAY, cardNames.toString());
			dominionModel.notifyObserver(playerIdDto.getPlayerId(), gson.toJson(message));
			break;
		case HAND:
			playerIdDto = gson.fromJson(playerMessage.getValue(), PlayerIdDto.class);
			// display player hand
			cardNames = new StringBuilder();
			Collection<Card> hand = dominionModel.getPlayerById(playerIdDto.getPlayerId()).getHand();
			for (Card cardInHand : hand) {
				cardNames.append(cardInHand.toString() + "\n");
			}
			message = new DominionMessage(DominionEvent.DISPLAY, cardNames.toString());
			dominionModel.notifyObserver(playerIdDto.getPlayerId(), gson.toJson(message));
			break;
		default:
			break;
		}
		return resultStr;
	}
}
