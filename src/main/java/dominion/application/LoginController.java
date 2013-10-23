package dominion.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class LoginController extends BorderPane {

	private LoginManager loginManager;
	
	public LoginController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML private void signInReleased(MouseEvent evt) {
		System.out.println("signInReleased");
	}
	
	@FXML private void enter(KeyEvent evt) {
		if (evt.getCode().equals(KeyCode.ENTER)) {
			System.out.println("handle username and password");
			
		}		
	}
	
	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
}
