package dominion.application;

import java.util.Collection;

import dominion.database.dao.UserDAO;
import dominion.game.user.User;

public class UserManager {
	
	public void addUser(String username, String password) {
		try (UserDAO userDao = new UserDAO()) {
			userDao.addUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Collection<User> getAllUsers() {
		Collection<User> users = null;
		try (UserDAO userDao = new UserDAO()) {
			users = userDao.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
