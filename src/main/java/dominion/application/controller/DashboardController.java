package dominion.application.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import dominion.application.manager.IViewManager;
import dominion.application.model.DashboardOptions;

public class DashboardController extends AnchorPane implements IViewManager {

	@FXML private AnchorPane leftPane;
	@FXML private AnchorPane rightPane;
	@FXML private SplitPane splitPane;
	@FXML private ListView<DashboardOptions> dashboardList;
	
	private ProfileController profileController;
	private SingleGameSetupController singleGameSetupController;
	private IViewManager viewManager;
	
	public DashboardController(IViewManager viewManager) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("dashboard_controller.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		profileController = new ProfileController();
		singleGameSetupController = new SingleGameSetupController();
		this.viewManager = viewManager;
		
		initListView();
	}
	
	private void initListView() {
		ObservableList<DashboardOptions> dashboardItems = FXCollections.observableArrayList();
		dashboardItems.add(DashboardOptions.PROFILE_MANAGEMENT);
		dashboardItems.add(DashboardOptions.SINGLE_PLAYER);
		dashboardItems.add(DashboardOptions.MULTIPLAYER);
		
		dashboardList.setItems(dashboardItems);
		
		dashboardList.setCellFactory(new Callback<ListView<DashboardOptions>, ListCell<DashboardOptions>>() {
			@Override
			public ListCell<DashboardOptions> call(ListView<DashboardOptions> list) {
				return new DashboardOptionCell();
			}
		});
		dashboardList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<DashboardOptions>() {
			public void changed(ObservableValue<? extends DashboardOptions> obsValue,
								DashboardOptions oldVal, DashboardOptions newVal) {
				if (newVal.equals(DashboardOptions.PROFILE_MANAGEMENT)) {
					splitPane.getItems().set(1, profileController);
				} else if (newVal.equals(DashboardOptions.SINGLE_PLAYER)) {
					splitPane.getItems().set(1, singleGameSetupController);
				}
			}
		});
	}
	
	private static class DashboardOptionCell extends ListCell<DashboardOptions> {
		@Override
		public void updateItem(DashboardOptions item, boolean empty) {
			super.updateItem(item, empty);
			if (!empty) {
				this.setText(item.getDisplayName());
			}
		}
	}
	
	public void setViewManager(IViewManager viewManager) {
		this.viewManager = viewManager;
	}

	@Override
	public void authenticated() { }

	@Override
	public void startGame() { }

	@Override
	public void exitGame() {
		viewManager.exitGame();
	}

	@Override
	public void logout() {
		viewManager.logout();
	}

	@Override
	public void exitApplication() {
		viewManager.exitApplication();
		
	}

	@Override
	public void setDisplay(AnchorPane pane) {

	}
}
