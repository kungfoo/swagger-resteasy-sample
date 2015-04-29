package ch.ergon.sample.rest;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.logging.Logger;

import javax.servlet.ServletContext;

import com.google.common.base.Joiner;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.jaxrs.config.ReflectiveJaxrsScanner;
import com.wordnik.swagger.models.Contact;
import com.wordnik.swagger.models.Info;
import com.wordnik.swagger.models.License;
import com.wordnik.swagger.models.Swagger;

public class SwaggerBootstrap {

	private static Logger LOGGER = Logger.getLogger(SwaggerBootstrap.class.getSimpleName());

	private final ServletContext servletContext;

	private SwaggerBootstrap(ServletContext servletContext) {
		this.servletContext = checkNotNull(servletContext);
	}

	public static void run(ServletContext servletContext) {
		new SwaggerBootstrap(servletContext).run();
	}

	private void run() {

		String resourcePackages = Joiner.on(',').join(SampleRestApplication.RESOURCE_PACKAGES);
		LOGGER.info("Adding resource packages " + resourcePackages);

		ReflectiveJaxrsScanner scanner = new ReflectiveJaxrsScanner();
		scanner.setResourcePackage(resourcePackages);
		scanner.setPrettyPrint(true);
		ScannerFactory.setScanner(scanner);
		Info info =
				new Info()
						.title("Sample Resteasy 3.0.9 based Swagger Documentation")
						.version("0.1-alpha")
						.description(
								"This is a sample, you can do stuff with it.")
						.contact(new Contact()
								.email("no-reply@google.com"))
						.license(new License()
								.name("WTFPL")
								.url("http://www.wtfpl.net/about/"));

		Swagger swagger = new Swagger()
				.info(info)
				.basePath(this.servletContext.getContextPath() + SampleRestApplication.APPLICATION_PATH);
		this.servletContext.setAttribute("swagger", swagger);
		LOGGER.info("Done boostrapping swagger");
	}

}
