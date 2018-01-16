/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_REJECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMER_OF_RECEIVED_SAMPLES;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.NumericAnswer;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionProcessor;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryService;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class RemoveDuplicatedIndicatorsClientTest extends AbstractSpringTest {

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

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
	private IndicatorService indicatorService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	@Inject
	private AnswerService answerService;

	private RemoveDulicatedIndicatorsClient client;

	@Override
	public void setUp() throws BusinessException {

		final Indicator indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), indicator.getTutor());

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        indicator.getForm().getProgrammaticArea());

		this.districtService.createDistrict(this.getUserContext(), indicator.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), indicator.getHealthFacility());

		this.formService.createForm(this.getUserContext(), indicator.getForm(), this.getQuestions());

		for (int i = 0; i < 10; i++) {
			this.createIndicator(indicator);
		}

		this.client = new RemoveDulicatedIndicatorsClient();
	}

	private void createIndicator(final Indicator indicator) throws BusinessException {

		final Indicator newIndicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		newIndicator.setTutor(indicator.getTutor());
		newIndicator.setHealthFacility(indicator.getHealthFacility());
		newIndicator.setReferredMonth(indicator.getReferredMonth());

		this.indicatorService.createIndicator(this.getUserContext(), newIndicator, indicator.getForm(),
		        this.getAnswers());
	}

	private List<Answer> getAnswers() throws BusinessException {
		final List<Answer> answers = new ArrayList<Answer>();

		for (final Question question : this.getQuestions()) {
			final NumericAnswer answer = new NumericAnswer();
			answer.setQuestion(question);
			answer.setValue(String.valueOf(this.getRandomInt()));
			answers.add(answer);
		}
		return answers;
	}

	private int getRandomInt() {

		final int max = 100;
		final int min = 0;

		final Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	private Set<Question> getQuestions() throws BusinessException {

		final Question question1 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question1.setUuid(NUMBER_OF_COLLECTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question1);

		final Question question2 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question2.setUuid(NUMBER_OF_TRANSPORTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question2);

		final Question question3 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question3.setUuid(NUMBER_OF_REJECTED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question3);

		final Question question4 = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION,
		        new QuestionProcessor());
		question4.setUuid(NUMER_OF_RECEIVED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question4);

		final Set<Question> questions = new HashSet<>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);

		return questions;
	}

	@Test
	public void shouldRemoveDuplicatedIndicators() throws BusinessException {

		final List<DuplicatedIndicator> duplicatedIndicators = this.indicatorQueryService.findDuplicatedIndicators();
		this.client.setIndicatorQueryService(this.indicatorQueryService);
		this.client.setIndicatorService(this.indicatorService);
		this.client.setAnswerService(this.answerService);

		final int records = this.client.process(this.client);

		assertEquals(duplicatedIndicators.size(), records);
	}
}
