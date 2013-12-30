/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.application.controller;

import dominion.application.IObserver;
import dominion.cards.CardFactory;
import dominion.cards.treasure.Treasures;
import dominion.cards.victory.VictoryCards;
import dominion.game.DominionModel;
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

/**
 *
 * @author jonathan
 */
public class SupplyPileController extends AnchorPane implements IObserver {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(SupplyPileController.class.getName());
    
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
    
    public SupplyPileController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("supply_pile_controller.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading supply pile controller");
        }
    }
    
    public void initController(DominionModel model) {
        this.model = model;
        this.model .registerObserver(this);
        
        initView();
    }
    
    private void initView() {
        
        goldCountLabel.setText("" + model.getTreasures().getGoldCount());
        silverCountLabel.setText("" + model.getTreasures().getSilverCount());
        copperCountLabel.setText("" + model.getTreasures().getCopperCount());
        provinceCountLabel.setText("" + model.getVictoryCards().getProvinceCount());
        duchyCountLabel.setText("" + model.getVictoryCards().getDuchyCount());
        estateCountLabel.setText("" + model.getVictoryCards().getEstateCount());
        
        if (model.kingdomCardInGame(CardFactory.witch)) {
            witchLabel = new Label("Witch:");
            witchCountLabel = new Label("" + model.getCurses().size());
            
            supplyPileHolder.add(witchLabel, colCount - 1, rowCount);
            supplyPileHolder.add(witchCountLabel, colCount, rowCount);
            
            rowCount++;
        }
    }
    
    @Override
    public void update() {
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
}
