package com.xalero.dominion.controller.ai;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.controller.terminal.DiscardPileController;
import com.xalero.dominion.controller.terminal.PlayersCardsController;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.views.KingdomCardsView;
import com.xalero.dominion.views.PlayerOptionsView;
import com.xalero.dominion.views.PlayerTurnsView;
import com.xalero.dominion.views.SupplyPilesView;

public class AIController extends AnchorPane implements IUniqueObserver {

	private static final Logger log = Logger.getLogger(AIController.class.getCanonicalName());
	
	@FXML
	private TextArea gameOutput;
	@FXML
	private KingdomCardsView kingdomCardsView;
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
	
	public AIController(DominionModel dominionModel, Long playerId) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ai_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error loading fxml for AI Controller", e);
		}
		
		dominionModel.registerObserver(this, playerId);
	}

	@Override
	public void update(String event) {
		
	}

	@Override
	public Long getUniqueId() {
		return playerId;
	}
}
