/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.HealthFacilityTemplate;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;

/**
 * @author Stélio Moiane
 *
 */
public class HealthFacilityQuerySeviceTest extends AbstractSpringTest {

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	private HealthFacility healthFacility;

	private District district;

	@Override
	public void setUp() throws BusinessException {
		this.healthFacility = EntityFactory.gimme(HealthFacility.class, HealthFacilityTemplate.VALID);
		this.district = this.districtService.createDistrict(this.getUserContext(), this.healthFacility.getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.healthFacility);
	}

	@Test
	public void shoulFindHealthFacilitiesByDistrict() throws BusinessException {
		final List<HealthFacility> healthFacilities = this.healthFacilityQueryService
		        .findHealthFacilityByDistrict(this.getUserContext(), this.district);

		assertFalse(healthFacilities.isEmpty());
	}

	@Test
	public void shouldFetchAllHealthFacilities() {
		final List<HealthFacility> fetchAllHealthFacilities = this.healthFacilityQueryService
		        .fetchAllHealthFacilities(this.getUserContext());

		assertFalse(fetchAllHealthFacilities.isEmpty());
	}

	@Test
	public void shouldFindHealthFacilityByDistrictAndName() {
		final HealthFacility healthFacility = this.healthFacilityQueryService
		        .findHealthFacilityByDistrictAndName(this.district, this.healthFacility.getHealthFacility());

		assertNotNull(healthFacility);
	}
}
