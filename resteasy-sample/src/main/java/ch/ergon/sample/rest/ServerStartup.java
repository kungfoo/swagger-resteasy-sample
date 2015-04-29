package ch.ergon.sample.rest;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServerStartup implements ServletContextListener {

	private static Logger LOGGER = Logger.getLogger(ServerStartup.class.getSimpleName());
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();

		LOGGER.info("Running " + SwaggerBootstrap.class.getSimpleName());
		SwaggerBootstrap.run(context);
	}

}