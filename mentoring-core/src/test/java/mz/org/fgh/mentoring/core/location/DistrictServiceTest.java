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
import mz.org.fgh.mentoring.core.fixturefactory.DistrictTemplate;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.location.service.DistrictService;

/**
 * @author Stélio Moiane
 *
 */
public class DistrictServiceTest extends AbstractSpringTest {

	@Inject
	private DistrictService districtService;

	@Inject
	private FileReaderService fileReaderService;

	private District district;

	@Override
	public void setUp() throws BusinessException {
		this.district = EntityFactory.gimme(District.class, DistrictTemplate.VALID);
	}

	@Test
	public void shouldCreateDistrict() throws BusinessException {
		this.districtService.createDistrict(this.getUserContext(), this.district);
		TestUtil.assertCreation(this.district);
	}

	@Ignore
	@Test
	public void shouldInsertDistrictsFromFile() throws BusinessException {

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

		assertFalse(districts.isEmpty());
	}
}
