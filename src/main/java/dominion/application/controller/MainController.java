package dominion.application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import dominion.application.manager.IViewManager;
import dominion.database.dao.InitializationDAO;


public class MainController extends BorderPane implements IViewManager, Initializable {
	
	@FXML private MenubarController menubarController;
	@FXML private LoginController loginController;
	@FXML private SplitPane splitPane;
	
	private DashboardController dashboardController;
	
	private Pane currentController;
	
	private String name;
	
	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		dashboardController = new DashboardController(this);
		menubarController.setViewManager(this);
		loginController.setViewManager(this);
		currentController = loginController;
		
		this.authenticated();
		
		try (InitializationDAO initDao = new InitializationDAO()) {
			initDao.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	@Override
	public void authenticated() {
		this.getChildren().remove(splitPane);
		this.setCenter(dashboardController);
	}

	@Override
	public void startGame() {
	}

	@Override
	public void exitGame() {
	}

	@Override
	public void logout() {
		this.getChildren().remove(dashboardController);
		this.setCenter(splitPane);
		menubarController.requestLayout();
		splitPane.requestLayout();
		this.requestLayout();
	}
	
	public void exitApplication() {
		Platform.exit();
	}
	
	public void setDisplay(AnchorPane pane) { }
	
	public void requestFocus() {
		currentController.requestFocus();
	}



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("initialize(" + location + ", " + resources + ")");
	}
}
