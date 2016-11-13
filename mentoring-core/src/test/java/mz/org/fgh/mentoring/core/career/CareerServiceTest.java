/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CareerTemplate;

/**
 * @author Stélio Moiane
 *
 */
public class CareerServiceTest extends AbstractSpringTest {

	@Inject
	private CareerService careerService;

	@Inject
	private FileReaderService fileReaderService;

	private Career career;

	@Override
	public void setUp() throws BusinessException {
		this.career = EntityFactory.gimme(Career.class, CareerTemplate.VALID);
	}

	@Test
	public void shouldCreateCarrer() throws BusinessException {

		this.careerService.createCareer(this.getUserContext(), this.career);

		TestUtil.assertCreation(this.career);
	}

	@Test
	public void shouldPopulateCarrers() throws BusinessException {

		final List<GenericObject> carrers = this.fileReaderService.readfile("mapping-careers.xlsx");

		for (final GenericObject genericObject : carrers) {

			final Career carrerToPersist = new Career();
			final String carrerType = (String) genericObject.getValue("Tipo");
			final String position = (String) genericObject.getValue("Categoria");

			carrerToPersist.setCareerType(CareerType.valueOf(carrerType));
			carrerToPersist.setPosition(position);

			this.careerService.createCareer(this.getUserContext(), carrerToPersist);

			TestUtil.assertCreation(carrerToPersist);
		}

		assertFalse(carrers.isEmpty());
	}
}
