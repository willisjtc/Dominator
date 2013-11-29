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
import javafx.scene.control.ComboBox;
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
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dominion.application.model.PlayerOptions;
import dominion.cards.Card;
import dominion.cards.CardFactory;
import dominion.cards.CardUtils;
import dominion.game.ai.DominionAI;

public class SingleGameSetupController extends AnchorPane {

	@FXML private Tab mainOverviewTab;
	
	@FXML private Tab playersTab;
	@FXML private HBox hboxOne;
	@FXML private ComboBox<PlayerOptions> comboBoxOne;
	@FXML private ComboBox<DominionAI> aiComboBoxOne;
	@FXML private HBox hboxTwo;
	@FXML private ComboBox<PlayerOptions> comboBoxTwo;
	@FXML private ComboBox<DominionAI> aiComboBoxTwo;
	@FXML private HBox hboxThree;
	@FXML private ComboBox<PlayerOptions> comboBoxThree;
	@FXML private ComboBox<DominionAI> aiComboBoxThree;
	@FXML private HBox hboxFour;
	@FXML private ComboBox<PlayerOptions> comboBoxFour;
	@FXML private ComboBox<DominionAI> aiComboBoxFour;
	
	@FXML private Tab cardsTab;
	
	@FXML private Tab baseTab;
	@FXML private Tab intrigueTab;
	@FXML private Tab hinterlandsTab;
	@FXML private Tab cornucopiaTab;
	@FXML private Tab alchemyTab;
	@FXML private Tab darkAgesTab;
	
	@FXML private RadioButton randomRadioButton;
	@FXML private RadioButton customRadioButton;
	
	@FXML private ListView<Card> customCardList;
	@FXML private ListView<Card> randomCardList;
	@FXML private ImageView overviewImageDisplay;
	
	@FXML private ListView<Card> baseCardList;
	@FXML private ImageView baseCardImageView;
	
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
	
	@FXML private ImageView miniImage1;
	@FXML private ImageView miniImage2;
	@FXML private ImageView miniImage3;
	@FXML private ImageView miniImage4;
	@FXML private ImageView miniImage5;
	@FXML private ImageView miniImage6;
	@FXML private ImageView miniImage7;
	@FXML private ImageView miniImage8;
	@FXML private ImageView miniImage9;
	@FXML private ImageView miniImage10;
	
	private List<ImageView> miniImages;
	private List<ImageView> overviewImages;
	private ObservableList<Card> obsCustomCardList;
	
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
		
		
		
		initBaseListView();
		initCustomCardListView();
		initRandomCardListView();
		initOverviewImages();
		initRadioButtons();
		initMiniCardImages();
		initPlayersTab();
	}
	private void initPlayersTab() {
		ObservableList<PlayerOptions> playerOptions = FXCollections.observableArrayList();
		playerOptions.addAll(PlayerOptions.values());
		comboBoxOne.setItems(playerOptions);
		comboBoxTwo.setItems(playerOptions);
		comboBoxThree.setItems(playerOptions);
		comboBoxFour.setItems(playerOptions);
		
		comboBoxOne.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (comboBoxOne.getSelectionModel().getSelectedItem().equals(PlayerOptions.COMPUTER)) {
					aiComboBoxOne.setVisible(true);
				} else {
					aiComboBoxOne.setVisible(false);
				}
			}
		});
		comboBoxTwo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (comboBoxTwo.getSelectionModel().getSelectedItem().equals(PlayerOptions.COMPUTER)) {
					aiComboBoxTwo.setVisible(true);
				} else {
					aiComboBoxTwo.setVisible(false);
				}
			}
		});
		comboBoxThree.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (comboBoxThree.getSelectionModel().getSelectedItem().equals(PlayerOptions.COMPUTER)) {
					aiComboBoxThree.setVisible(true);
				} else {
					aiComboBoxThree.setVisible(false);
				}
			}
		});
		comboBoxFour.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (comboBoxFour.getSelectionModel().getSelectedItem().equals(PlayerOptions.COMPUTER)) {
					aiComboBoxFour.setVisible(true);
				} else {
					aiComboBoxFour.setVisible(false);
				}
			}
		});
		
		comboBoxOne.getSelectionModel().selectFirst();
		comboBoxTwo.getSelectionModel().selectFirst();
		comboBoxThree.getSelectionModel().selectFirst();
		comboBoxFour.getSelectionModel().selectFirst();
		
		aiComboBoxOne.setVisible(false);
		aiComboBoxTwo.setVisible(false);
		aiComboBoxThree.setVisible(false);
		aiComboBoxFour.setVisible(false);
	}
	private void initRadioButtons() {
		randomRadioButton.setToggleGroup(radioToggleGroup);
		randomRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				
			}
		});
		customRadioButton.setToggleGroup(radioToggleGroup);
		
		radioToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
				System.out.println("Toggle occurred");
				resetOverviewImages();
			}
		});
		randomRadioButton.setSelected(true);
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
		        	for (Card card : baseCardList.getItems()) {
		        		if (card.selectedProperty().get() && !obsCustomCardList.contains(card)) {
		        			obsCustomCardList.add(card);
		        		} else if (!card.selectedProperty().get()){
		        			obsCustomCardList.remove(card);
		        		}
		        	}
		        	resetMiniImages();
		        	resetOverviewImages();
		        }
			}
		};
		for (Card card : observableBaseCardList) {
			card.selectedProperty().addListener(listener);
		}
		
		///////////////
		// BUGGED - does not handle when checkbox has focus
		///////////////
//		baseCardList.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
//			@Override
//			public void handle(KeyEvent event) {
//				if (baseCardList.getSelectionModel().getSelectedItem().selectedProperty().getValue() == true) {
//					baseCardList.getSelectionModel().getSelectedItem().setSelected(false);
//				} else {
//					baseCardList.getSelectionModel().getSelectedItem().setSelected(true);
//				}
//			}
//		});
		
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
		obsCustomCardList = FXCollections.observableArrayList();
		customCardList.setItems(obsCustomCardList);
		customCardList.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				MultipleSelectionModel<Card> selectedCard = customCardList.selectionModelProperty().get();
				if (selectedCard == null || selectedCard.getSelectedItem() == null) {
					overviewImageDisplay.setImage(null);
					return;
				}
				overviewImageDisplay.setImage(selectedCard.getSelectedItem().getCardImage());
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
		randomizeRandomList();
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
		randomCardList.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				MultipleSelectionModel<Card> selectedCard = randomCardList.selectionModelProperty().get();
				if (selectedCard == null || selectedCard.getSelectedItem() == null) {
					overviewImageDisplay.setImage(null);
					return;
				}
				overviewImageDisplay.setImage(selectedCard.getSelectedItem().getCardImage());
			}
		});
		
		final ContextMenu randomListContextMenu = new ContextMenu();
		MenuItem randomizeMenuItem = new MenuItem("Randomize");
		randomizeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				randomizeRandomList();
			}
		});
		randomListContextMenu.getItems().add(randomizeMenuItem);
		
		MenuItem replaceCardMenuItem = new MenuItem("Replace");
		replaceCardMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				replaceRandomCard();
			}
		});
		randomListContextMenu.getItems().add(replaceCardMenuItem);
		randomCardList.setContextMenu(randomListContextMenu);
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
	private void initMiniCardImages() {
		miniImages = new LinkedList<ImageView>();
		miniImages.add(miniImage1);
		miniImages.add(miniImage2);
		miniImages.add(miniImage3);
		miniImages.add(miniImage4);
		miniImages.add(miniImage5);
		miniImages.add(miniImage6);
		miniImages.add(miniImage7);
		miniImages.add(miniImage8);
		miniImages.add(miniImage9);
		miniImages.add(miniImage10);
	}
	
	private void randomizeRandomList() {
		ObservableList<Card> observableCardList = FXCollections.observableArrayList();
		observableCardList.addAll(CardUtils.getRandomCardSet());
		int index = randomCardList.getSelectionModel().getSelectedIndex();
		randomCardList.setItems(observableCardList);
		if (index != -1) {
			randomCardList.getSelectionModel().select(index);
		}
	}
	
	private void replaceRandomCard() {
		int index = randomCardList.getSelectionModel().getSelectedIndex();
		Card randomCard = CardUtils.getRandomCard(randomCardList.getItems().toArray(new Card[0]));
		randomCardList.getItems().set(index, randomCard);
		randomCardList.getSelectionModel().select(index);
	}	
	private void resetMiniImages() {
		
		for (ImageView miniImageView : miniImages) {
			miniImageView.setImage(null);
		}
		for (Card card : obsCustomCardList) {
			if (card.selectedProperty().get()) {
				for (ImageView miniImageView : miniImages) {
					if (miniImageView.getImage() == null) {
						miniImageView.setImage(card.getCardImage());
						break;
					}
				}
			}
		}
	}
	private void resetOverviewImages() {
		System.out.println("resetOverviewImages");
		for (ImageView overviewImageView : overviewImages) {
			overviewImageView.setImage(null);
		}
		if (randomRadioButton.isSelected()) {
			for (Card card : randomCardList.getItems()) {
				System.out.println("card: " + card.toString());
				for (ImageView overviewImageView : overviewImages) {
					if (overviewImageView.getImage() == null) {
						overviewImageView.setImage(card.getCardImage());
						break;
					}
				}
			}
		} else {
			for (Card card : customCardList.getItems()) {
				for (ImageView overviewImageView : overviewImages) {
					if (overviewImageView.getImage() == null) {
						overviewImageView.setImage(card.getCardImage());
						break;
					}
				}
			}
		}
	}
}
