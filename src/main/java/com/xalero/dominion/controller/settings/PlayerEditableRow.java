package com.xalero.dominion.controller.settings;


import com.xalero.dominion.IObserver;
import com.xalero.dominion.RemoveRowHandler;
import com.xalero.dominion.ai.IDominionAI;
import com.xalero.dominion.model.PlayerType;
import com.xalero.dominion.model.SimplePlayerInfo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class PlayerEditableRow extends AnchorPane implements IObserver {
	@FXML private GridPane gridPane;
	@FXML protected ComboBox<IDominionAI> aiComboBox;
	@FXML protected Label playerDescription;
	@FXML private Button removeButton;
	
	protected SimplePlayerInfo playerInfo;
    private RemoveRowHandler removeRowHandler;
	
    public PlayerEditableRow(SimplePlayerInfo playerInfo) {
    	this.playerInfo = playerInfo;
    	this.removeRowHandler = null;
    	
    	initializeController();
    }
    
	public PlayerEditableRow(SimplePlayerInfo playerInfo, RemoveRowHandler removeRowHandler) {
		this.playerInfo = playerInfo;
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
		
		
		if (playerInfo.getPlayerType().equals(PlayerType.HUMAN)) {
			aiComboBox.setVisible(false);
			this.getChildren().remove(aiComboBox);
			playerDescription.setText(playerInfo.getDisplayName());
		} else {
			playerDescription.setText("Computer");
		}
	}
	
	public PlayerEditableRow hideRemoveButton() {
		removeButton.setVisible(false);
		return this;
	}
	
	@FXML public void onRemove() {
            removeRowHandler.removeRow();
	}

	@Override
	public void update() {
	}
}
