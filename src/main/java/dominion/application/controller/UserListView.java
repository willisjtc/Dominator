package dominion.application.controller;

import java.util.Collection;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import dominion.application.manager.IViewManager;
import dominion.application.manager.UserManager;
import dominion.game.user.User;

public class UserListView {

	private User userSelected;
	private ListView<User> userList;
	private IViewManager viewManager;

	public UserListView(final IViewManager viewManager) {
		userList = new ListView<User>();
		this.viewManager = viewManager;
		setUserListHandlers();
	}
	
	public UserListView(final IViewManager viewManager, Collection<User> users) {
		userList = new ListView<User>();
		this.viewManager = viewManager;
		setUserListHandlers();
		refreshList(users);
	}
	
	public User getUserSelected() {
		return userSelected;
	}
	
	public ListView<User> getUserList() {
		return userList;
	}
	
	public void setViewManager(final IViewManager viewManager) {
		this.viewManager = viewManager;
	}
	
	private void setUserListHandlers() {
		userList.setCellFactory(new Callback<ListView<User>, ListCell<User>>() {
			@Override
			public ListCell<User> call(ListView<User> list) {
				return new ProfileViewCell();
			}
		});
		userList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
			public void changed(
					ObservableValue<? extends User> oValue,
					User oldVal, User newVal) {
				userSelected = newVal;
				if (UserManager.instance.getLoggedInUser() != null) {
					viewManager.setDisplay(new ProfileTab(newVal));
				}
			}
		});
		userList.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evt) {
				userList.requestFocus();
			}
		});
	}
	
	public void refreshList(Collection<User> users) {
		System.out.println("UserListView: refreshing list");
		ObservableList<User> userData = FXCollections.observableArrayList();
		for (User user : users) {
			System.out.println("\tuser: " + user.getUsername());
			userData.add(user);
		}
		userList.setItems(userData);
	}

	private static class ProfileViewCell extends ListCell<User> {
		@Override
		public void updateItem(User item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				this.setText(item.getUsername());
			}
		}
	}
}
