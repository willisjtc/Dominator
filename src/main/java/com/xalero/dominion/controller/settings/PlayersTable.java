package com.xalero.dominion.controller.settings;

import com.xalero.dominion.IObserver;
import com.xalero.dominion.model.GameSettings;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayersTable extends AnchorPane implements IObserver {

	@FXML private VBox playerTable;
	
	private GameSettings gameSettings;
	
	public PlayersTable() {	}
	
	public void initializeController(GameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.gameSettings.registerObserver(this);
		
		initView();
	}
	
	private void initView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("players_table.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("anchor pane palyer table clicked");
				
			}
			
		});
		playerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("player table clicked");
				
			}
			
		});
	}
	
	@Override
	public void update() {
		
	}
}
