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

import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.cards.CardFactory;
import com.xalero.dominion.server.model.DominionModel;

/**
 *
 * @author jonathan
 */
public class SupplyPilesView extends AnchorPane implements IUniqueObserver {
    
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
    
    @FXML private Label goldCountLabel;
    @FXML private Label silverCountLabel;
    @FXML private Label copperCountLabel;
    @FXML private Label provinceCountLabel;
    @FXML private Label duchyCountLabel;
    @FXML private Label estateCountLabel;
    private Label witchCountLabel;
    
    private DominionModel model;
    
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
    
    public void initController() {
        initView();
    }
    
    private void initView() {
        
//        goldCountLabel.setText("" + model.getTreasures().getGoldCount());
//        silverCountLabel.setText("" + model.getTreasures().getSilverCount());
//        copperCountLabel.setText("" + model.getTreasures().getCopperCount());
//        provinceCountLabel.setText("" + model.getVictoryCards().getProvinceCount());
//        duchyCountLabel.setText("" + model.getVictoryCards().getDuchyCount());
//        estateCountLabel.setText("" + model.getVictoryCards().getEstateCount());
//        
//        if (model.kingdomCardInGame(CardFactory.witch)) {
//            witchLabel = new Label("Curses:");
//            witchCountLabel = new Label("" + model.getCurses().size());
//            
//            supplyPileHolder.add(witchLabel, colCount - 1, rowCount);
//            supplyPileHolder.add(witchCountLabel, colCount, rowCount);
//            
//            rowCount++;
//        }
    }
    
    @Override
    public void update(String event) {
        goldCountLabel.setText("" + model.getTreasures().getGoldCount());
        silverCountLabel.setText("" + model.getTreasures().getSilverCount());
        copperCountLabel.setText("" + model.getTreasures().getCopperCount());
        provinceCountLabel.setText("" + model.getVictoryCards().getProvinceCount());
        duchyCountLabel.setText("" + model.getVictoryCards().getDuchyCount());
        estateCountLabel.setText("" + model.getVictoryCards().getEstateCount());
        
        if (model.kingdomCardInGame(CardFactory.witch)) {
            witchCountLabel.setText("" + model.getCurses().size());
        }
    }
    
    @Override
    public Long getUniqueId() {
    	return null;
    }
}
