package dominion.application;

import java.lang.reflect.Field;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import dominion.database.dao.PlayerDAO;
import dominion.game.user.Player;

public class Dominion extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));		
			
			Scene scene = new Scene(root, 600, 275);
			scene.getStylesheets().add(Dominion.class.getResource("login.css").toExternalForm());
			primaryStage.setTitle("Dominion Client");
			primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		PlayerDAO playerDao = new PlayerDAO();
		Player player = playerDao.getPlayerById(1);
		Object playerRef = player;
		Field[] fields = playerRef.getClass().getFields();
		
		for (int i = 0; i < fields.length; i++) {
			System.out.println("field: " + fields[i]);
		}
		for (int i = 0; i < playerRef.getClass().getMethods().length; i++) {
			System.out.println("method: " + playerRef.getClass().getMethods()[i]);
		}
		System.out.println("player: " + player.getName() + " " + player.getUsername() + " " + player.getPassword());
//		BasicDAO basicDao = new BasicDAO();
//		String name = basicDao.getName();
//		System.out.println("name: " + name);
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
