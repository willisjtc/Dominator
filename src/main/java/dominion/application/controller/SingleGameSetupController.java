package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import dominion.application.GameSettingsModule;
import dominion.application.model.SingleGameSettings;

public class SingleGameSetupController extends AnchorPane {

	@FXML private MainOverviewTab mainOverviewTab;
	@FXML private Tab playersTab;
	@FXML private BaseTab baseCardsTab;
	@FXML private Tab cardsTab;
	@FXML private CardOverviewTab cardOverviewTab;
	
	@FXML private Tab intrigueTab;
	@FXML private Tab hinterlandsTab;
	@FXML private Tab cornucopiaTab;
	@FXML private Tab alchemyTab;
	@FXML private Tab darkAgesTab;

	@Inject private SingleGameSettings gameSettings; 
	

	public SingleGameSetupController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("single_game_setup_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Injector injector = Guice.createInjector(new GameSettingsModule());
		injector.injectMembers(this);		
	}
}
