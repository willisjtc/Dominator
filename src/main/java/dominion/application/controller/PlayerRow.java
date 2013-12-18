package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;
import dominion.game.ai.IDominionAI;

public class PlayerRow extends PlayerEditableRow {

	@FXML protected ComboBox<IDominionAI> aiComboBox;
	
	public PlayerRow(PlayerType playerOption, SingleGameSettings gameSettings) {
		super(playerOption, gameSettings);
	}
	
	@Override
	protected void initView() {
		System.out.println("player row init view");
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
		System.out.println("removing row");
	}
}
