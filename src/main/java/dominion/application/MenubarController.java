package dominion.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class MenubarController extends MenuBar{
	@FXML private MenuBar menubar;
	@FXML private Menu file;
	@FXML private MenuItem close;
	@FXML private Menu edit;
	@FXML private MenuItem delete;
	@FXML private MenuItem server;
	@FXML private Menu view;
	@FXML private Menu help;
	
	public String test = "test";
	
	public MenubarController() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menubar.fxml"));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML private void onClose(ActionEvent evt) {
		System.out.println("onClose");
		System.out.println(test);
	}
	
	@FXML private void onDelete(ActionEvent evt) {
		System.out.println("onDelete");
	}
	
	@FXML private void selectServer(ActionEvent evt) {
		System.out.println("selectServer");
	}
}
