package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import dominion.application.IObserver;
import dominion.application.model.SingleGameSettings;

public class PlayersTab extends Tab implements IObserver {
	
	@FXML private AnchorPane content;
	@FXML private PlayersTable playerTable;
	
	private SingleGameSettings gameSettings;
	
	public PlayersTab() {
		
	}
	
	public void initializeController(SingleGameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.gameSettings.registerObserver(this);
		
		initView();
	}
	
	private void initView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_tab.fxml"));
		fxmlLoader.setRoot(content);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setText("Players");
		this.setContent(content);
		
		playerTable.initializeController(gameSettings);
	}

	@Override
	public void update() {
		
	}
}
