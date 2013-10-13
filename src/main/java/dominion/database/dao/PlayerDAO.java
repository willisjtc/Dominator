package dominion.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominion.database.utils.DatabaseObjectMapper;
import dominion.database.utils.DatabaseProperties;
import dominion.game.user.Player;

public class PlayerDAO extends BasicDAO {

	public Player getPlayerById(int id) {
		Player player = new Player();
		try (PreparedStatement ps = con.prepareStatement(DatabaseProperties.getProperty("dominion.getPlayerById"))){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				DatabaseObjectMapper.fillObjectFromResultSet(player, rs, player.getClass());
			}
			System.out.println("player name: " + player.getName());
			System.out.println("username: " + player.getUsername());
			System.out.println("password: " + player.getPassword());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
}
