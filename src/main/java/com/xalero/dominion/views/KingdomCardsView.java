/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xalero.dominion.views;

import java.lang.reflect.Type;
import java.util.ArrayList;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import com.xalero.dominion.client.model.SimpleKingdomCard;

/**
 *
 * @author jonathan
 */
public class KingdomCardsView extends AnchorPane {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(KingdomCardsView.class.getName());
    
    @FXML private GridPane kingdomCardHolder;
    
    @FXML private Label title;
    @FXML private Label kingdomCard1;
    @FXML private Label kingdomCard2;
    @FXML private Label kingdomCard3;
    @FXML private Label kingdomCard4;
    @FXML private Label kingdomCard5;
    @FXML private Label kingdomCard6;
    @FXML private Label kingdomCard7;
    @FXML private Label kingdomCard8;
    @FXML private Label kingdomCard9;
    @FXML private Label kingdomCard10;
    
    @FXML private Label kingdomCardCount1;
    @FXML private Label kingdomCardCount2;
    @FXML private Label kingdomCardCount3;
    @FXML private Label kingdomCardCount4;
    @FXML private Label kingdomCardCount5;
    @FXML private Label kingdomCardCount6;
    @FXML private Label kingdomCardCount7;
    @FXML private Label kingdomCardCount8;
    @FXML private Label kingdomCardCount9;
    @FXML private Label kingdomCardCount10;
    
    public KingdomCardsView() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kingdom_cards_view.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading kingdom card controller");
        }
    }
    
    public void initController(String cards) {
    	Gson gson = new GsonBuilder().create();
    	Type listType = new TypeToken<ArrayList<String>>() {}.getType();
    	List<String> kingdomCards = gson.fromJson(cards, listType);
        initView(kingdomCards);
    }
    
    private void initView(List<String> kingdomCards) {
        FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
        double minLength = 0;
        for (String card : kingdomCards) {
            double cardNameLength = fontMetrics.computeStringWidth(card);
            if (cardNameLength > minLength) {
                minLength = cardNameLength;
            }
        }
        
        kingdomCard1.setText(kingdomCards.get(0) + ": ");
        kingdomCard2.setText(kingdomCards.get(1).toString() + ": ");
        kingdomCard3.setText(kingdomCards.get(2).toString() + ": ");
        kingdomCard4.setText(kingdomCards.get(3).toString() + ": ");
        kingdomCard5.setText(kingdomCards.get(4).toString() + ": ");
        kingdomCard6.setText(kingdomCards.get(5).toString() + ": ");
        kingdomCard7.setText(kingdomCards.get(6).toString() + ": ");
        kingdomCard8.setText(kingdomCards.get(7).toString() + ": ");
        kingdomCard9.setText(kingdomCards.get(8).toString() + ": ");
        kingdomCard10.setText(kingdomCards.get(9).toString() + ": ");
        
        kingdomCardHolder.setPrefWidth(minLength * 2);
    }
    
    public void update(List<SimpleKingdomCard> kingdomCards) {
        kingdomCardCount1.setText("" + kingdomCards.get(0).getCount());
        kingdomCardCount2.setText("" + kingdomCards.get(1).getCount());
        kingdomCardCount3.setText("" + kingdomCards.get(2).getCount());
        kingdomCardCount4.setText("" + kingdomCards.get(3).getCount());
        kingdomCardCount5.setText("" + kingdomCards.get(4).getCount());
        kingdomCardCount6.setText("" + kingdomCards.get(5).getCount());
        kingdomCardCount7.setText("" + kingdomCards.get(6).getCount());
        kingdomCardCount8.setText("" + kingdomCards.get(7).getCount());
        kingdomCardCount9.setText("" + kingdomCards.get(8).getCount());
        kingdomCardCount10.setText("" + kingdomCards.get(9).getCount());
    }
}
