package mz.org.fgh.mentoring.core.tutorprogramaticarea;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import mz.co.mozview.frameworks.core.email.MailSenderService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.MockUtil;
import mz.co.mozview.frameworks.core.webservices.service.ClientWS;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorProgramaticAreaTamplate;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaQueryService;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaService;

public class TutorProgrammaticAreaQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private TutorProgrammaticAreaService tutorProgrammaticAreaService;

	@Inject
	private TutorProgrammaticAreaQueryService tutorProgrammaticAreaQueryService;

	@Mock
	private ClientWS client;

	@Mock
	private MailSenderService mailSenderService;

	private Tutor tutor;

	private ProgrammaticArea programmaticArea;

	@Override
	public void setUp() throws BusinessException {
		MockitoAnnotations.initMocks(this);

		MockUtil.mockField(this.tutorProgrammaticAreaService, "client", this.client);
		MockUtil.mockField(this.tutorProgrammaticAreaService, "mailSenderService", this.mailSenderService);

		final TutorProgrammaticArea tutorProgramaticArea = EntityFactory.gimme(TutorProgrammaticArea.class,
		        TutorProgramaticAreaTamplate.VALID);
		this.careerService.createCareer(this.getUserContext(), tutorProgramaticArea.getTutor().getCareer());

		this.tutor = this.tutorService.createTutor(this.getUserContext(), tutorProgramaticArea.getTutor());
		this.programmaticArea = this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        tutorProgramaticArea.getProgrammaticArea());

		this.tutorProgrammaticAreaService.mapTutorToProgramaticArea(this.getUserContext(), tutorProgramaticArea);
	}

	@Test
	public void shouldFindTutorMapByTutorAndProgrammaticArea() throws BusinessException {

		final TutorProgrammaticArea tutorMap = this.tutorProgrammaticAreaQueryService
		        .findTutorMapByTutorAndProgrammaticArea(this.getUserContext(), this.tutor, this.programmaticArea);

		assertNotNull(tutorMap);
		assertEquals(this.tutor.getCode(), tutorMap.getTutor().getCode());
		assertEquals(this.programmaticArea.getUuid(), tutorMap.getProgrammaticArea().getUuid());
	}
}
