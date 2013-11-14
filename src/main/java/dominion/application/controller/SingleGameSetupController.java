package dominion.application.controller;

import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dominion.application.model.PlayerOptions;
import dominion.cards.Card;
import dominion.cards.CardFactory;

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
		ObservableList<Card> observableCardList = FXCollections.observableArrayList();
		observableCardList.addAll(CardFactory.getBaseKingdomCards());
		cards.setItems(observableCardList);
		
		Callback<Card, ObservableValue<Boolean>> checkBoxCallback = new Callback<Card, ObservableValue<Boolean>>() {
			@Override
			public BooleanProperty call(Card card) {
				return card.selectedProperty();
			}
		};
		
		StringConverter<Card> cardToStringConverter = new StringConverter<Card>() {
			@Override
			public Card fromString(String card) {
				try {
					return CardFactory.createCard(card);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public String toString(Card card) {
				return card.toString();
			}
			
		};
		
		Callback<ListView<Card>, ListCell<Card>> listViewCallback = CheckBoxListCell.forListView(checkBoxCallback, cardToStringConverter);
		cards.setCellFactory(listViewCallback);
		cards.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
			@Override
			public void changed(ObservableValue<? extends Card> obsValue,
					Card oldValue, Card newValue) {
				cardImageView.setImage(newValue.getCardImage());
			}
		});
	}
}
