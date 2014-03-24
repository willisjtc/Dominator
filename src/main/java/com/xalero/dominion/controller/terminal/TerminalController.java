package com.xalero.dominion.controller.terminal;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.client.model.SimpleModel;
import com.xalero.dominion.client.model.SimpleSpecificPlayer;
import com.xalero.dominion.command.CommandHandler;
import com.xalero.dominion.events.DominionEvent;
import com.xalero.dominion.events.DominionMessage;
import com.xalero.dominion.server.model.DominionEventHandler;
import com.xalero.dominion.views.KingdomCardsView;
import com.xalero.dominion.views.PlayerOptionsView;
import com.xalero.dominion.views.PlayerTurnsView;
import com.xalero.dominion.views.SupplyPilesView;


/**
 *
 * @author jonathan
 */
public class TerminalController extends AnchorPane implements IUniqueObserver {
    
    private static final Logger log = Logger.getLogger(TerminalController.class.getCanonicalName());
    
    @FXML
    private TextArea gameOutput;
    @FXML
    private TextField gameInput;
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
    
    private CommandHandler commandHandler;
    private long playerId;
    
    public TerminalController(DominionEventHandler dominionEventReceiver, Long playerId) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("terminal_controller.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            log.log(Level.WARNING, "Error loading fxml for Terminal Controller", e);
        }
        
        gameOutput.setEditable(false);
        gameOutput.appendText("Welcome to the Game");
        
        this.playerId = playerId;
        
        initView(dominionEventReceiver);
    }
    
    public void initView(DominionEventHandler dominionEventHandler) {
//      dominionModel.registerObserver(this, playerId);
//      dominionModel.getPlayerById(playerId);
      
    	commandHandler = new CommandHandler(gameOutput, dominionEventHandler, playerId);
      
    	Gson gson = new GsonBuilder().create();
    	
    	DominionMessage message = new DominionMessage(DominionEvent.KINGDOM_CARD_LIST, "");
    	kingdomCardsView.initController(dominionEventHandler.receiveEvent(gson.toJson(message)));
    	message = new DominionMessage(DominionEvent.CURSES, "");
    	supplyPilesView.initController(Integer.parseInt(dominionEventHandler.receiveEvent(gson.toJson(message))));
    	
        playerTurnsView.initController();
        playerOptionsView.initController();
        discardPileController.initController();    
        
        gameOutput.setFocusTraversable(false);
        gameInput.setFocusTraversable(true);
        gameInput.requestFocus();
    }
    
    @FXML
    private void keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleUserInput(gameInput.getText());
            gameInput.clear();
        }
    }
    
    private void handleUserInput(String text) {
    	commandHandler.handleUserInput(text, playerId);
    }
    
    public long getCurrentPlayerId() {
    	return playerId;
    }

    @Override
    public void update(String event) {
        Gson gson = new GsonBuilder().create();
        DominionMessage e = gson.fromJson(event, DominionMessage.class);
        switch (e.getEvent()) {
        case DISPLAY :
        	gameOutput.appendText("\n" + e.getValue());
        	break;
        case DOMINION_MODEL :
        	SimpleModel simpleModel = gson.fromJson(e.getValue(), SimpleModel.class);
        	kingdomCardsView.update(simpleModel.getKingdomCards());
        	supplyPilesView.update(simpleModel.getSimpleTreasures(), simpleModel.getSimpleVictoryCards(), simpleModel.getCurses());
        	playerTurnsView.update(simpleModel.getSimplePlayers(), simpleModel.getPlayerTurn());
        	break;
        case PLAYER_MODEL :
        	SimpleSpecificPlayer specificPlayer = gson.fromJson(e.getValue(), SimpleSpecificPlayer.class);
        	playersCardsController.update(specificPlayer);
        	playerOptionsView.update(specificPlayer);
        	discardPileController.update(specificPlayer);
        	break;
    	default :
    		break;
        }
    }
    
    @Override
    public Long getUniqueId() {
    	return playerId;
    }
}