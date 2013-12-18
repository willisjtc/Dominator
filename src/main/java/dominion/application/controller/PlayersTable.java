package dominion.application.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dominion.application.IObserver;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;

public class PlayersTable extends AnchorPane implements IObserver {

	@FXML private VBox playerTable;
	
	private SingleGameSettings gameSettings;
	
	public PlayersTable() {	}
	
	public void initializeController(SingleGameSettings gameSettings) {
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
		
		playerTable.getChildren().add(new PlayerEditableRow(PlayerType.COMPUTER, gameSettings));
		playerTable.getChildren().add(new Button("Test Button"));
		
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
