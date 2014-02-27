package com.xalero.dominion.server.dao;

import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.xalero.dominion.utils.DatabaseProperties;

public class GameDAO extends BasicDAO {
	
	private static final Logger log = LogManager.getLogManager().getLogger(GameDAO.class.getName());
	
	public long createGame(long gameCreator, String model, String filePath) {
		String sql = DatabaseProperties.getProperty("dominion.createGame");
		
		long gameId = 0;
        try (CallableStatement cs = con.prepareCall(sql)) {
            int index = 0;
            cs.setLong(++index, gameCreator);
            cs.setString(++index, model);
            cs.setString(++index, filePath);
            cs.execute();
            filePath = cs.getString(3);
            gameId = cs.getLong(4);
        }
        catch (Exception e) {
            log.log(Level.WARNING, "Error adding user", e);
        }
		return gameId;
	}
}
