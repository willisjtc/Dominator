package dominion.application.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dominion.application.IObservable;
import dominion.application.IObserver;
import dominion.database.dao.UserDAO;
import dominion.game.user.User;

/**
 * Singleton UserManager.
 * 
 * @author jonathan
 *
 */
public enum UserManager implements IObservable {
	
	instance;
	private List<IObserver> observers = new ArrayList<IObserver>();
	private User loggedInUser;
	

	public void addUser(String username, String password) {
		try (UserDAO userDao = new UserDAO()) {
			userDao.addUser(username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		notifyObservers();
		System.out.println("UserManager: added a user");
	}
	
	public void removeUser(String username) {
		try (UserDAO userDao = new UserDAO()) {
			userDao.removeUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		notifyObservers();
	}
	
	public User getUserById(int id) {
		return null;
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		try (UserDAO userDao = new UserDAO()) {
			user = userDao.getUserByUsername(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
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
	
	public User getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(User loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	
	@Override
	public void registerObserver(IObserver obs) {
		observers.add(obs);
	}

	
	@Override
	public void removeObserver(IObserver obs) {
		observers.remove(obs);
	}

	
	@Override
	public void notifyObservers() {
		System.out.println("UserManager: notified observers");
		for (IObserver observer : observers) {
			observer.update();
		}
	}
}
