package dominion.application.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import dominion.application.manager.IViewManager;

public class DashboardController extends SplitPane implements IViewManager {

	@FXML private Accordion accordion;
	@FXML private TitledPane userManagement;
	@FXML private AnchorPane userManagementPane;
	@FXML private TitledPane gameSetup;
	@FXML private ProfilesController profilesController;
	
	private IViewManager viewManager;
	
	public DashboardController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		profilesController.addDeleteOption();
		profilesController.removeProfilePane();
		profilesController.setViewManager(this);
		
		AnchorPane.setLeftAnchor(profilesController, 0.0);
		AnchorPane.setRightAnchor(profilesController, 0.0);
		AnchorPane.setTopAnchor(profilesController, 0.0);
		AnchorPane.setBottomAnchor(profilesController, 0.0);
	}
	
	public void setViewManager(IViewManager viewManager) {
		this.viewManager = viewManager;
	}

	@Override
	public void authenticated() { }

	@Override
	public void startGame() { }

	@Override
	public void exitGame() {
		viewManager.exitGame();
	}

	@Override
	public void logout() {
		viewManager.logout();
	}

	@Override
	public void exitApplication() {
		viewManager.exitApplication();
		
	}

	@Override
	public void setDisplay(AnchorPane pane) {
		final int rightSide = 1;
		if (this.getItems().get(rightSide) != null) {
			this.getItems().remove(rightSide);
		}		
		this.getItems().add(rightSide, pane);
	}
}
