package dominion.application;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;


public class MainController extends BorderPane {
	
	@FXML private MenubarController menubarController;
	@FXML private ProfilesController profilesController;
	@FXML private LoginController loginController;
	@FXML private SplitPane splitPane;
	
	private LoginManager loginManager;

	public MainController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		loginManager = new LoginManager();
		loginController.setLoginManager(loginManager);
		profilesController.setLoginManager(loginManager);
		
		this.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent evt) {
				System.out.println(evt.getCharacter());
				System.out.println("splitpane controller: " + splitPane.isFocused());
				System.out.println("profile controller: " + profilesController.isFocused());
				System.out.println("login controller: " + loginController.isFocused());
			}
		});
	}
}
