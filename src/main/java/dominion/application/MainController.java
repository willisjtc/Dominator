package dominion.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;


public class MainController extends BorderPane {
	
	@FXML private MenubarController menubarController;
	@FXML private ProfilesController profilesController;
	@FXML private LoginController loginController;
	@FXML private SplitPane splitPane;
	
	private UserSelector userSelector;

	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		userSelector = new UserSelector();
		loginController.setUserSelector(userSelector);
		profilesController.setUserSelector(userSelector);
	}
}
