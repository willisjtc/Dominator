package dominion.application.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import dominion.application.IObserver;
import dominion.application.model.CardSet;
import dominion.application.model.SingleGameSettings;
import dominion.cards.Card;
import dominion.cards.CardUtils;

public class CardOverviewTab extends Tab implements IObserver{
	
	@FXML private AnchorPane content;
	
	@FXML private RadioButton randomRadioButton;
	@FXML private RadioButton customRadioButton;
	
	@FXML private ListView<Card> customCardList;
	@FXML private ListView<Card> randomCardList;
	
	@FXML private ImageView overviewImageDisplay;
	
	private SingleGameSettings gameSettings;
	private final ToggleGroup radioToggleGroup = new ToggleGroup();
	
	public CardOverviewTab() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("card_overview_tab.fxml"));
		fxmlLoader.setRoot(content);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setText("Overview");
		this.setContent(content);
		
		initRadioButtons();
		initCustomCardListView();
		initRandomCardListView();
	}
	
	public void setGameSettings(SingleGameSettings settings) {
		gameSettings = settings;
		gameSettings.registerObserver(this);
		randomRadioButton.setSelected(true);
	}
	
	private void initRadioButtons() {
		randomRadioButton.setToggleGroup(radioToggleGroup);
		randomRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					gameSettings.setCardSet(CardSet.RANDOM);
				}
			}
		});
		customRadioButton.setToggleGroup(radioToggleGroup);
		customRadioButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue == true) {
					gameSettings.setCardSet(CardSet.CUSTOM);
				}
			}
		});
		radioToggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable,
					Toggle oldValue, Toggle newValue) {
			}
		});
	}
	
	private void initCustomCardListView() {
		ObservableList<Card> obsCustomCardList = FXCollections.observableArrayList();
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

	@Override
	public void update() {
		customCardList.setItems(FXCollections.observableArrayList(gameSettings.getCustomCards()));
	}
}
