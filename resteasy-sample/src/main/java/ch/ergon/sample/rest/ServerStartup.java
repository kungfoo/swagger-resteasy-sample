package ch.ergon.sample.rest;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



@WebListener
public class ServerStartup implements ServletContextListener {
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		 SwaggerBootstrap.run(event.getServletContext());
	}
}