/**
 *
 */
package mz.org.fgh.mentoring.integ.util;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.core.spi.component.ioc.IoCComponentProviderFactory;
import com.sun.jersey.spi.spring.container.SpringComponentProviderFactory;

/**
 * @author Stélio Moiane
 *
 *         Servidor Grizzly para execução de testes com WebServices
 */
public class Server {
	private String uriBase;
	private int port;
	private String resourcesPackage;
	private ApplicationContext context;

	public Server uriBase(final String uriBasei) {
		this.uriBase = uriBasei;
		return this;
	}

	public Server port(final int port) {
		this.port = port;
		return this;
	}

	public Server resourcesPackage(final String resourcesPackage) {
		this.resourcesPackage = resourcesPackage;
		return this;
	}

	public Server context(final ApplicationContext context) {
		this.context = context;
		return this;
	}

	public HttpServer initialize() {
		final URI uri = UriBuilder.fromUri(this.uriBase).port(this.port).build();

		final ResourceConfig resourceConfig = new PackagesResourceConfig(this.resourcesPackage);

		// Adicionando o contexto do spring ao levantar o Grizzly
		final ConfigurableApplicationContext configurableApplicationContext = new AnnotationConfigApplicationContext();
		configurableApplicationContext.setParent(this.context);
		configurableApplicationContext.refresh();

		final IoCComponentProviderFactory ioCComponentProviderFactory = new SpringComponentProviderFactory(
				resourceConfig, configurableApplicationContext);

		HttpServer server = null;

		try {
			server = GrizzlyServerFactory.createHttpServer(uri, resourceConfig, ioCComponentProviderFactory);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		return server;
	}
}
