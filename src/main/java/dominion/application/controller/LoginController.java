package dominion.application.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import dominion.application.manager.IViewManager;
import dominion.application.manager.UserManager;
import dominion.game.user.User;

public class LoginController extends BorderPane {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button signInButton;

	private IViewManager viewManager;

	public LoginController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"login_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				username.requestFocus();
			}
		});
	}

	@FXML
	private void signInReleased(MouseEvent evt) {
		System.out.println("signInReleased");
		authenticateLogin();
	}

	@FXML
	private void enter(KeyEvent evt) {
		System.out.println(evt.getCharacter());
		if (evt.getCode().equals(KeyCode.ENTER)
			&& (this.isFocused() || username.isFocused()
				|| password.isFocused() || signInButton.isFocused())) {
			System.out.println("handle username and pasdfasdfassword");
			authenticateLogin();
		}
	}

	public void authenticateLogin() {
		String username = this.username.getText();
		User user = UserManager.instance.getUserByUsername(username);

		if (user != null && user.getUsername() != null
				&& user.getUsername().equals(username)
				&& password.getText().equals(password.getText())) {
			this.username.setText("");
			this.password.setText("");
			UserManager.instance.setLoggedInUser(user);
			viewManager.authenticated();
		}
	}

	public void setViewManager(IViewManager viewManager) {
		this.viewManager = viewManager;
	}

	public void requestFocus() {
		username.requestFocus();
	}
}
