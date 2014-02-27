/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.controller.terminal;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.cards.Card;
import com.xalero.dominion.model.DominionModel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author jonathan
 */
public class PlayersCardsController extends AnchorPane implements IObserver {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(PlayersCardsController.class.getName());
    
    @FXML
    private HBox playersCardsContainer;
    
    private DominionModel model;
    
    private long playerId;
    
    public PlayersCardsController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_cards_controller.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading fxml: players_cards_controller.fxml");
        }
    }
    
    public void initController(DominionModel model, long playerId) {
        this.model = model;
        this.model.registerObserver(this);
        
        this.playerId = playerId;
        
        initView();
    }
    
    private void initView() {
        displayCards();
    }
    
    private void displayCards() {
        playersCardsContainer.getChildren().clear();
        for (Card card : model.getPlayerById(playerId).getHand()) {
            ImageView imageView = new ImageView(card.getCardImage());
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(70);
            playersCardsContainer.getChildren().add(imageView);
        }   
    }

    @Override
    public void update() {
        displayCards();
    }
}
