package dominion.game.user;


public class User {
	private Integer id;
	private String name;
	private String username;
	private String password;
	private UserGameState playerState;
	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserGameState getPlayerState() {
		return playerState;
	}
	public void setPlayerState(UserGameState playerState) {
		this.playerState = playerState;
	}
}
