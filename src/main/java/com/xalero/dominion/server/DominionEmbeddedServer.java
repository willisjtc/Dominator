package com.xalero.dominion.server;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ThreadPool;

public enum DominionEmbeddedServer {
	INSTANCE;
	
	private final static Logger log = LogManager.getLogManager().getLogger(DominionEmbeddedServer.class.getName());
	
	private Server server;
	private final int THREAD_POOL_SIZE = 20;
	private final int IDLE_TIMEOUT = 30000;
	
	public void startServer(int port) throws Exception {
		initServer(port);
		server.start();
		
	}
	
	private void initServer(int port) {
		ThreadPool threadPool = new QueuedThreadPool(THREAD_POOL_SIZE);
		server = new Server(threadPool);
		
		ServerConnector httpConnector = new ServerConnector(server);
		httpConnector.setHost("localhost");
		httpConnector.setPort(port);
		httpConnector.setIdleTimeout(IDLE_TIMEOUT);
		
		server.addConnector(httpConnector);
		
		ServletContextHandler servletContext = new ServletContextHandler();
		servletContext.setContextPath("/Dominion");
		servletContext.addServlet(LoginServlet.class, "/login");
		servletContext.addServlet(CreateGameServlet.class, "/createGame");
		servletContext.addServlet(MakeMoveServlet.class, "/makeMove");
		
		server.setHandler(servletContext);
	}
	
	public boolean isRunning() {
		if (server != null) {
			return server.isRunning(); 
		}
		return false;
	}
}
