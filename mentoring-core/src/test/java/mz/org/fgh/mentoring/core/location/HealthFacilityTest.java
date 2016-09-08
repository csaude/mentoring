/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.HealthFacilityTemplate;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;

/**
 * @author Stélio Moiane
 *
 */
public class HealthFacilityTest extends AbstractSpringTest {

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private DistrictService districtService;

	@Inject
	private FileReaderService fileReaderService;

	private HealthFacility healthFacility;

	@Override
	public void setUp() throws BusinessException {
		this.healthFacility = EntityFactory.gimme(HealthFacility.class, HealthFacilityTemplate.VALID);
		this.districtService.createDistrict(this.getUserContext(), this.healthFacility.getDistrict());
	}

	@Test
	public void shouldCreateHealthFacility() throws BusinessException {
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.healthFacility);
		TestUtil.assertCreation(this.healthFacility);
	}

	@Ignore
	@Test
	public void shouldInsertHeathFacilitiesFromFile() throws BusinessException {

		this.insertDistricts();

		final List<GenericObject> healthFacilities = this.fileReaderService.readfile("health-facility-mapping.xlsx");

		for (final GenericObject genericObject : healthFacilities) {

			final Double districtId = (Double) genericObject.getValue("District_Id");
			final String healthFacility = (String) genericObject.getValue("Health_Facility");

			final District district = new District();
			district.setId(districtId.longValue());

			final HealthFacility healthFacilityToInsert = new HealthFacility();
			healthFacilityToInsert.setDistrict(district);
			healthFacilityToInsert.setHealthFacility(healthFacility);

			this.healthFacilityService.createHealthFacility(this.getUserContext(), healthFacilityToInsert);

			TestUtil.assertCreation(healthFacilityToInsert);
		}

		assertFalse(healthFacilities.isEmpty());
	}

	private void insertDistricts() throws BusinessException {
		final List<GenericObject> districts = this.fileReaderService.readfile("mapping-districts.xlsx");

		for (final GenericObject genericObject : districts) {

			final District districtToInsert = new District();
			final String province = (String) genericObject.getValue("Province");
			final String district = (String) genericObject.getValue("District");

			districtToInsert.setProvince(Province.valueOf(province));
			districtToInsert.setDistrict(district);

			this.districtService.createDistrict(this.getUserContext(), districtToInsert);

			TestUtil.assertCreation(districtToInsert);
		}
	}
}
