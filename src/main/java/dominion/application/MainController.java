package dominion.application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import dominion.application.controller.DashboardController;
import dominion.application.controller.ViewManager;


public class MainController extends BorderPane implements ViewManager {
	
	@FXML private MenubarController menubarController;
	@FXML private ProfilesController profilesController;
	@FXML private LoginController loginController;
	@FXML private SplitPane splitPane;
	
	private DashboardController dashboardController;
	
	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		menubarController.setViewManager(this);
		profilesController.setViewManager(this);
		loginController.setViewManager(this);
	}

	@Override
	public void authenticated() {
		dashboardController = new DashboardController();
		dashboardController.setViewManager(this);
		this.getChildren().remove(splitPane);
		this.setCenter(dashboardController);
		this.requestLayout();
	}

	@Override
	public void startGame() {
	}

	@Override
	public void exitGame() {
	}

	@Override
	public void logout() {
		for (Node node : this.getChildren()) {
			this.getChildren().remove(node);
		}
		this.getChildren().add(menubarController);
		this.getChildren().add(splitPane);
		menubarController.requestLayout();
		splitPane.requestLayout();
		this.requestLayout();
	}
	
	public void exitApplication() {
		Platform.exit();
	}
}
