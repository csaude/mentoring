/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.career;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CareerTemplate;

/**
 * @author Stélio Moiane
 *
 */
public class CareerQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CareerService carrerService;

	@Inject
	private CareerQueryService careerQueryService;

	private Career carrer;

	@Override
	public void setUp() throws BusinessException {
		this.carrer = EntityFactory.gimme(Career.class, CareerTemplate.VALID);
		this.carrerService.createCareer(this.getUserContext(), this.carrer);
	}

	@Test
	public void shouldFindCarrersByCarrerType() throws BusinessException {
		final List<Career> carres = this.careerQueryService.findCareersByCareerType(this.getUserContext(),
				this.carrer.getCareerType());

		assertFalse(carres.isEmpty());
		carres.forEach(carrer -> assertEquals(carrer.getCareerType(), this.carrer.getCareerType()));
	}

	@Test
	public void shouldFindAllCareers() {
		final List<Career> carrers = this.careerQueryService.findAllCareers(this.getUserContext());

		assertFalse(carrers.isEmpty());
	}
}
