package dominion.database.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dominion.database.utils.DatabaseProperties;

public class InitializationDAO extends BasicDAO {

	public void run() {
		createTableUser();
	}
	
	public void createTableUser() {
		String sql = DatabaseProperties.getProperty("dominion.doesTableExist");
		boolean tableExists = false;
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			int index = 0;
			ps.setString(++index, "DOMINION_USER");
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tableExists = rs.getInt(index) == 0 ? false : true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (tableExists) return;
		
		sql = DatabaseProperties.getProperty("dominion.createTableUser");
		try (PreparedStatement ps = con.prepareStatement(sql)) {
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
