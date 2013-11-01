package dominion.application.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import dominion.application.manager.UserManager;
import dominion.game.user.User;

public class ProfileTab extends AnchorPane {
	@FXML private Tab profileTab;
	@FXML private Label username;
	@FXML private ImageView userImageView;
	@FXML private Button changePhoto;
	
	private UserManager userManager;
	
	public ProfileTab(User user) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profile_view.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (user != null) {
			username.setText(user.getUsername());
			userImageView.setImage(user.getImage());
		}
		
		changePhoto.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent evt) {
				FileChooser fileChooser = new FileChooser();
				fileChooser.setTitle("Open Resource File");
				File userImageFile = fileChooser.showOpenDialog(new Stage());
				Image userImage = null;
				try {
					userImage = new Image(new FileInputStream(userImageFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
//				userManager.setUserImage(profileView.getUsername(), userImage);
				userImageView.setImage(userImage);
				
			}
		});
	}
	
	public void setUsername(String username) {
		this.username.setText(username);
	}
	
	public void setImage(Image image) {
		userImageView.setImage(image);
	}
}
