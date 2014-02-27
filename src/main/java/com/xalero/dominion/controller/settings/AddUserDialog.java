package com.xalero.dominion.controller.settings;

import com.xalero.dominion.manager.UserManager;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AddUserDialog {
	private Stage addStage;
	
	public AddUserDialog() {
		addStage = new Stage();
		
		BorderPane addPane = new AddPane(addStage);
		Scene addScene = new Scene(addPane);
		addScene.getStylesheets().add(
				getClass().getResource("login.css").toExternalForm());
		addStage.setScene(addScene);
		addStage.sizeToScene();
		addStage.centerOnScreen();
		addStage.initModality(Modality.APPLICATION_MODAL);
		addStage.setResizable(false);
	}
	
	private class AddPane extends BorderPane {
		@FXML private TextField username;
		@FXML private TextField password;
		@FXML private TextField confirmPassword;
		@FXML private Button add;
		@FXML private Button cancel;
		
		public AddPane(final Stage parent) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("add_dialog.fxml"));
			fxmlLoader.setRoot(this);
			fxmlLoader.setController(this);
			
			try {
				fxmlLoader.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			add.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent evt) {
					addUser(parent);
				}
			});
			
			cancel.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent evt) {
					parent.close();
				}
			});
		
			confirmPassword.setOnKeyTyped(new EventHandler<KeyEvent>() {
				public void handle(KeyEvent event) {
					addUser(parent);
				};
			});
			
			Platform.runLater(new Runnable() {
				public void run() {
					username.requestFocus();
				}
			});
			
		}
		
		public void addUser(final Stage parent) {
			if (username.getText() != null && !username.getText().equals("") &&
					password.getText() != null && !password.getText().equals("") &&
					confirmPassword.getText() != null && !confirmPassword.getText().equals("") &&
					password.getText().equals(confirmPassword.getText())) {
					UserManager.INSTANCE.addUser(username.getText(), password.getText());
					parent.close();
			}
		}
	}
	
	public void show() {
		addStage.showAndWait();
	}
}