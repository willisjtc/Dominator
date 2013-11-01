package dominion.application.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import dominion.application.IObserver;
import dominion.application.manager.IViewManager;
import dominion.application.manager.UserManager;

public class ProfilesController extends AnchorPane implements IObserver {

	@FXML
	private AnchorPane profilePane;
	@FXML
	private HBox buttonHBox;
	@FXML
	private Button addProfile;
	@FXML
	private Button removeUser;
	
	private IViewManager viewManager;
	private UserListView userList;
	

	public ProfilesController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"profiles.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}

		userList = new UserListView(viewManager);
		UserManager.instance.registerObserver(this);
		initProfileList();
	}

	@FXML
	private void add(ActionEvent evt) {
		AddUserDialog addDialog = new AddUserDialog();
		addDialog.show();
	}

	public void addDeleteOption() {
		removeUser = new Button("Delete");
		removeUser.setMaxWidth(Double.MAX_VALUE);
		removeUser.setMaxHeight(Double.MAX_VALUE);
		removeUser.setPrefSize(1000, 1000);
		removeUser.setOnAction(new EventHandler<ActionEvent> () {
			public void handle(ActionEvent evt) {
				if (userList.getUserSelected() != null) {
					UserDeleteDialog deleteDialog = new UserDeleteDialog();
					deleteDialog.show();
				}
			}
		});
		addProfile.setPrefSize(1000, 1000);
		HBox.setHgrow(removeUser, Priority.ALWAYS);
		HBox.setHgrow(addProfile, Priority.ALWAYS);
		HBox.setHgrow(removeUser, Priority.ALWAYS);
		HBox.setHgrow(addProfile, Priority.ALWAYS);
		buttonHBox.getChildren().add(removeUser);
	}
	
	public void removeProfilePane() {
		this.getChildren().remove(profilePane);
		AnchorPane.setTopAnchor(userList.getUserList(), 0.0);
		AnchorPane.setBottomAnchor(userList.getUserList(), 0.0);
		AnchorPane.setLeftAnchor(userList.getUserList(), 0.0);
	}

	@Override
	public void update() {
		System.out.println("Profiles Controller: updated");
		userList.refreshList(UserManager.instance.getAllUsers());
	}
	
	public void setViewManager(IViewManager viewManager) {
		this.viewManager = viewManager;
		userList.setViewManager(viewManager);
	}

	private void initProfileList() {
		userList.refreshList(UserManager.instance.getAllUsers());
		this.getChildren().add(0, userList.getUserList());
		AnchorPane.setTopAnchor(userList.getUserList(), 0.0);
		AnchorPane.setBottomAnchor(userList.getUserList(), 0.0);
	}
	
	private class UserDeleteDialog {

		private Stage deleteStage;

		public UserDeleteDialog() {
			deleteStage = new Stage();

			BorderPane deletePane = new ProfilesController.UserDeleteDialog.DeletePane(
					deleteStage);
			Scene deleteScene = new Scene(deletePane);
			deleteScene.getStylesheets().add(
					getClass().getResource("login.css").toExternalForm());
			deleteStage.setScene(deleteScene);
			deleteStage.sizeToScene();
			deleteStage.centerOnScreen();
			deleteStage.initModality(Modality.APPLICATION_MODAL);
			deleteStage.setResizable(false);
		}

		public void show() {
			deleteStage.showAndWait();
		}

		private class DeletePane extends BorderPane {

			@FXML
			private Label deleteLabel;
			@FXML
			private Button delete;
			@FXML
			private Button cancel;

			public DeletePane(final Stage parent) {

				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
						"delete_dialog.fxml"));
				fxmlLoader.setRoot(this);
				fxmlLoader.setController(this);

				try {
					fxmlLoader.load();
				} catch (Exception e) {
					e.printStackTrace();
				}

				deleteLabel.setText("Delete user "
						+ userList.getUserSelected().getUsername() + "?");

				delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent evt) {
						UserManager.instance.removeUser(userList.getUserSelected().getUsername());
						parent.close();
						update();
					}
				});

				cancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent evt) {
						parent.close();
					}
				});
			}
		}
	}
}
