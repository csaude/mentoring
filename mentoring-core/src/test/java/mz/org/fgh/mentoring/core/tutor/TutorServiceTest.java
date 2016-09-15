/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.service.CarrerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 *
 */
public class TutorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorDAO tutorDAO;

	private Tutor tutor;

	@Inject
	private CarrerService carrerService;

	@Override
	public void setUp() throws BusinessException {
		
		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);

	}

	@Test
	public void shouldCreateTutor() throws BusinessException {

		final Carrer createdCarrer =  carrerService.createCarrer(getUserContext(), tutor.getCarrer());
		
		this.tutorService.createTutor(this.getUserContext(), tutor, createdCarrer);

		TestUtil.assertCreation(this.tutor);
	}

	@Test
	public void shouldUpdateTutor() throws BusinessException {

		final Carrer createdCarrer =  carrerService.createCarrer(getUserContext(), tutor.getCarrer());

		this.tutorService.createTutor(this.getUserContext(), this.tutor, createdCarrer);

		final Tutor tutorUpdate = this.tutorDAO.findById(this.tutor.getId());

		final String name = "Eurico Jose";
		final String surname = "Maposse";

		tutorUpdate.setName(name);
		tutorUpdate.setSurname(surname);

		this.tutorService.updateTutor(this.getUserContext(), tutorUpdate);

		TestUtil.assertUpdate(tutorUpdate);

		assertEquals(name, tutorUpdate.getName());
		assertEquals(surname, tutorUpdate.getSurname());
	}

}
