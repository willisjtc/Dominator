package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import dominion.application.model.PlayerOptions;
import dominion.cards.Card;

public class SingleGameSetupController extends AnchorPane {

	@FXML private Tab playersTab;
	@FXML private HBox hboxOne;
	@FXML private ComboBox<PlayerOptions> comboBoxOne;
	@FXML private HBox hboxTwo;
	@FXML private ComboBox<PlayerOptions> comboBoxTwo;
	@FXML private HBox hboxThree;
	@FXML private ComboBox<PlayerOptions> comboBoxThree;
	@FXML private HBox hboxFour;
	@FXML private ComboBox<PlayerOptions> comboBoxFour;
	
	@FXML private Tab cardsTab;
	@FXML private Tab miscellaneousTab;
	
	@FXML private Tab baseTab;
	@FXML private Tab intrigueTab;
	@FXML private Tab hinterlandsTab;
	@FXML private Tab cornucopiaTab;
	@FXML private Tab alchemyTab;
	@FXML private Tab darkAgesTab;
	
	@FXML private ListView<Card> cards;
	@FXML private ImageView cardImageView;
	
	public SingleGameSetupController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single_game_setup_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// initialize combo boxes
	}
}
