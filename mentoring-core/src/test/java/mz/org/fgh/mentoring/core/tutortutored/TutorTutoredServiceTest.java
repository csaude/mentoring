package mz.org.fgh.mentoring.core.tutortutored;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTutoredTamplate;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;
import mz.org.fgh.mentoring.core.tutortutored.model.TutorTudored;
import mz.org.fgh.mentoring.core.tutortutored.service.TutorTutoredService;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class TutorTutoredServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	private TutorTudored tutorTutored;

	@Inject
	private TutorTutoredService tutorTutoredService;

	@Inject
	private CareerService carrerService;

	@Override
	public void setUp() throws BusinessException {
		tutorTutored = EntityFactory.gimme(TutorTudored.class, TutorTutoredTamplate.VALID);
		carrerService.createCareer(getUserContext(), tutorTutored.getTutor().getCareer());
		carrerService.createCareer(getUserContext(), tutorTutored.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.tutorTutored.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.tutorTutored.getTutored());

	}

	@Test
	public void shuldCreateTutorTutored() throws BusinessException {

		TutorTudored created = tutorTutoredService.createTutorTutored(getUserContext(), tutorTutored);
		TestUtil.assertCreation(created);

	}

}
