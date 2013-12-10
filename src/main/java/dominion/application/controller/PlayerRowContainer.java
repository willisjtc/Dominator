package dominion.application.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class PlayerRowContainer extends AnchorPane {
	
	@FXML private VBox playerRowsContainer;
	
	private List<PlayerRow> playerRows;
	
	public PlayerRowContainer() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("player_row_container.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		playerRows = new LinkedList<PlayerRow>();
	}
	
	public boolean addPlayerRow(PlayerRow playerRow) {
		return playerRows.add(playerRow);
	}
	
	public boolean removePlayerRow(PlayerRow playerRow) {
		return playerRows.remove(playerRow);
	}
	
	public void removeAllPlayerRows() {
		playerRows.clear();
	}
}
