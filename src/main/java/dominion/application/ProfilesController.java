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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import dominion.application.profile.ProfileViewDTO;
import dominion.database.dao.UserDAO;
import dominion.game.user.User;

public class ProfilesController extends AnchorPane {

	private LoginManager loginManager;
	@FXML
	private ListView<ProfileViewDTO> profiles;
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
		initProfileList();
	}

	@FXML
	private void add(ActionEvent evt) {

	}

	@FXML
	private void delete(ActionEvent evt) throws InterruptedException {
		if (loginManager.getProfileSelected() != null) {
			ProfileDeleteDialog deleteDialog = new ProfileDeleteDialog();
			deleteDialog.show();
		}
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}

	private void initProfileList() {
		try (UserDAO userDao = new UserDAO()) {
			Collection<User> users = userDao.getAllUsers();
			ObservableList<ProfileViewDTO> profileData = FXCollections
					.observableArrayList();
			for (User user : users) {
				profileData.add(new ProfileViewDTO(user));
			}
			profiles.setItems(profileData);
		} catch (Exception e) {
			e.printStackTrace();
		}

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
						loginManager.setProfileSelected(newVal);
					}
				});

		profiles.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				profiles.requestFocus();
			}
		});
	}

	private class ProfileDeleteDialog {

		private Stage deleteStage;

		public ProfileDeleteDialog() {
			deleteStage = new Stage();

			BorderPane deletePane = new ProfilesController.ProfileDeleteDialog.DeletePane(
					deleteStage);
			Scene deleteScene = new Scene(deletePane);
			deleteScene.getStylesheets().add(
					getClass().getResource("login.css").toExternalForm());
			deleteStage.setScene(deleteScene);
			deleteStage.sizeToScene();
			deleteStage.centerOnScreen();
			deleteStage.initModality(Modality.APPLICATION_MODAL);
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
						+ loginManager.getProfileSelected().getName() + "?");

				delete.setOnMouseClicked(new EventHandler<MouseEvent>() {
					public void handle(MouseEvent evt) {
						System.out.println("delete user: "
								+ loginManager.getProfileSelected().getName());
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
