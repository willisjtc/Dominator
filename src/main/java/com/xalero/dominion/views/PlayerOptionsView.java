package com.xalero.dominion.views;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import com.xalero.dominion.IUniqueObserver;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.server.model.Player;

public class PlayerOptionsView extends AnchorPane implements IUniqueObserver {

	private Logger log = LogManager.getLogManager().getLogger(PlayerOptionsView.class.getName());
	
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
	
	
	public PlayerOptionsView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_options_view.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			log.log(Level.WARNING, "Error loading fxml file for PlayerOptionsDisplay.java", e);
		}
	}
	
	public void initController() {
		initView();
	}
	
	private void initView() {
		buyCount.setText("--");
		actionCount.setText("--");
		moneyCount.setText("--");
		scoreCount.setText("--");
	}
	
	@Override
	public void update(String event) {
		Player curPlayer = dominionModel.getPlayerById(playerId);
		buyCount.setText("" + curPlayer.getBuyCount());
		actionCount.setText("" + curPlayer.getActionCount());
		moneyCount.setText("" + curPlayer.getMoneyCount());
		scoreCount.setText("" + curPlayer.getScore());
	}

	@Override
	public Long getUniqueId() {
		return playerId;
	}
}
