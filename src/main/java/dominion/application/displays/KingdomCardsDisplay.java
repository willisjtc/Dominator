/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominion.application.displays;

import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;
import com.xalero.dominion.IObserver;
import com.xalero.dominion.cards.action.KingdomCard;
import com.xalero.dominion.model.DominionModel;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.util.logging.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author jonathan
 */
public class KingdomCardsDisplay extends AnchorPane implements IObserver {
    
    private static final Logger logger = LogManager.getLogManager().getLogger(KingdomCardsDisplay.class.getName());
    
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
    
    private DominionModel model;
    
    
    public KingdomCardsDisplay() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("kingdom_cards_display.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        
        try {
            fxmlLoader.load();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Error loading kingdom card controller");
        }
    }
    
    public void initController(DominionModel model) {
        this.model = model;
        this.model.registerObserver(this);
        
        initView();
    }
    
    private void initView() {
        FontMetrics fontMetrics = Toolkit.getToolkit().getFontLoader().getFontMetrics(Font.getDefault());
        double minLength = 0;
        for (KingdomCard card : model.getKingdomCards()) {
            double cardNameLength = fontMetrics.computeStringWidth(card.toString());
            if (cardNameLength > minLength) {
                minLength = cardNameLength;
            }
        }
        
        KingdomCard[] kingdomCards = model.getKingdomCards().toArray(new KingdomCard[0]);
        
        
        kingdomCard1.setText(kingdomCards[0].toString() + ": ");
        kingdomCard2.setText(kingdomCards[1].toString() + ": ");
        kingdomCard3.setText(kingdomCards[2].toString() + ": ");
        kingdomCard4.setText(kingdomCards[3].toString() + ": ");
        kingdomCard5.setText(kingdomCards[4].toString() + ": ");
        kingdomCard6.setText(kingdomCards[5].toString() + ": ");
        kingdomCard7.setText(kingdomCards[6].toString() + ": ");
        kingdomCard8.setText(kingdomCards[7].toString() + ": ");
        kingdomCard9.setText(kingdomCards[8].toString() + ": ");
        kingdomCard10.setText(kingdomCards[9].toString() + ": ");
        
        kingdomCardCount1.setText("" + kingdomCards[0].getCardCount());
        kingdomCardCount2.setText("" + kingdomCards[1].getCardCount());
        kingdomCardCount3.setText("" + kingdomCards[2].getCardCount());
        kingdomCardCount4.setText("" + kingdomCards[3].getCardCount());
        kingdomCardCount5.setText("" + kingdomCards[4].getCardCount());
        kingdomCardCount6.setText("" + kingdomCards[5].getCardCount());
        kingdomCardCount7.setText("" + kingdomCards[6].getCardCount());
        kingdomCardCount8.setText("" + kingdomCards[7].getCardCount());
        kingdomCardCount9.setText("" + kingdomCards[8].getCardCount());
        kingdomCardCount10.setText("" + kingdomCards[9].getCardCount());
        
        kingdomCardHolder.setPrefWidth(minLength * 2);
    }
    
    @Override 
    public void update() {
        KingdomCard[] kingdomCards = model.getKingdomCards().toArray(new KingdomCard[0]);
        
        kingdomCardCount1.setText("" + kingdomCards[0].getCardCount());
        kingdomCardCount2.setText("" + kingdomCards[1].getCardCount());
        kingdomCardCount3.setText("" + kingdomCards[2].getCardCount());
        kingdomCardCount4.setText("" + kingdomCards[3].getCardCount());
        kingdomCardCount5.setText("" + kingdomCards[4].getCardCount());
        kingdomCardCount6.setText("" + kingdomCards[5].getCardCount());
        kingdomCardCount7.setText("" + kingdomCards[6].getCardCount());
        kingdomCardCount8.setText("" + kingdomCards[7].getCardCount());
        kingdomCardCount9.setText("" + kingdomCards[8].getCardCount());
        kingdomCardCount10.setText("" + kingdomCards[9].getCardCount());
    }
}
