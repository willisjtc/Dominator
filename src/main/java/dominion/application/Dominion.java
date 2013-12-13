package dominion.application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import dominion.application.controller.ConfigurationFactory;
import dominion.application.controller.MainController;

public class Dominion extends Application {
	
	private MainController mainController;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			mainController = new MainController();
			
			Scene scene = new Scene(mainController);
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
