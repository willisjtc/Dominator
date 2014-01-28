package dominion.database.dao.manager;

import dominion.application.model.PlayerType;
import dominion.application.model.SimplePlayerInfo;
import dominion.database.dao.UserDAO;
import dominion.game.Player;
import dominion.game.user.User;

public class PlayerManager {

	private UserDAO userDao;
	
	public PlayerManager() {
		userDao = new UserDAO();
	}
	
	public Player getCurrentUserPlayer() {
		User currentUser = userDao.getCurrentUser();
		SimplePlayerInfo spi = new SimplePlayerInfo(currentUser, PlayerType.HUMAN, null);
		Player currentPlayer = new Player(spi);
		return currentPlayer;
	}
}
