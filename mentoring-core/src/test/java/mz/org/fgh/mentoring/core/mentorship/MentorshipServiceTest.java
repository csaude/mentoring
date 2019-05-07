/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipProcessor;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.service.CabinetService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class MentorshipServiceTest extends AbstractSpringTest {

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private MentorshipDAO mentorshipDAO;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private CareerService careerService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private HealthFacilityService heathFacilityService;

	@Inject
	private DistrictService districtService;

	@Inject
	private CabinetService cabinetService;

	@Inject
	private QuestionCategoryService questionCategoryService;

	private Mentorship mentorship;

	private FormQuestion formQuestion;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());
		this.cabinetService.createCabinet(this.getUserContext(), this.mentorship.getCabinet());

		this.formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        this.formQuestion.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), this.formQuestion.getQuestion());

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(this.formQuestion);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.mentorship.getForm().getProgrammaticArea());

		this.form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), this.form, formQuestions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());
	}

	@Test
	public void shouldCreateMentorship() throws BusinessException {

		final Answer answer = new TextAnswer();
		answer.setQuestion(this.formQuestion.getQuestion());
		answer.setValue("COMPETENTE");

		this.mentorship.addAnswer(answer);

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);

		TestUtil.assertCreation(this.mentorship);
	}

	@Ignore
	@Test
	public void shouldUpdateMentorship() throws BusinessException {

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);

		final Mentorship mentorshipUpdate = this.mentorshipDAO.findById(this.mentorship.getId());

		final LocalDateTime startDate = LocalDateTime.now();
		final LocalDateTime endDate = LocalDateTime.now();

		mentorshipUpdate.setStartDate(startDate);
		mentorshipUpdate.setEndDate(endDate);

		this.mentorshipService.updateMentorship(this.getUserContext(), mentorshipUpdate);
		this.mentorshipService.updateMentorship(this.getUserContext(), mentorshipUpdate);

		TestUtil.assertUpdate(mentorshipUpdate);
	}

	@Test
	public void shouldSynchronizeMentorships() throws BusinessException {

		List<Session> sessions = EntityFactory.gimme(Session.class, 10, SessionTemplate.VALID,
		        new MentorshipProcessor(this.getUserContext(), this.formService, this.careerService,
		                this.programmaticAreaService, this.districtService, this.heathFacilityService,
		                this.questionService, this.tutorService, this.tutoredService, this.cabinetService,
		                this.questionCategoryService));

		sessions = this.mentorshipService.synchronizeMentorships(this.getUserContext(), sessions);

		sessions.forEach(session -> {
			TestUtil.assertCreation(session);

			assertFalse(session.getMentorships().isEmpty());

			session.getMentorships().forEach(mentorship -> {
				TestUtil.assertCreation(mentorship);
				mentorship.getAnswers().forEach(answer -> TestUtil.assertCreation(answer));
			});
		});
	}
}
