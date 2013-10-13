package dominion.application;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Dominion extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));		
			
			Scene scene = new Scene(root, 800, 575);
			scene.getStylesheets().add(Dominion.class.getResource("login.css").toExternalForm());
			primaryStage.setTitle("Dominion Client");
			primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML public void signInReleased(MouseEvent evt) {
		System.out.println("sign in released");
	}
	
	@FXML public void enter(KeyEvent evt) {
		if (evt.getCode().equals(KeyCode.ENTER)) {
			System.out.println("handle username and password");
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
