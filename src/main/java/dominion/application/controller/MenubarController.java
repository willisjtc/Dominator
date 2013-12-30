package dominion.application.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import dominion.application.manager.IViewManager;

public class MenubarController extends MenuBar {

    @FXML
    private MenuBar menubar;
    @FXML
    private Menu file;
    @FXML
    private Menu newMenu;
    @FXML
    private MenuItem user;
    @FXML
    private MenuItem logout;
    @FXML
    private MenuItem close;
    @FXML
    private Menu edit;
    @FXML
    private MenuItem delete;
    @FXML
    private MenuItem server;
    @FXML
    private Menu view;
    @FXML
    private Menu help;
    private IViewManager viewManager;

    public MenubarController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menubar.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        this.setUseSystemMenuBar(true);
    }

    @FXML
    private void onUser(ActionEvent evt) {
        new AddUserDialog().show();
        viewManager.requestFocus();
    }

    @FXML
    private void onLogout(ActionEvent evt) {
        viewManager.logout();
    }

    @FXML
    private void onClose(ActionEvent evt) {
        viewManager.exitApplication();
    }

    @FXML
    private void onDelete(ActionEvent evt) {
        System.out.println("onDelete");
    }

    @FXML
    private void selectServer(ActionEvent evt) {
        System.out.println("selectServer");
    }

    public void setViewManager(IViewManager viewManager) {
        this.viewManager = viewManager;
    }
}
