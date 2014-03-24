package com.xalero.dominion.controller.ai;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.ai.IDominionAI;
import com.xalero.dominion.controller.terminal.DiscardPileController;
import com.xalero.dominion.controller.terminal.PlayersCardsController;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.views.PlayerOptionsView;
import com.xalero.dominion.views.PlayerTurnsView;
import com.xalero.dominion.views.SupplyPilesView;

public class AIController extends AnchorPane implements IUniqueObserver {

	private static final Logger log = Logger.getLogger(AIController.class.getCanonicalName());
	
	@FXML
	private TextArea gameOutput;
	@FXML
	private SupplyPilesView supplyPilesView;
	@FXML
	private PlayerTurnsView playerTurnsView;
	@FXML
	private PlayersCardsController playersCardsController;
	@FXML
	private PlayerOptionsView playerOptionsView;
	@FXML
	private DiscardPileController discardPileController;
	
	private long playerId;
	
	private IDominionAI ai; 
	

	public AIController(DominionEventHandler dominionEventHandler, Long playerId, IDominionAI ai) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ai_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error loading fxml for AI Controller", e);
		}
		
		this.playerId = playerId;
		this.ai = ai;
		this.ai.setDominionEventHandler(dominionEventHandler);
		
    	Gson gson = new GsonBuilder().create();
    	
    	DominionMessage message = new DominionMessage(DominionEvent.KINGDOM_CARD_LIST, "");
    	message = new DominionMessage(DominionEvent.CURSES, "");
    	supplyPilesView.initController(Integer.parseInt(dominionEventHandler.receiveEvent(gson.toJson(message))));
    	
        playerTurnsView.initController();
        playerOptionsView.initController();
        discardPileController.initController();
	}

	@Override
	public void update(String event) {
		Gson gson = new GsonBuilder().create();
		DominionMessage e = gson.fromJson(event,  DominionMessage.class);
		switch (e.getEvent()) {
		case DISPLAY :
			gameOutput.appendText("\n" + e.getValue());
			break;
		default :
			ai.update(event);
		}
	}

	@Override
	public Long getUniqueId() {
		return playerId;
	}
}
