package mz.org.fgh.mentoring.core.tutorprogramaticarea;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.MockUtil;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.co.mozview.frameworks.core.webservices.service.ClientWS;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorProgramaticAreaTamplate;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaService;

public class TutorProgramaticAreaServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private TutorProgrammaticAreaService tutorProgramaticAreaService;

	private TutorProgrammaticArea tutorProgramaticArea;

	@Inject
	private CareerService carrerService;

	@Mock
	private ClientWS client;

	@Override
	public void setUp() throws BusinessException {

		MockitoAnnotations.initMocks(this);
		MockUtil.mockField(this.tutorProgramaticAreaService, "client", this.client);

		this.tutorProgramaticArea = EntityFactory.gimme(TutorProgrammaticArea.class,
		        TutorProgramaticAreaTamplate.VALID);
		final Tutor tutor = this.tutorProgramaticArea.getTutor();

		this.carrerService.createCareer(this.getUserContext(), tutor.getCareer());

		tutor.setEmail("stelio.moiane@fgh.org.mz");

		this.tutorService.createTutor(this.getUserContext(), tutor);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.tutorProgramaticArea.getProgrammaticArea());
	}

	@Test
	public void shouldMapTutorToProgramaticArea() throws BusinessException {

		this.tutorProgramaticArea.mapAsUser();

		final TutorProgrammaticArea mapped = this.tutorProgramaticAreaService
		        .mapTutorToProgramaticArea(this.getUserContext(), this.tutorProgramaticArea);

		TestUtil.assertCreation(mapped);
		TestUtil.assertUpdate(mapped.getTutor());
		assertTrue(mapped.getTutor().isUser());
	}

	@Test(expected = BusinessException.class)
	public void shouldNotMapTutorToProgramaticArea() throws BusinessException {

		final TutorProgrammaticArea mapped = this.tutorProgramaticAreaService
		        .mapTutorToProgramaticArea(this.getUserContext(), this.tutorProgramaticArea);

		this.tutorProgramaticAreaService.mapTutorToProgramaticArea(this.getUserContext(), mapped);
	}

	@Test
	public void shouldMapTutorToProgramaticAreaForNotUserTutor() throws BusinessException {

		final TutorProgrammaticArea mapped = this.tutorProgramaticAreaService
		        .mapTutorToProgramaticArea(this.getUserContext(), this.tutorProgramaticArea);

		TestUtil.assertCreation(mapped);
		assertNull(mapped.getTutor().getUpdatedAt());
		assertFalse(mapped.getTutor().isUser());
	}
}
