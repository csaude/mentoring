package mz.org.fgh.mentoring.core.tutor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

/*
 * Friends in Global Health - FGH © 2016
 */
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class TutorQueryServiceTest extends AbstractSpringTest {

	private Tutor tutor;

	@Inject
	private TutorService tutorService;

	@Inject
	private CareerService carrerService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Override
	public void setUp() throws BusinessException {

		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);

		this.carrerService.createCareer(this.getUserContext(), this.tutor.getCareer());

		this.tutorService.createTutor(this.getUserContext(), this.tutor);
	}

	@Test
	public void shouldFindTutorBySelectedFilter() throws BusinessException {

		final String code = null;
		final String name = null;
		final String surname = null;
		final String phoneNumber = null;
		final CareerType careerType = this.tutor.getCareer().getCareerType();

		final List<Tutor> tutors = this.tutorQueryService.findTutorsBySelectedFilter(this.getUserContext(), code, name,
		        surname, careerType, phoneNumber);
		assertFalse(tutors.isEmpty());

		for (final Tutor tutor : tutors) {
			assertEquals("840665903", tutor.getPhoneNumber());
		}
	}

	@Test
	public void shouldFetchTutorByUuid() throws BusinessException {
		final String uuid = this.tutor.getUuid();
		final Tutor foundTutor = this.tutorQueryService.fetchTutorByUuid(this.getUserContext(), uuid);

		assertNotNull(foundTutor);
		assertEquals(uuid, foundTutor.getUuid());
		assertNotNull(foundTutor.getCareer());
	}

	@Test
	public void shouldFetchTutorByEmail() throws BusinessException {

		final Tutor newTutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);
		this.carrerService.createCareer(this.getUserContext(), newTutor.getCareer());

		newTutor.setEmail("actuator@gmail.com");

		this.tutorService.createTutor(this.getUserContext(), newTutor);
		final Tutor foundTutor = this.tutorQueryService.fetchTutorByEmail(this.getUserContext(), newTutor.getEmail());
		assertEquals(newTutor.getEmail(), foundTutor.getEmail());
		assertNotNull(foundTutor.getCareer());
	}

	@Test(expected = BusinessException.class)
	public void shouldNotFindTutorByEmail() throws BusinessException {
		this.tutorQueryService.fetchTutorByEmail(this.getUserContext(), "hawena@hawena.com");
	}
}
