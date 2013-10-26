package dominion.application;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import dominion.application.controller.ViewManager;
import dominion.application.user.views.ProfileViewDTO;
import dominion.game.user.User;

public class ProfilesController extends AnchorPane implements DObserver {

	private UserSelector userSelector;
	private UserManager userManager;
	private ViewManager viewManager;
	
	@FXML
	private ListView<ProfileViewDTO> profiles;
	@FXML
	private HBox buttonHBox;
	@FXML
	private Button addProfile;
	@FXML
	private Button removeProfile;

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

		// load profiles
		this.setFocused(true);
		userManager = new UserManager();
		userSelector = new UserSelector();
		initProfileList();
	}

	@FXML
	private void add(ActionEvent evt) {
		UserAddDialog addDialog = new UserAddDialog();
		addDialog.show();
	}

	@FXML
	private void delete(ActionEvent evt) throws InterruptedException {
		if (userSelector.getProfileSelected() != null) {
			UserDeleteDialog deleteDialog = new UserDeleteDialog();
			deleteDialog.show();
		}
	}
	
	public void addDeleteOption() {
		removeProfile = new Button("Delete");
		removeProfile.setMaxWidth(Double.MAX_VALUE);
		removeProfile.setMaxHeight(Double.MAX_VALUE);
		removeProfile.setPrefSize(1000, 1000);
		addProfile.setPrefSize(1000, 1000);
		HBox.setHgrow(removeProfile, Priority.ALWAYS);
		HBox.setHgrow(addProfile, Priority.ALWAYS);
		HBox.setHgrow(removeProfile, Priority.ALWAYS);
		HBox.setHgrow(addProfile, Priority.ALWAYS);
		buttonHBox.getChildren().add(removeProfile);
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	
	public void setUserSelector(UserSelector userSelector) {
		this.userSelector = userSelector;
	}

	public void update() {
		refreshProfileList();
	}
	
	public void setViewManager(ViewManager viewManager) {
		this.viewManager = viewManager;
	}

	private void initProfileList() {
		refreshProfileList();
		profiles.setCellFactory(new Callback<ListView<ProfileViewDTO>, ListCell<ProfileViewDTO>>() {
			@Override
			public ListCell<ProfileViewDTO> call(ListView<ProfileViewDTO> list) {
				return new ProfileViewCell();
			}
		});
		profiles.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<ProfileViewDTO>() {
					public void changed(
							ObservableValue<? extends ProfileViewDTO> oValue,
							ProfileViewDTO oldVal, ProfileViewDTO newVal) {
						userSelector.setProfileSelected(newVal);
					}
				});
		profiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				profiles.requestFocus();
			}
		});
	}
	
	public void refreshProfileList() {
		Collection<User> users = userManager.getAllUsers();
		ObservableList<ProfileViewDTO> profileData = FXCollections
				.observableArrayList();
		for (User user : users) {
			profileData.add(new ProfileViewDTO(user));
		}
		profiles.setItems(profileData);
	}

	private class UserAddDialog {
		private Stage addStage;
		
		public UserAddDialog() {
			addStage = new Stage();
			
			BorderPane addPane = new ProfilesController.UserAddDialog.AddPane(addStage);
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
						if (username.getText() != null && !username.getText().equals("") &&
							password.getText() != null && !password.getText().equals("") &&
							confirmPassword.getText() != null && !confirmPassword.getText().equals("") &&
							password.getText().equals(confirmPassword.getText())) {
							userManager.addUser(username.getText(), password.getText());
							parent.close();
							refreshProfileList();
						}
					}
				});
				
				cancel.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent evt) {
						parent.close();
					}
				});
			}
		}
		
		public void show() {
			addStage.showAndWait();
		}
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
			private Label deleteText;
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

				deleteText.setText("Delete user "
						+ userSelector.getProfileSelected().getName() + "?");

				delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent evt) {
						System.out.println("delete user: "
								+ userSelector.getProfileSelected().getName());
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

	private static class ProfileViewCell extends ListCell<ProfileViewDTO> {
		@Override
		public void updateItem(ProfileViewDTO item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				this.setText(item.getName());
			}
		}
	}	
}
