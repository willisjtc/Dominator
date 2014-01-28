package dominion.application.manager;

import java.util.Collection;

import javafx.scene.image.Image;
import dominion.database.dao.UserDAO;
import dominion.game.user.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton UserManager.
 *
 * @author jonathan
 *
 */
public enum UserManager {

    instance;

    private static final Logger log = Logger.getLogger(UserManager.class.getCanonicalName());

    private Map<String, Boolean> defaultNames = getNamingMap();

    private Map<String, Boolean> getNamingMap() {
        Map<String, Boolean> defaultNamer = new HashMap<>();
        defaultNamer.put("Goofy", false);
        defaultNamer.put("Duck", false);
        defaultNamer.put("Quacky", false);
        defaultNamer.put("Quirky", false);
        defaultNamer.put("Ace", false);
        defaultNamer.put("Aqua", false);
        defaultNamer.put("Bugle", false);
        defaultNamer.put("Crammy", false);
        defaultNamer.put("Hotshot", false);
        defaultNamer.put("Iguana", false);
        defaultNamer.put("Joker", false);
        defaultNamer.put("Kickstarter", false);
        defaultNamer.put("Loopy", false);

        return defaultNamer;
    }

    public String getRandomName() {
        Random rand = new Random();

        while (true) {
            int random = rand.nextInt(defaultNames.entrySet().size());
            int iteration = 0;
            for (Entry<String, Boolean> entry : defaultNames.entrySet()) {
                if (random == iteration && entry.getValue() == false) {
                    defaultNames.put(entry.getKey(), Boolean.TRUE);
                    return entry.getKey();
                }
                iteration++;
            }
        }
    }

    public void addUser(String username, String password) {
        try (UserDAO userDao = new UserDAO()) {
            userDao.addUser(username, password);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error adding user", e);
        }
    }

    public void removeUser(String username) {
        try (UserDAO userDao = new UserDAO()) {
            userDao.removeUser(username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getCurrentUser() {
        try (UserDAO userDao = new UserDAO()) {
            return userDao.getCurrentUser();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int id) {
        try (UserDAO userDao = new UserDAO()) {
            return userDao.getUserById(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserByUsername(String username) {
        User user = null;
        try (UserDAO userDao = new UserDAO()) {
            user = userDao.getUserByUsername(username);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public Collection<User> getAllUsers() {
        Collection<User> users = null;
        try (UserDAO userDao = new UserDAO()) {
            users = userDao.getAllUsers();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    public void saveUserProfile(Image image, String username, String password, String description) {
        try (UserDAO userDao = new UserDAO()) {
            userDao.saveUserProfile(image, username, password, description);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
