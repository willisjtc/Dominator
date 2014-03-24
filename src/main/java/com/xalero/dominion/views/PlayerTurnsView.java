/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.views;

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

import com.xalero.dominion.client.model.SimplePlayer;

/**
 *
 * @author jonathan
 */
public class PlayerTurnsView extends AnchorPane {
    
    private final static Logger logger = LogManager.getLogManager().getLogger(PlayerTurnsView.class.getName());
    
    @FXML
    private GridPane playersTurnDisplay;
    
    public PlayerTurnsView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_turns_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading players turn view", e);
        }
    }
    
    public void initController() {
        initView();
    }
    
    private void initView() {
//        updatePlayerDisplay();
        
    }
    
    private void updatePlayerDisplay(List<SimplePlayer> players, int playerTurn) {
        playersTurnDisplay.setPrefWidth(USE_COMPUTED_SIZE);
        if (playersTurnDisplay.getChildren().size() == players.size() + 1) {
            playersTurnDisplay.getChildren().remove(1, playersTurnDisplay.getChildren().size());
        }
        for (int i = 0; i < players.size(); i++) {
            Label playerLabel = new Label("Player: " + players.get(i).getName());
            if (players.get(i).getTurnNumber() == playerTurn) {
                playerLabel.setFont(Font.font(Font.getDefault().getName(), FontWeight.BOLD, 13));
            } else {
                playerLabel.setFont(Font.font(Font.getDefault().getName(), FontWeight.NORMAL, 12));
            }
            playersTurnDisplay.add(playerLabel, 0, i + 1);
        }
    }

    public void update(List<SimplePlayer> players, int playerTurn) {
        updatePlayerDisplay(players, playerTurn);
    }
}
