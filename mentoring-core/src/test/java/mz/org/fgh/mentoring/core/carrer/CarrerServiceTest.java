/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.carrer;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.files.FileReaderService;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.co.mozview.frameworks.core.util.GenericObject;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;
import mz.org.fgh.mentoring.core.carrer.service.CarrerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.CarrerTemplate;

/**
 * @author Stélio Moiane
 *
 */
public class CarrerServiceTest extends AbstractSpringTest {

	@Inject
	private CarrerService carrerService;

	@Inject
	private FileReaderService fileReaderService;

	private Carrer carrer;

	@Override
	public void setUp() throws BusinessException {
		this.carrer = EntityFactory.gimme(Carrer.class, CarrerTemplate.VALID);
	}

	@Test
	public void shouldCreateCarrer() throws BusinessException {

		this.carrerService.createCarrer(this.getUserContext(), this.carrer);

		TestUtil.assertCreation(this.carrer);
	}

	@Test
	public void shouldPopulateCarrers() throws BusinessException {

		final List<GenericObject> carrers = this.fileReaderService.readfile("mapping-careers.xlsx");

		for (final GenericObject genericObject : carrers) {

			final Carrer carrerToPersist = new Carrer();
			final String carrerType = (String) genericObject.getValue("Tipo");
			final String position = (String) genericObject.getValue("Categoria");

			carrerToPersist.setCarrerType(CarrerType.valueOf(carrerType));
			carrerToPersist.setPosition(position);

			this.carrerService.createCarrer(this.getUserContext(), carrerToPersist);

			TestUtil.assertCreation(carrerToPersist);
		}

		assertFalse(carrers.isEmpty());
	}
}
