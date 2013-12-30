/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.application.controller;

import dominion.application.IObserver;
import dominion.application.model.SingleGameSettings;
import dominion.game.DominionModel;
import dominion.game.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jonathan
 */
public class TerminalController extends AnchorPane implements IObserver {
    
    @FXML
    private TextArea gameOutput;
    @FXML
    private TextField gameInput;
    @FXML
    private KingdomCardController kingdomCardController;
    @FXML
    private SupplyPileController supplyPileController;
    
    private DominionModel dominionModel;
    
    private Player currentPlayer;
    
    public TerminalController(SingleGameSettings gameSettings) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("terminal_game_play.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        gameOutput.setEditable(false);
        gameOutput.appendText("Welcome to the Game");
        
        dominionModel = new DominionModel(gameSettings);
        dominionModel.registerObserver(this);
        
        kingdomCardController.initController(dominionModel);
        supplyPileController.initController(dominionModel);
    }
    
    private void beginGame() {
        dominionModel.startGame();
        currentPlayer = dominionModel.getPlayers().toArray(new Player[0])[0];
    }
    
    @FXML
    private void keyReleased(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            handleUserInput(gameInput.getText());
            gameInput.clear();
        }
    }
    
    private void handleUserInput(String text) {
        System.out.println("text: " + text);
        if (text.equals("start game")) {
            System.out.println("starting game");
            beginGame();
        }
    }

    @Override
    public void update() {
        if (dominionModel.getPlayer(dominionModel.getPlayerTurn()) != currentPlayer) {
            gameOutput.appendText("\n" + "Player turn: " + dominionModel.getPlayerTurn());
        }
    }
}