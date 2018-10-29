/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.DistrictTemplate;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.service.DistrictQueryService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;

/**
 * @author Stélio Moiane
 *
 */
public class DistrictQueryServiceTest extends AbstractSpringTest {

	@Inject
	private DistrictService districtService;

	@Inject
	private DistrictQueryService districtQueryService;

	private District district;

	@Override
	public void setUp() throws BusinessException {
		this.district = EntityFactory.gimme(District.class, DistrictTemplate.VALID);
		this.districtService.createDistrict(this.getUserContext(), this.district);
	}

	@Test
	public void shouldFindDistricsByProvince() throws BusinessException {
		final List<District> districts = this.districtQueryService.findDistrictsByProvince(this.getUserContext(),
		        this.district.getProvince());

		assertFalse(districts.isEmpty());
		districts.forEach(district -> assertEquals(district.getProvince(), this.district.getProvince()));
	}

	@Test
	public void shouldFindDistricsByProvinceAndName() throws BusinessException {
		final District district = this.districtQueryService.findDistrictByProvinceAndName(this.getUserContext(),
		        this.district.getProvince(), this.district.getDistrict());

		assertNotNull(district);
		assertEquals(district.getProvince(), this.district.getProvince());
		assertEquals(district.getDistrict(), this.district.getDistrict());
	}
}
