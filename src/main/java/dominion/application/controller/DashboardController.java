package dominion.application.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

public class DashboardController extends SplitPane {

	private ViewManager viewManager;
	
	public DashboardController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
}
