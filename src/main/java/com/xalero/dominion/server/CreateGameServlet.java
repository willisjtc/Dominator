package com.xalero.dominion.server;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xalero.dominion.server.model.DominionModel;
import com.xalero.dominion.server.model.GameSettings;
import com.xalero.dominion.server.services.GameService;

public class CreateGameServlet extends HttpServlet {

	private static final long serialVersionUID = 3574403203880196808L;
	
	private static final Logger log = LogManager.getLogManager().getLogger(CreateGameServlet.class.getName());
	
	private static final GameService gameService = GameService.INSTANCE;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json");
		
		// create the game
		
		Gson gson = new Gson();
		
		GameSettings gameSettings = gson.fromJson(request.getParameter("gameSettings"), GameSettings.class);
		
		DominionModel dominionModel = gameService.createGame(1L, gameSettings);
		String jsonDominionModel = gson.toJson(dominionModel);
		
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(jsonDominionModel);
	}
}
