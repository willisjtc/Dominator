package com.xalero.dominion.views;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.client.model.SimpleSpecificPlayer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PlayerOptionsView extends AnchorPane {

	private Logger log = LogManager.getLogManager().getLogger(PlayerOptionsView.class.getName());
	
	@FXML
	private Label buyCount;
	@FXML 
	private Label actionCount;
	@FXML 
	private Label moneyCount;
	
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
	}
	
	public void update(SimpleSpecificPlayer specificPlayer) {
		buyCount.setText("" + specificPlayer.getBuyCount());
		actionCount.setText("" + specificPlayer.getActionCount());
		moneyCount.setText("" + specificPlayer.getMoney());
	}
}
