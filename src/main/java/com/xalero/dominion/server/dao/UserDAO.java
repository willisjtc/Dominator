package com.xalero.dominion.server.dao;

import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import com.xalero.dominion.model.User;
import com.xalero.dominion.utils.DatabaseObjectMapper;
import com.xalero.dominion.utils.DatabaseProperties;


public class UserDAO extends BasicDAO {

    private static final Logger log = Logger.getLogger(UserDAO.class.getCanonicalName());
    
    public void addUser(String username, String password) {
        String sql = DatabaseProperties.getProperty("dominion.addUser");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setString(++index, username);
            ps.setString(++index, password);

            ps.execute();
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error adding user", e);
        }
    }

    public void removeUser(String username) {
        String sql = DatabaseProperties.getProperty("dominion.removeUserByUsername");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setString(++index, username);
            ps.execute();
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error removing user", e);
        }
    }

    public User getCurrentUser() {
        User player = new User();
        String sql = DatabaseProperties.getProperty("dominion.getUserById");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, 1);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                DatabaseObjectMapper.fillObjectFromResultSet(player, rs, player.getClass());
            }
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error getting current user", e);
        }
        return player;
    }

    public User getUserById(int id) {
        User player = new User();
        String sql = DatabaseProperties.getProperty("dominion.getUserById");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DatabaseObjectMapper.fillObjectFromResultSet(player, rs, player.getClass());
            }
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error getting user by id", e);
        }
        return player;
    }

    public Collection<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        String sql = DatabaseProperties.getProperty("dominion.getAllUsers");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                DatabaseObjectMapper.fillObjectFromResultSet(user, rs, User.class);
                users.add(user);
            }
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error getting all users", e);
        }
        return users;
    }

    public User getUserByUsername(String username) {
        User user = new User();
        String sql = DatabaseProperties.getProperty("dominion.getUserByUsername");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            ps.setString(++index, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                DatabaseObjectMapper.fillObjectFromResultSet(user, rs, user.getClass());
            }
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error getting user by username", e);
        }
        return user;
    }

    public void saveUserProfile(Image image, String username, String password, String description) {
        String sql = DatabaseProperties.getProperty("dominion.saveUserProfile");
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            int index = 0;
            String imageUrl = new StringBuffer().append(image.hashCode()).toString();
            
            ps.setString(++index, imageUrl);
            ps.setString(++index, username);
            ps.setString(++index, password);
            ps.setString(++index, description);
            ps.setInt(++index, 1);
            
            ps.execute();
            
            File outFileImage = new File(imageUrl);
            
            try {
            	ImageIO.write(SwingFXUtils.fromFXImage(image, null), ".png", outFileImage);
            } catch (Exception e) {
            	log.log(Level.WARNING, "Image not saved to disk", e);
            }
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error saving user profile", e);
        }
    }
}
