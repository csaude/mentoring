/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.location.service.DistrictQueryService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;

/**
 * @author Stélio Moiane
 *
 */
public class AddHealthFacilityClientTest extends AbstractSpringTest {

	@Inject
	private FileReaderService fileReaderService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private DistrictService districtService;

	@Inject
	DistrictQueryService districtQueryService;

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	private AddHealthFacilityClient client;

	@Override
	public void setUp() throws BusinessException {
		this.client = new AddHealthFacilityClient();
		this.client.setHealthFacilityService(this.healthFacilityService);
		this.client.setFileReaderService(this.fileReaderService);
		this.client.setDistrictService(this.districtService);
		this.client.setDistrictQueryService(this.districtQueryService);
		this.client.setHealthFacilityQueryService(this.healthFacilityQueryService);
	}

	@Test
	public void shouldAddHealthFacilities() throws BusinessException {

		final List<GenericObject> healthFacilities = this.fileReaderService
		        .readfile("add-health-facilities_20181029.xlsx");

		final int records = this.client.process(this.client);

		assertEquals(healthFacilities.size(), records);
	}
}
