package dominion.application.displays;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.manager.PlayerManager;
import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.model.Player;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PlayerOptionsDisplay extends AnchorPane implements IObserver {

	private Logger log = LogManager.getLogManager().getLogger(PlayerOptionsDisplay.class.getName());
	
	@FXML
	private Label buyCount;
	@FXML 
	private Label actionCount;
	@FXML 
	private Label moneyCount;
	@FXML
	private Label scoreCount;
	
	private DominionModel dominionModel;
	private long playerId;
	
	
	public PlayerOptionsDisplay() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_options_display.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error loading fxml file for PlayerOptionsDisplay.java", e);
		}
	}
	
	public void initController(DominionModel dominionModel, long playerId) {
		this.dominionModel = dominionModel;
		this.dominionModel.registerObserver(this);
		
		this.playerId = playerId;
		
		initView();
	}
	
	private void initView() {
		buyCount.setText("--");
		actionCount.setText("--");
		moneyCount.setText("--");
		scoreCount.setText("--");
	}
	
	@Override
	public void update() {
		Player curPlayer = dominionModel.getPlayerById(playerId);
		buyCount.setText("" + curPlayer.getBuyCount());
		actionCount.setText("" + curPlayer.getActionCount());
		moneyCount.setText("" + curPlayer.getMoneyCount());
		scoreCount.setText("" + curPlayer.getScore());
	}
}
