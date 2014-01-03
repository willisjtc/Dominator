/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.application.controller;

import dominion.application.IObserver;
import dominion.cards.Card;
import dominion.game.DominionModel;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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
    
    public void initController(DominionModel model) {
        this.model = model;
        this.model.registerObserver(this);
        
        initView();
    }
    
    private void initView() {
        displayCards();
    }
    
    private void displayCards() {
        for (Card card : model.getPlayer(0).getHand()) {
            ImageView imageView = new ImageView(card.getCardImage());
//            javafx.geometry.Rectangle2D viewportRect = new javafx.geometry.Rectangle2D(10, 10, 10, 10);
//            imageView.setViewport(viewportRect);
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
