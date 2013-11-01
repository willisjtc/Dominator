package dominion.application.user.views;

import javafx.scene.image.Image;
import dominion.game.user.User;

public class ProfileViewDTO {

	private String username;
	private Image image;
	
	public ProfileViewDTO(User user) {
		this.setUsername(user.getUsername());
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
