package dominion.application.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import dominion.application.GameSettingsModule;
import dominion.application.IObserver;
import dominion.application.model.SingleGameSettings;
import dominion.cards.Card;
import dominion.cards.CardFactory;

public class MainOverviewTab extends Tab implements IObserver, Initializable{

	@FXML private AnchorPane content;
	@FXML private VBox playersVBox;
	
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
	
	@Inject private SingleGameSettings gameSettings;
	
	private List<ImageView> overviewImages;
	
	public MainOverviewTab() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_overview_tab.fxml"));
		fxmlLoader.setRoot(content);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Injector injector = Guice.createInjector(new GameSettingsModule());
		injector.injectMembers(this);
		
		this.setText("Overview Game");
		this.setContent(content);
		this.gameSettings.registerObserver(this);
		
		initOverviewImages();
	}
	
	private void resetOverviewImages() {
		System.out.println("reset overview images");
		for (ImageView overviewImageView : overviewImages) {
			overviewImageView.setImage(null);
		}
		
		for (Card card : gameSettings.getSelectedCards()) {
			for (ImageView overviewImageView : overviewImages) {
				if (overviewImageView.getImage() == null) {
					overviewImageView.setImage(card.getCardImage());
					break;
				}
			}
		}
		
		overviewImage1.setImage(CardFactory.adventurer.getCardImage());
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

	@Override
	public void update() {
		resetOverviewImages();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
}
