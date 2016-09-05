/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.service.CarrerQueryService;
import mz.org.fgh.mentoring.core.carrer.service.CarrerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CarrerTemplate;

/**
 * @author Stélio Moiane
 *
 */
public class CarrerQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CarrerService carrerService;

	@Inject
	private CarrerQueryService carrerQueryService;

	private Carrer carrer;

	@Override
	public void setUp() throws BusinessException {
		this.carrer = EntityFactory.gimme(Carrer.class, CarrerTemplate.VALID);
		this.carrerService.createCarrer(this.getUserContext(), this.carrer);
	}

	@Test
	public void shouldFindCarrersByCarrerType() throws BusinessException {
		final List<Carrer> carres = this.carrerQueryService.findCarrersByCarrerType(this.getUserContext(),
				this.carrer.getCarrerType());

		assertFalse(carres.isEmpty());
		carres.forEach(carrer -> assertEquals(carrer.getCarrerType(), this.carrer.getCarrerType()));
	}
}
