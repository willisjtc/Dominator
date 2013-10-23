package dominion.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import dominion.database.utils.DatabaseObjectMapper;
import dominion.database.utils.DatabaseProperties;
import dominion.game.user.User;

public class UserDAO extends BasicDAO {

	public User getUserById(int id) {
		User player = new User();
		String sql = DatabaseProperties.getProperty("dominion.getAllUsers");
		System.out.println(sql);
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
		System.out.println(sql);
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
}
