/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;

/**
 * @author Stélio Moiane
 *
 */
public abstract class ClientConfig<E> {

	private AnnotationConfigApplicationContext context;

	public void setup() {
		this.context = new AnnotationConfigApplicationContext();
		this.context.getEnvironment().setActiveProfiles("client");
		this.context.register(ApplicationContextClient.class);
		this.context.refresh();
	}

	public void destroy() {
		this.context.destroy();
	}

	public <T> T getBean(final Class<T> clazz) {
		return this.context.getBean(clazz);
	}

	public abstract int process(E client) throws BusinessException;

	public UserContext getUserContext() {
		final UserContext context = new UserContext();
		context.setUuid("682eb67387a84d54b9adf93247aefb55");
		context.setId(1L);
		return context;
	}

	public void close() {
		this.context.close();
	}
}
