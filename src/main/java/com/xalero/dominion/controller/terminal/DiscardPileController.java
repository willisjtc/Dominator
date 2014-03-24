package com.xalero.dominion.controller.terminal;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import com.xalero.dominion.cards.Card;
import com.xalero.dominion.client.model.SimpleSpecificPlayer;

/**
 *
 * @author jonathan
 */
public class DiscardPileController extends AnchorPane {
    
    private static Logger log = LogManager.getLogManager().getLogger(DiscardPileController.class.getName());
    
    @FXML
    private ImageView discardImage;
    @FXML
    private Button previousCard;
    @FXML 
    private Button nextCard;
    @FXML
    private Label discardCount;
    
    public DiscardPileController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("discard_pile_controller.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            log.log(Level.WARNING, "Error loading DiscardPileController fxml", e);
        }
    }
    
    public void initController() {
        initView();
    }
    
    private void initView() {
//        discardImage.setFitHeight(0);
//        discardImage.setFitWidth(0);
    }

    public void update(SimpleSpecificPlayer specificPlayer) {
    }
}
