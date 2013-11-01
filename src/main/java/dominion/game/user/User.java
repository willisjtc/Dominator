package dominion.game.user;

import javafx.scene.image.Image;


public class User {
	private Integer id;
	private String username;
	private String password;
	private Image image;
	private UserGameState playerState;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public UserGameState getPlayerState() {
		return playerState;
	}
	public void setPlayerState(UserGameState playerState) {
		this.playerState = playerState;
	}
}
