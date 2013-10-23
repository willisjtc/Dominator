package dominion.application.profile;

import javafx.scene.image.Image;
import dominion.game.user.User;

public class ProfileViewDTO {

	private String name;
	private String username;
	private Image image;
	
	public ProfileViewDTO(User user) {
		this.setName(user.getName());
		this.setUsername(user.getUsername());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
