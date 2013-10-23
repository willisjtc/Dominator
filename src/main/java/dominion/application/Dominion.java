package dominion.application;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Dominion extends Application {
	
	Parent root;
	MainController mainController;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			mainController = new MainController();
			
			Scene scene = new Scene(mainController);
			scene.getStylesheets().add(Dominion.class.getResource("login.css").toExternalForm());
			primaryStage.setTitle("Dominion Client");
			primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
