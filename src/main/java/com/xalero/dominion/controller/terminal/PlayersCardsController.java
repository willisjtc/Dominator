/*
s * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.controller.terminal;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.client.model.SimpleSpecificPlayer;

/**
 *
 * @author jonathan
 */
public class PlayersCardsController extends AnchorPane {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(PlayersCardsController.class.getName());
    
    @FXML
    private HBox playersCardsContainer;
    
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
    
    public void initController(Collection<String> hand) {
        initView(hand);
    }
    
    private void initView(Collection<String> hand) {
        displayCards(hand);
    }
    
    private void displayCards(Collection<String> hand) {
        playersCardsContainer.getChildren().clear();
        for (String card : hand) {
        	Card cardInstance = CardFactory.createCard(card);
            ImageView imageView = new ImageView(cardInstance.getCardImage());
            imageView.setPreserveRatio(true);
            imageView.setFitHeight(70);
            playersCardsContainer.getChildren().add(imageView);
        }   
    }

    public void update(SimpleSpecificPlayer specificPlayer) {
        displayCards(specificPlayer.getHand());
    }
}
