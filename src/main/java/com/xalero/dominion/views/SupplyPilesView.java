/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.views;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.client.model.SimpleTreasures;
import com.xalero.dominion.client.model.SimpleVictoryCards;

/**
 *
 * @author jonathan
 */
public class SupplyPilesView extends AnchorPane {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(SupplyPilesView.class.getName());
    
    @FXML private GridPane supplyPileHolder;
    
    @FXML private Label title;
    @FXML private Label goldLabel;
    @FXML private Label silverLabel;
    @FXML private Label copperLabel;
    @FXML private Label provinceLabel;
    @FXML private Label duchyLabel;
    @FXML private Label estateLabel;
    
    int rowCount = 7;
    int colCount = 1;
    private Label witchLabel;
    private boolean cursesInGame;
    
    @FXML private Label goldCountLabel;
    @FXML private Label silverCountLabel;
    @FXML private Label copperCountLabel;
    @FXML private Label provinceCountLabel;
    @FXML private Label duchyCountLabel;
    @FXML private Label estateCountLabel;
    private Label witchCountLabel;
    
    public SupplyPilesView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("supply_piles_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading supply pile controller");
        }
    }
    
    public void initController(int curseCount) {
    	if (curseCount > 0) {
    		cursesInGame = true;
    	} else {
    		cursesInGame = false;
    	}
        initView(curseCount);
    }
    
    private void initView(int curseCount) {
    	witchLabel = new Label("Curses:");
    	witchCountLabel = new Label("");
    	if (cursesInGame) {
    		witchCountLabel.setText("" + curseCount);	
    	}
    	
    	supplyPileHolder.add(witchLabel, colCount - 1, rowCount);
    	supplyPileHolder.add(witchCountLabel, colCount, rowCount);
    }
    
    public void update(SimpleTreasures treasures, SimpleVictoryCards victoryCards, int curseCount) {
        goldCountLabel.setText("" + treasures.getGolds());
        silverCountLabel.setText("" + treasures.getSilvers());
        copperCountLabel.setText("" + treasures.getCoppers());
        provinceCountLabel.setText("" + victoryCards.getProvinces());
        duchyCountLabel.setText("" + victoryCards.getDuchies());
        estateCountLabel.setText("" + victoryCards.getEstates());
        
        if (cursesInGame) {
            witchCountLabel.setText("" + curseCount);
        }
    }
}
