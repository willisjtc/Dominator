/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.application.controller;

import dominion.application.IObserver;
import dominion.game.DominionModel;
import dominion.game.Player;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author jonathan
 */
public class PlayersTurnView extends AnchorPane implements IObserver {
    
    private final static Logger logger = LogManager.getLogManager().getLogger(PlayersTurnView.class.getName());
    
    @FXML
    private GridPane playersTurnDisplay;
    
    private List<Player> players;
    
    private DominionModel dominionModel;
    
    public PlayersTurnView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_turn_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading players turn view", e);
        }
    }
    
    public void initController(DominionModel dominionModel) {
        this.dominionModel = dominionModel;
        this.dominionModel.registerObserver(this);
        
        players = new LinkedList<>(dominionModel.getPlayers());
        
        initView();
    }
    
    private void initView() {
        updatePlayerDisplay();
        
    }
    
    private void updatePlayerDisplay() {
        playersTurnDisplay.setPrefWidth(USE_COMPUTED_SIZE);
        if (playersTurnDisplay.getChildren().size() == players.size() + 1) {
            playersTurnDisplay.getChildren().remove(1, playersTurnDisplay.getChildren().size());
        }
        for (int i = 0; i < players.size(); i++) {
            Label playerLabel = new Label("Player: " + players.get(i).getPlayerName());
            System.out.println("player turn: " + dominionModel.getPlayerTurn());
            if (dominionModel.getPlayerTurn() == i) {
                playerLabel.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 13));
            } else {
                playerLabel.setFont(Font.font(Font.getDefault().getName(), FontWeight.NORMAL, 12));
            }
            playersTurnDisplay.add(playerLabel, 0, i + 1);
        }
    }

    @Override
    public void update() {
        updatePlayerDisplay();
    }
    
}
