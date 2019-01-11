/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.answer.service.AnswerQueryService;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.core.session.service.SessionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
public class VoidDuplicatedSessionsClientTest extends AbstractSpringTest {

	@Inject
	private SessionService sessionService;

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
	private SessionQueryService sessionQueryService;

	@Inject
	private AnswerService answerService;

	@Inject
	private AnswerQueryService answerQueryService;

	@Override
	public void setUp() throws BusinessException {

		final Session session = EntityFactory.gimme(Session.class, SessionTemplate.VALID);

		for (int i = 0; i < 3; i++) {

			session.setId(null);
			session.setCreatedAt(null);
			session.setCreatedBy(null);

			this.sessionService.createSession(this.getUserContext(), session);

			final Mentorship mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
			mentorship.setSession(session);
			mentorship.setCabinet(null);

			this.careerService.createCareer(this.getUserContext(), mentorship.getTutor().getCareer());
			this.careerService.createCareer(this.getUserContext(), mentorship.getTutored().getCareer());
			this.tutorService.createTutor(this.getUserContext(), mentorship.getTutor());
			this.tutoredService.createTutored(this.getUserContext(), mentorship.getTutored());

			final FormQuestion formQuestion = EntityFactory.gimme(FormQuestion.class,
			        FormQuestionTemplate.WITH_NO_FORM);
			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());

			final Set<FormQuestion> formQuestions = new HashSet<>();
			formQuestions.add(formQuestion);

			this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
			        mentorship.getForm().getProgrammaticArea());

			final Form form = mentorship.getForm();
			this.formService.createForm(this.getUserContext(), form, formQuestions);

			this.districtService.createDistrict(this.getUserContext(), mentorship.getHealthFacility().getDistrict());
			this.healthFacilityService.createHealthFacility(this.getUserContext(), mentorship.getHealthFacility());

			final Answer answer = new TextAnswer();
			answer.setQuestion(formQuestion.getQuestion());
			answer.setValue("COMPETENTE");

			mentorship.addAnswer(answer);

			this.mentorshipService.createMentorship(this.getUserContext(), mentorship);
		}
	}

	@Test
	public void shouldVoidSessionsWithDuplicatedUuid() throws BusinessException {
		final VoidDuplicatedSessionsClient client = new VoidDuplicatedSessionsClient();

		client.setSessionQueryService(this.sessionQueryService);
		client.setSessionService(this.sessionService);
		client.setMentorshipService(this.mentorshipService);
		client.setAnswerQueryService(this.answerQueryService);
		client.setAnswerService(this.answerService);

		final int records = client.process(client);

		assertFalse(records == 0);
	}
}
