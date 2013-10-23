package dominion.application;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import dominion.application.profile.ProfileViewDTO;
import dominion.database.dao.UserDAO;
import dominion.game.user.User;

public class ProfilesController extends AnchorPane {

	private LoginManager loginManager;
	@FXML private ListView<ProfileViewDTO> profiles;
	
	public ProfilesController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("profiles.fxml"));
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
		this.setOnKeyReleased(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent evt) {
				System.out.println("hello");
				evt.consume();
			}
		});
		this.addEventHandler(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			public void handle(KeyEvent evt) {
				System.out.println("hello");
				evt.consume();
			}
		});
	}

	public void setLoginManager(LoginManager loginManager) {
		this.loginManager = loginManager;
	}
	
	private void initProfileList() {
		try (UserDAO userDao = new UserDAO()) {
			Collection<User> users = userDao.getAllUsers();
			ObservableList<ProfileViewDTO> profileData = FXCollections.observableArrayList();
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
		
		profiles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProfileViewDTO>() {
			public void changed(ObservableValue<? extends ProfileViewDTO> oValue, ProfileViewDTO oldVal, ProfileViewDTO newVal) {
			}
		});
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
