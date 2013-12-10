package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import dominion.application.model.PlayerType;
import dominion.game.ai.IDominionAI;

public class PlayerRow extends AnchorPane {

	@FXML private GridPane gridPane;
	@FXML private ComboBox<IDominionAI> aiComboBox;
	@FXML private Label playerDescription;
	@FXML private Button removeButton;
	
	public PlayerRow(PlayerType playerOption) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_row.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if (playerOption.equals(PlayerType.HUMAN)) {
			aiComboBox.setVisible(false);
			playerDescription.setText("Human");
		} else {
			playerDescription.setText("Computer");
		}
	}
	
	@FXML public void onMouseClicked() {
		System.out.println("removing row");
	}
}
