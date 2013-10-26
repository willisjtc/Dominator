package dominion.application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import dominion.application.controller.ViewManager;
import dominion.game.user.User;

public class LoginController extends BorderPane {
	
	@FXML private TextField username;
	@FXML private PasswordField password;
	
	private UserManager userManager;
	private ViewManager viewManager;
	
	public LoginController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		userManager = new UserManager();
	}

	@FXML private void signInReleased(MouseEvent evt) {
		System.out.println("signInReleased");
		authenticateLogin();
	}
	
	@FXML private void enter(KeyEvent evt) {
		if (evt.getCode().equals(KeyCode.ENTER)) {
			System.out.println("handle username and password");
			authenticateLogin();
		}
	}
	
	public void authenticateLogin() {
		String username = this.username.getText();
		User user = userManager.getUserByUsername(username);
		if (user.getUsername().equals(username) && 
			password.getText().equals(password.getText())) {
			viewManager.authenticated();
		}
	}
	
	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
}
