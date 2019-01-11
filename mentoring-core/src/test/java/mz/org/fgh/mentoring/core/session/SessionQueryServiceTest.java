/**
 *
 */
package mz.org.fgh.mentoring.core.session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.service.CabinetService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.core.session.service.SessionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
public class SessionQueryServiceTest extends AbstractSpringTest {

	@Inject
	private SessionService sessionService;

	@Inject
	private SessionQueryService sessionQueryService;

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private QuestionService questionService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private FormService formService;

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private CabinetService cabinetService;

	private Session session;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.createSession();
		this.createMentorship();
	}

	private void createMentorship() throws BusinessException {
		this.prepareMentorship(this.session);
		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);
	}

	private void prepareMentorship(final Session session) throws BusinessException {
		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());
		this.cabinetService.createCabinet(this.getUserContext(), this.mentorship.getCabinet());

		final FormQuestion formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(formQuestion);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.mentorship.getForm().getProgrammaticArea());

		final Form form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), form, formQuestions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		final Answer answer = new TextAnswer();
		answer.setQuestion(formQuestion.getQuestion());
		answer.setValue("COMPETENTE");

		this.mentorship.addAnswer(answer);
		this.mentorship.setForm(form);
		this.mentorship.setSession(session);
	}

	private Session createSession() throws BusinessException {
		this.session = EntityFactory.gimme(Session.class, SessionTemplate.VALID);
		this.sessionService.createSession(this.getUserContext(), this.session);
		return this.session;
	}

	@Test
	public void shouldFindPerformedSessionsBySelectedFilter() {

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsBySelectedFilter(
		        this.mentorship.getHealthFacility().getDistrict(), this.mentorship.getHealthFacility(),
		        this.mentorship.getForm().getProgrammaticArea(), this.mentorship.getForm(), startDate, endDate);

		assertFalse(performedSessions.isEmpty());
		assertEquals(this.session.getPerformedDate(), startDate);
	}

	@Test
	public void shouldFindMentoringSessionByDistrictAndHealthFacility() {

		final List<SubmitedSessions> submitedSessions = this.sessionQueryService
		        .findNumberOfSessionsPerDistrict(this.getUserContext());

		assertFalse(submitedSessions.isEmpty());

		submitedSessions.forEach(submitedSession -> {
			assertNotNull(submitedSession.getDistrict());
			assertNotNull(submitedSession.getProgrammaticArea());
			assertNotNull(submitedSession.getTotalSubmited());
			assertNotNull(submitedSession.getLastUpdate());
		});
	}

	@Test
	public void shouldFindPerformedSessionsByTutorAndForm() {

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsByTutorAndForm(
		        this.mentorship.getTutor(), this.mentorship.getForm(), startDate, endDate);

		assertFalse(performedSessions.isEmpty());
		assertEquals(this.session.getPerformedDate(), startDate);
	}

	@Test
	public void shouldFindSessionsWithDuplicatedUuid() throws BusinessException {

		for (int i = 0; i < 3; i++) {
			this.session.setId(null);
			this.session.setCreatedAt(null);
			this.session.setCreatedBy(null);

			this.sessionService.createSession(this.getUserContext(), this.session);
			this.prepareMentorship(this.session);
		}

		final List<Session> sessions = this.sessionQueryService.findSessionsWithDuplicatedUuids();
		assertFalse(sessions.isEmpty());
	}

	@Test
	public void shouldFetchSessionsByUuid() throws BusinessException {
		final List<Session> sessions = this.sessionQueryService.fetchSessionsByUuid(this.session.getUuid());
		assertFalse(sessions.isEmpty());
	}
}
