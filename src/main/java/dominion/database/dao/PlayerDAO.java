package dominion.database.dao;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import dominion.game.user.Player;

public class PlayerDAO extends BasicDAO {

	public Player getPlayerById(int id) {
		Player player = new Player();
		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM player WHERE id = ?")){
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData resultSetMetaData = rs.getMetaData();
			for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
				String columnName = resultSetMetaData.getColumnName(i);
				System.out.println("label: " + columnName);
				String fieldStr = player.getClass().getDeclaredField(columnName.toLowerCase()).getName();
				Field field = player.getClass().getDeclaredField(columnName.toLowerCase());
				System.out.println("field: " + fieldStr);
			}
			if (rs.next()) {
				String name = rs.getString("name");
				String username = rs.getString("username");
				String password = rs.getString("password");
				
				player.setId(id);
				player.setName(name);
				player.setUsername(username);
				player.setPassword(password);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return player;
	}
}
