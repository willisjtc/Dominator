package dominion.application.controller;

import java.util.LinkedList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dominion.application.IObserver;
import dominion.application.model.SingleGameSettings;
import dominion.cards.Card;

public class MainOverviewTab extends Tab implements IObserver {

	@FXML public AnchorPane content;
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
	
	private SingleGameSettings gameSettings;
	
	private List<ImageView> overviewImages;
	
	public MainOverviewTab() { }
	
	public void initializeController(SingleGameSettings gameSettings) {
		this.gameSettings = gameSettings;
		this.gameSettings.registerObserver(this);
		
		initView();
	}
	
	private void initView() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main_overview_tab.fxml"));
		fxmlLoader.setRoot(content);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.setText("Overview Game");
		this.setContent(content);
		
		initOverviewImages();
	}
	
	private void resetOverviewImages() {
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
}
