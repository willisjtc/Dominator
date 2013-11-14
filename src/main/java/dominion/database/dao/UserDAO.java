package dominion.database.dao;

import java.io.ByteArrayOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import dominion.database.utils.DatabaseObjectMapper;
import dominion.database.utils.DatabaseProperties;
import dominion.game.user.User;

public class UserDAO extends BasicDAO {

	public void addUser(String username, String password) {
		String sql = DatabaseProperties.getProperty("dominion.addUser");
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int index = 0;
			ps.setString(++index, username);
			ps.setString(++index, password);
			
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(String username) {
		String sql = DatabaseProperties.getProperty("dominion.removeUserByUsername");
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int index = 0;
			ps.setString(++index, username);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public User getUserById(int id) {
		User player = new User();
		String sql = DatabaseProperties.getProperty("dominion.getAllUsers");
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				DatabaseObjectMapper.fillObjectFromResultSet(player, rs, player.getClass());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
	
	public Collection<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = DatabaseProperties.getProperty("dominion.getAllUsers");
		try (PreparedStatement ps = con.prepareStatement(sql)){
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				DatabaseObjectMapper.fillObjectFromResultSet(user, rs, User.class);
				users.add(user);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public void saveUserProfile(Image image, String username, String password, String description) {
		String sql = DatabaseProperties.getProperty("dominion.saveUserProfile");
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int index = 0;
			ByteArrayOutputStream os = new ByteArrayOutputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
