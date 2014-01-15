package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import dominion.application.model.PlayerType;
import dominion.application.model.SimplePlayerInfo;

public class PlayerRow extends PlayerEditableRow {

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
			playerDescription.setText("Human");
		} else {
			playerDescription.setText("Computer");
		}
		
		aiComboBox.disableProperty().set(true);
	}
	
	@FXML public void onMouseClicked() {
	}
}
