package dominion.application.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dominion.application.model.PlayerType;
import dominion.application.model.SingleGameSettings;
import dominion.cards.Card;
import dominion.cards.CardFactory;
import dominion.cards.CardUtils;

public class SingleGameSetupController extends AnchorPane {

	@FXML private Tab mainOverviewTab;
	
	@FXML private Tab playersTab;
	
	@FXML private VBox playersVBox;
	
	@FXML private Tab cardsTab;
	
	@FXML private BaseTab baseTab;
	@FXML private CardOverviewTab cardOverviewTab;
	@FXML private Tab intrigueTab;
	@FXML private Tab hinterlandsTab;
	@FXML private Tab cornucopiaTab;
	@FXML private Tab alchemyTab;
	@FXML private Tab darkAgesTab;
	
	@FXML private ImageView overviewImage1;
	@FXML private ImageView overviewImage2;
	@FXML private ImageView overviewImage3;
	@FXML private ImageView overviewImage4;
	@FXML private ImageView overviewImage5;
	@FXML private ImageView overviewImage6;
	@FXML private ImageView overviewImage7;
	@FXML private ImageView overviewImage8;
	@FXML private ImageView overviewImage9;
	@FXML private ImageView overviewImage10;
	
	private List<ImageView> overviewImages;
	private SingleGameSettings gameSettings; 
	

	public SingleGameSetupController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single_game_setup_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		gameSettings = new SingleGameSettings();
		baseTab.setGameSettings(gameSettings);
		cardOverviewTab.setGameSettings(gameSettings);
		
		// do not change the order
		initOverviewImages();
		initPlayersTab();
	}

	private void initOverviewImages() {
		overviewImages = new LinkedList<ImageView>();
		overviewImages.add(overviewImage1);
		overviewImages.add(overviewImage2);
		overviewImages.add(overviewImage3);
		overviewImages.add(overviewImage4);
		overviewImages.add(overviewImage5);
		overviewImages.add(overviewImage6);
		overviewImages.add(overviewImage7);
		overviewImages.add(overviewImage8);
		overviewImages.add(overviewImage9);
		overviewImages.add(overviewImage10);
	}
		
	private void initPlayersTab() {
		PlayerRow humanRow = new PlayerRow(PlayerType.HUMAN);
		PlayerRow computerRow = new PlayerRow(PlayerType.COMPUTER);
		playersVBox.getChildren().add(humanRow);
		playersVBox.getChildren().add(computerRow);
	}

	private void resetOverviewImages() {
//		for (ImageView overviewImageView : overviewImages) {
//			overviewImageView.setImage(null);
//		}
//		if (randomRadioButton.isSelected()) {
//			for (Card card : randomCardList.getItems()) {
//				for (ImageView overviewImageView : overviewImages) {
//					if (overviewImageView.getImage() == null) {
//						overviewImageView.setImage(card.getCardImage());
//						break;
//					}
//				}
//			}
//		} else {
//			for (Card card : customCardList.getItems()) {
//				for (ImageView overviewImageView : overviewImages) {
//					if (overviewImageView.getImage() == null) {
//						overviewImageView.setImage(card.getCardImage());
//						break;
//					}
//				}
//			}
//		}
	}
}
