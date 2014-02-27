package com.xalero.dominion.server.services;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.xalero.dominion.model.DominionModel;
import com.xalero.dominion.model.GameSettings;
import com.xalero.dominion.server.dao.GameDAO;

public enum GameService {
	INSTANCE;

	private static final Logger log = LogManager.getLogManager().getLogger(GameService.class.getName());
	
	public DominionModel createGame(long gameCreator, GameSettings gameSettings) {
		DominionModel model = new DominionModel(gameSettings);
		
		Gson gson = new Gson();
		String modelString = gson.toJson(model);
		
		String filePath = "games/";
		
		long gameId = 0;
		try (GameDAO gameDao = new GameDAO()) {
			gameId = gameDao.createGame(gameCreator, modelString, filePath);
		} catch (Exception e) {
			log.log(Level.WARNING, "error creating game", e);
		} 
		
		model.setGameId(gameId);
		modelString = gson.toJson(model);
		
		try (PrintWriter writer = new PrintWriter(filePath)) {
			writer.println(modelString);
		} catch (Exception e) {
			
		}
		
		return model;
	}
}
