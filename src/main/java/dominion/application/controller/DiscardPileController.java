package dominion.application.controller;

import dominion.application.IObserver;
import dominion.game.DominionModel;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author jonathan
 */
public class DiscardPileController extends AnchorPane implements IObserver {
    
    private static Logger log = LogManager.getLogManager().getLogger(DiscardPileController.class.getName());
    
    @FXML
    private ImageView discardImage;
    @FXML
    private Button previousCard;
    @FXML 
    private Button nextCard;
    @FXML
    private Label discardCount;
    
    private DominionModel model;
    
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
    
    public void initController(DominionModel model) {
        this.model = model;
        this.model.registerObserver(this);
        
        initView();
    }
    
    private void initView() {
//        discardImage.setFitHeight(0);
//        discardImage.setFitWidth(0);
    }

    @Override
    public void update() {
    }
}
