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
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
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

	private Session session;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.createSession();
		this.createMentorship();
	}

	private void createMentorship() throws BusinessException {
		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());

		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), question);

		final Set<Question> questions = new HashSet<>();
		questions.add(question);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.mentorship.getForm().getProgrammaticArea());

		final Form form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), form, questions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		final Answer answer = new TextAnswer();
		answer.setQuestion(question);
		answer.setValue("COMPETENTE");

		this.mentorship.addAnswer(answer);
		this.mentorship.setForm(form);
		this.mentorship.setSession(this.session);

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);
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

}
