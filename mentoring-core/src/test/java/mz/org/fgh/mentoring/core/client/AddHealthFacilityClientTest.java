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
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
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

	private AddHealthFacilityClient client;

	@Override
	public void setUp() throws BusinessException {
		this.client = new AddHealthFacilityClient();
		this.client.setHealthFacilityService(this.healthFacilityService);
		this.client.setFileReaderService(this.fileReaderService);
		this.addDistricts();
	}

	private void addDistricts() throws BusinessException {
		final List<GenericObject> districts = this.fileReaderService.readfile("mapping-districts.xlsx");

		for (final GenericObject genericObject : districts) {

			final District districtToInsert = new District();
			final String province = (String) genericObject.getValue("Province");
			final String district = (String) genericObject.getValue("District");

			districtToInsert.setProvince(Province.valueOf(province));
			districtToInsert.setDistrict(district);

			this.districtService.createDistrict(this.getUserContext(), districtToInsert);
		}
	}

	@Test
	public void shouldAddHealthFacilities() throws BusinessException {

		final List<GenericObject> healthFacilities = this.fileReaderService
		        .readfile("add-health-facilities_20171129.xlsx");
		final int records = this.client.process(this.client);

		assertEquals(healthFacilities.size(), records);
	}
}
