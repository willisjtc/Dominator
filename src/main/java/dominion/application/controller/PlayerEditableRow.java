package dominion.application.controller;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import dominion.application.IObserver;
import dominion.application.model.PlayerType;   
import dominion.application.RemoveRowHandler;
import dominion.game.ai.IDominionAI;

public class PlayerEditableRow extends AnchorPane implements IObserver {
	@FXML private GridPane gridPane;
	@FXML protected ComboBox<IDominionAI> aiComboBox;
	@FXML protected Label playerDescription;
	@FXML private Button removeButton;
	
	protected PlayerType playerType;
        private RemoveRowHandler removeRowHandler;
	
	public PlayerEditableRow(PlayerType playerType, RemoveRowHandler removeRowHandler) {
		this.playerType = playerType;
                this.removeRowHandler = removeRowHandler;
            
		initializeController();
	}
	
	protected final void initializeController() {
		initView();
	}
	
	protected void initView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_editable_row.fxml"));
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
	}
	
	@FXML public void onRemove() {
            removeRowHandler.removeRow();
	}

	@Override
	public void update() {
		
	}
}
