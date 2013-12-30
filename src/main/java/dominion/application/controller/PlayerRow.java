package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import dominion.application.model.PlayerType;

public class PlayerRow extends PlayerEditableRow {

	public PlayerRow(PlayerType playerOption) {
		super(playerOption, null);
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
		
		
		if (playerType.equals(PlayerType.HUMAN)) {
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
