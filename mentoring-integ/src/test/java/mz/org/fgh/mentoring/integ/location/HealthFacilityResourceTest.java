/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.location;

import static com.jayway.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.jayway.restassured.path.xml.XmlPath;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.fixturefactory.HealthFacilityTemplate;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.integ.config.IntegAbstractSpringTest;
import mz.org.fgh.mentoring.integ.util.Server;

/**
 * @author Stélio Moiane
 *
 */
public class HealthFacilityResourceTest extends IntegAbstractSpringTest {

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private ApplicationContext applicationContext;

	private HealthFacility healthFacility;

	private HttpServer server;

	@Override
	public void setUp() throws BusinessException {

		this.server = new Server().uriBase("http://localhost/services").port(8081)
				.resourcesPackage("mz.org.fgh.mentoring.integ").context(this.applicationContext).initialize();

		this.healthFacility = EntityFactory.gimme(HealthFacility.class, HealthFacilityTemplate.VALID);
		this.districtService.createDistrict(this.getUserContext(), this.healthFacility.getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.healthFacility);
	}

	@Test
	public void shouldFindHealtFacilityByDistrictId() throws BusinessException {

		final XmlPath xmlPath = given().header("Accept", "application/xml").expect().statusCode(200)
				.get("/services/healthfacilities/" + this.healthFacility.getDistrict().getId()).andReturn().xmlPath();

		final List<HealthFacility> healthFacilities = xmlPath.getList("healthFacilities.healthFacility",
				HealthFacility.class);

		assertFalse(healthFacilities.isEmpty());
		assertEquals(1, healthFacilities.size());

		assertEquals(this.healthFacility.getId(), healthFacilities.get(0).getId());
		assertEquals(this.healthFacility.getHealthFacility(), healthFacilities.get(0).getHealthFacility());
		assertEquals(this.healthFacility.getHealthFacility(), healthFacilities.get(0).getHealthFacility());
	}

	@Override
	public void tearDown() {
		super.tearDown();
		this.server.stop();
	}
}
