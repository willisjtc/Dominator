package dominion.application.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
import dominion.application.IObserver;
import dominion.application.model.SingleGameSettings;
import dominion.cards.Card;
import dominion.cards.CardFactory;

public class BaseTab extends Tab implements IObserver {

	@FXML private AnchorPane content;
	@FXML private ListView<Card> baseCardList;
	@FXML private ImageView baseCardImageView;
	@FXML private ImageView customCardImage1;
	@FXML private ImageView customCardImage2;
	@FXML private ImageView customCardImage3;
	@FXML private ImageView customCardImage4;
	@FXML private ImageView customCardImage5;
	@FXML private ImageView customCardImage6;
	@FXML private ImageView customCardImage7;
	@FXML private ImageView customCardImage8;
	@FXML private ImageView customCardImage9;
	@FXML private ImageView customCardImage10;
	
	private SingleGameSettings gameSettings;
	
	private List<ImageView> miniImages;
	
	
	public BaseTab() {	}
	
	public void initializeController(SingleGameSettings gameSettings) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("base_tab.fxml"));
		fxmlLoader.setRoot(content);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.gameSettings = gameSettings;
		this.gameSettings.registerObserver(this);
		
		initView();
	}
	
	private void initView() {
		this.setText("Base");
		this.setContent(content);
		
		initBaseListView();
		initMiniCardImages();
	}
	
	private void initBaseListView() {
		ObservableList<Card> observableBaseCardList = FXCollections.observableArrayList();
		observableBaseCardList.addAll(CardFactory.getBaseKingdomCards());
		baseCardList.setItems(observableBaseCardList);
		
		ChangeListener<Boolean> listener = new ChangeListener<Boolean>() {
			@Override
			public void changed(final ObservableValue<? extends Boolean> observable,
								Boolean oldValue, Boolean newValue) {
				int currentCount = gameSettings.getCustomCardCount() + (newValue ? 1 : -1);
				if (currentCount > 10) {
		            Platform.runLater(new Runnable() {
		                @Override
		                public void run() {
		                    ((BooleanProperty) observable).set(false);
		                }
		            });
		        } else {
		        	gameSettings.cardToggled(baseCardList.getItems().toArray(new Card[0]));
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
	
	private void resetMiniImages() {
		
		for (ImageView miniImageView : miniImages) {
			miniImageView.setImage(null);
		}
		for (Card card : gameSettings.getCustomCards()) {
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
	
	private void initMiniCardImages() {
		miniImages = new LinkedList<ImageView>();
		miniImages.add(customCardImage1);
		miniImages.add(customCardImage2);
		miniImages.add(customCardImage3);
		miniImages.add(customCardImage4);
		miniImages.add(customCardImage5);
		miniImages.add(customCardImage6);
		miniImages.add(customCardImage7);
		miniImages.add(customCardImage8);
		miniImages.add(customCardImage9);
		miniImages.add(customCardImage10);
	}

	@Override
	public void update() {
		resetMiniImages();
	}
}
