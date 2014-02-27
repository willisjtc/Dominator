package com.xalero.dominion.server;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = -5523131683756070818L;
	
	private static final Logger log = LogManager.getLogManager().getLogger(LoginServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/json");
		response.setStatus(HttpServletResponse.SC_OK);
		// fulfill login
	}
}
