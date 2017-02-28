package mz.org.fgh.mentoring.core.tutorprogramaticarea;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorProgramaticAreaTamplate;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgramaticAreaService;

public class TutorProgramaticAreaServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private TutorProgramaticAreaService tutorProgramaticAreaService;

	private TutorProgramaticArea tutorProgramaticArea;

	@Inject
	private CareerService carrerService;

	@Override
	public void setUp() throws BusinessException {
		tutorProgramaticArea = EntityFactory.gimme(TutorProgramaticArea.class, TutorProgramaticAreaTamplate.VALID);
		carrerService.createCareer(getUserContext(), tutorProgramaticArea.getTutor().getCareer());
		tutorService.createTutor(getUserContext(), tutorProgramaticArea.getTutor());
		programmaticAreaService.createProgrammaticArea(getUserContext(), tutorProgramaticArea.getProgrammaticArea());

	}

	@Test
	public void shuldCreateTutorProgramaticArea() throws BusinessException {

		TutorProgramaticArea created = tutorProgramaticAreaService.createTutorProgramaticArea(getUserContext(),
				tutorProgramaticArea);
		TestUtil.assertCreation(created);

	}
}
