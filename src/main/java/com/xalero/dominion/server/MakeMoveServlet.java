package com.xalero.dominion.server;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MakeMoveServlet extends HttpServlet {

	private static final long serialVersionUID = 4967780285791705824L;
	
	private static final Logger log = LogManager.getLogManager().getLogger(MakeMoveServlet.class.getName());

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json");
		response.setStatus(HttpServletResponse.SC_OK);
		// complete move
	}
}
