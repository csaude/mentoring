/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.tutoreds;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.jayway.restassured.response.Response;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.fixturefactory.TutoredTemplate;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.integ.config.IntegAbstractSpringTest;
import mz.org.fgh.mentoring.integ.resources.tutored.TutoredBeanResource;
import mz.org.fgh.mentoring.integ.util.Server;

/**
 * @author Stélio Moiane
 *
 */
public class TutoredsResourceTest extends IntegAbstractSpringTest {

	@Inject
	private ApplicationContext applicationContext;

	@Inject
	private CareerService careerService;

	private List<Tutored> tutoreds;

	private HttpServer server;

	@Override
	public void setUp() throws BusinessException {

		this.server = new Server().uriBase("http://localhost/services").port(8081)
				.resourcesPackage("mz.org.fgh.mentoring.integ").context(this.applicationContext).initialize();

		this.tutoreds = EntityFactory.gimme(Tutored.class, 20, TutoredTemplate.VALID);
		for (final Tutored tutored : this.tutoreds) {
			this.careerService.createCareer(this.getUserContext(), tutored.getCareer());
			tutored.getCareer().setCreatedAt(null);
		}
	}

	@Test
	public void shouldSyncTutoreds() {
		final TutoredBeanResource resource = new TutoredBeanResource();
		resource.setTutoreds(this.tutoreds);
		resource.setUserContext(this.getUserContext());

		final Response post = given().contentType("application/json").body(resource).when()
				.post("/services/tutoreds/sync");

		final TutoredBeanResource beanResource = post.body().as(TutoredBeanResource.class);
		assertNotNull(beanResource);
		assertFalse(beanResource.getTutoreds().isEmpty());

		for (final Tutored tutored : beanResource.getTutoreds()) {
			assertNotNull(tutored.getId());
			assertNotNull(tutored.getCode());
			assertEquals(0, tutored.getVersion());
		}
	}

	@Test
	public void shouldSyncSingleTutored() {
		final TutoredBeanResource resource = new TutoredBeanResource();
		resource.setTutoreds(this.tutoreds.subList(0, 1));
		resource.setUserContext(this.getUserContext());

		final Response post = given().contentType("application/json").body(resource).when()
				.post("/services/tutoreds/sync");

		final TutoredBeanResource beanResource = post.body().as(TutoredBeanResource.class);

		assertNotNull(beanResource);
		assertNotNull(beanResource.getTutored());
		assertNull(beanResource.getTutoreds());
		assertNotNull(beanResource.getTutored().getId());
		assertNotNull(beanResource.getTutored().getCode());
		assertEquals(0, beanResource.getTutored().getVersion());
	}

	@Override
	public void tearDown() {
		super.tearDown();
		this.server.stop();
	}
}
