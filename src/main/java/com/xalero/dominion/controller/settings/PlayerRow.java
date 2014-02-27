package com.xalero.dominion.controller.settings;

import com.xalero.dominion.manager.UserManager;
import com.xalero.dominion.model.PlayerType;
import com.xalero.dominion.model.SimplePlayerInfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class PlayerRow extends PlayerEditableRow {

	public PlayerRow(String displayName, SimplePlayerInfo playerInfo) {
		super(playerInfo, null);
	}
	
	public PlayerRow(SimplePlayerInfo playerInfo) {
		super(playerInfo, null);
	}
	
	@Override
	protected void initView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_row.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (playerInfo.getPlayerType().equals(PlayerType.HUMAN)) {
			aiComboBox.setVisible(false);
			playerDescription.setText(UserManager.INSTANCE.getCurrentUser().getUsername());
		} else {
			playerDescription.setText("Computer");
		}
		
		aiComboBox.disableProperty().set(true);
	}
	
	@Override
	public PlayerRow hideRemoveButton() {
		return this;
	}
	
	@FXML public void onMouseClicked() {
	}
}
