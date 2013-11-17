package dominion.application.controller;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dominion.application.model.PlayerOptions;
import dominion.cards.Card;
import dominion.cards.CardFactory;
import dominion.cards.expansion.Expansion;

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
	
	@FXML private RadioButton randomRadioButton;
	@FXML private RadioButton customRadioButton;
	
	@FXML private ListView<Expansion> expansionList;
	@FXML private ListView<Card> customCardList;
	@FXML private ListView<Card> randomCardList;
	@FXML private ImageView overviewImageDisplay;
	
	@FXML private ListView<Card> baseCardList;
	@FXML private ImageView baseCardImageView;
	
	private final IntegerProperty cardsChosenCount = new SimpleIntegerProperty(0);
	private final ToggleGroup radioToggleGroup = new ToggleGroup();
	
	public SingleGameSetupController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single_game_setup_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		initRadioButtons();
		initExpansionListView();
		initBaseListView();
		initCustomCardListView();
		initRandomCardListView();
	}
	
	private void initRadioButtons() {
		randomRadioButton.setToggleGroup(radioToggleGroup);
		randomRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
			}
		});
		randomRadioButton.setSelected(true);
		customRadioButton.setToggleGroup(radioToggleGroup);
	}
	
	private void initExpansionListView() {
		ObservableList<Expansion> observableExpansionList = FXCollections.observableArrayList();
		observableExpansionList.addAll(Expansion.values());
		expansionList.setItems(observableExpansionList);
		
		ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
			}
		};
		for (Expansion expansion : Expansion.values()) {
			expansion.selectedProperty().addListener(listener);
			expansion.setSelected(true);
		}
		
		Callback<Expansion, ObservableValue<Boolean>> checkBoxCallback = new Callback<Expansion, ObservableValue<Boolean>>() {
			@Override
			public BooleanProperty call(Expansion expansion) {
				return expansion.selectedProperty();
			}
		};
		
		StringConverter<Expansion> expansionToStringConverter = new StringConverter<Expansion>() {
			@Override
			public Expansion fromString(String expansion) {
				try {
					return Expansion.valueOf(expansion);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return null;
			}

			@Override
			public String toString(Expansion expansion) {
				return expansion.toString();
			}
			
		};
		
		Callback<ListView<Expansion>, ListCell<Expansion>> listViewCallback = CheckBoxListCell.forListView(checkBoxCallback, expansionToStringConverter);
		expansionList.setCellFactory(listViewCallback);
		
		
		expansionList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Expansion>() {
			@Override
			public void changed(ObservableValue<? extends Expansion> observable,
					Expansion oldValue, Expansion newValue) {
				overviewImageDisplay.setImage(newValue.getExpansionImage());
			}
		});
	}
	
	private void initBaseListView() {
		ObservableList<Card> observableBaseCardList = FXCollections.observableArrayList();
		observableBaseCardList.addAll(CardFactory.getBaseKingdomCards());
		baseCardList.setItems(observableBaseCardList);
		
		ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {
				cardsChosenCount.set(cardsChosenCount.get() + (newValue ? 1 : -1));
				if (cardsChosenCount.get() > 10) {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                    ((BooleanProperty) observable).set(false);
		                }
		            });
		        } else {
		    		ObservableList<Card> observableChosenCardsList = FXCollections.observableArrayList();
		        	for (Card card : baseCardList.getItems()) {
		        		if (card.selectedProperty().get()) {
		        			observableChosenCardsList.add(card);
		        		}
		        	}
		        	customCardList.setItems(observableChosenCardsList);
		        }
			}
		};
		for (Card card : observableBaseCardList) {
			card.selectedProperty().addListener(listener);
		}
		
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
		baseCardList.setCellFactory(listViewCallback);
		
		
		baseCardList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
			@Override
			public void changed(ObservableValue<? extends Card> observable,
					Card oldValue, Card newValue) {
				baseCardImageView.setImage(newValue.getCardImage());
			}
		});
	}
	
	private void initCustomCardListView() {
		customCardList.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				System.out.println("oldValue: " + oldValue);
				System.out.println("newValue: " + newValue);
			}
			
		});
		customCardList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
			@Override
			public void changed(ObservableValue<? extends Card> observable,
					Card oldValue, Card newValue) {
				if (newValue != null) {
					overviewImageDisplay.setImage(newValue.getCardImage());
				} else {
					overviewImageDisplay.setImage(null);
				}
			}
		});
	}

	private void initRandomCardListView() {
		randomCardList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Card>() {
			@Override
			public void changed(ObservableValue<? extends Card> observable,
					Card oldValue, Card newValue) {
				if (newValue != null) {
					overviewImageDisplay.setImage(newValue.getCardImage());
				} else {
					overviewImageDisplay.setImage(null);
				}
			}
		});
	}
}
