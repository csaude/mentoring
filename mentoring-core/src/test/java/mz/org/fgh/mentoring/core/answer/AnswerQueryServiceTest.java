/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.NumericAnswer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.NumericAnswerTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TextAnswerTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
public class AnswerQueryServiceTest extends AbstractSpringTest {

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

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
	private AnswerQueryService answerQueryService;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());

		final Question question1 = EntityFactory.gimme(Question.class, QuestionTemplate.TEXT_QUESTION);
		this.questionService.createQuestion(this.getUserContext(), question1);

		final Question question2 = EntityFactory.gimme(Question.class, QuestionTemplate.TEXT_QUESTION);
		this.questionService.createQuestion(this.getUserContext(), question2);

		final Question question3 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION);
		this.questionService.createQuestion(this.getUserContext(), question3);

		final Set<Question> questions = new HashSet<>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.mentorship.getForm().getProgrammaticArea());

		final Form form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), form, questions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		final Answer answer1 = EntityFactory.gimme(TextAnswer.class, TextAnswerTemplate.VALID);
		final Answer answer2 = EntityFactory.gimme(TextAnswer.class, TextAnswerTemplate.VALID);
		final Answer answer3 = EntityFactory.gimme(NumericAnswer.class, NumericAnswerTemplate.VALID);

		answer1.setQuestion(question1);
		answer2.setQuestion(question2);
		answer3.setQuestion(question3);

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship, form,
		        Arrays.asList(answer1, answer2, answer3));
	}

	@Test
	public void shouldFetchAnswersByMentorship() {

		final List<Answer> answers = this.answerQueryService.fetchAnswersByMentorship(this.getUserContext(),
		        this.mentorship);

		assertFalse(answers.isEmpty());

		for (final Answer answer : answers) {
			assertNotNull(answer.getQuestion());
		}
	}
}
