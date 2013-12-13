package dominion.application.controller;

import javafx.fxml.FXMLLoader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConfigurationFactory {
	
	@Bean 
	public MainController getMainController() {
		return (MainController) loadController("main.fxml");
	}
	
	@Bean 
	public MenubarController getMenubarController() {
		return (MenubarController) loadController("menubar.fxml");
	}

	private Object loadController(String url) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(url));
		
		try {
			fxmlLoader.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fxmlLoader.getController();
	}
}
