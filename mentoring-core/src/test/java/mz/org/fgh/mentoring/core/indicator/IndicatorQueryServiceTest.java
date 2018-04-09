/**
 *
 */
package mz.org.fgh.mentoring.core.indicator;

import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_REJECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionProcessor;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.indicator.model.AnalysisTable;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.model.SampleIndicator;
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
public class IndicatorQueryServiceTest extends AbstractSpringTest {

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
	private HealthFacilityService heathFacilityService;

	@Inject
	private IndicatorService indicatorService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	private Indicator indicator;

	@Override
	public void setUp() throws BusinessException {

		this.indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.indicator.getTutor());

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.indicator.getForm().getProgrammaticArea());

		this.districtService.createDistrict(this.getUserContext(), this.indicator.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.indicator.getHealthFacility());

		this.formService.createForm(this.getUserContext(), this.indicator.getForm(), this.getQuestions());

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.indicator.getForm(),
		        this.getAnswers());
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
		question4.setUuid(NUMBER_OF_RECEIVED_SAMPLES.getValue());
		this.questionService.createQuestion(this.getUserContext(), question4);

		final Set<Question> questions = new HashSet<>();
		questions.add(question1);
		questions.add(question2);
		questions.add(question3);
		questions.add(question4);

		return questions;
	}

	private int getRandomInt() {

		final int max = 100;
		final int min = 0;

		final Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
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

	@Test
	public void shouldFindSampleIndicatorsBySelectedFilter() throws BusinessException {

		final List<SampleIndicator> sampleIndicators = this.indicatorQueryService.findSampleIndicatorsBySelectedFilter(
		        this.indicator.getHealthFacility().getDistrict(), this.indicator.getHealthFacility(),
		        this.indicator.getForm(), this.indicator.getReferredMonth(), this.indicator.getReferredMonth());

		assertFalse(sampleIndicators.isEmpty());
		assertEquals(1, sampleIndicators.size());
	}

	@Test
	public void shouldFindIndicatorByHealthFacilityFormAndReferredMonth() throws BusinessException {

		final List<Indicator> indicators = this.indicatorQueryService
		        .findIndicatorsByHealthFacilityFormAndReferredMonth(this.indicator.getHealthFacility(),
		                this.indicator.getForm(), this.indicator.getReferredMonth());

		assertFalse(indicators.isEmpty());
		assertNotNull(indicators.get(0));
	}

	@Test
	public void shoulFindDuplicatedIdicators() throws BusinessException {

		for (int i = 0; i < 10; i++) {

			final Indicator indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
			indicator.setTutor(this.indicator.getTutor());
			indicator.setForm(this.indicator.getForm());
			indicator.setHealthFacility(this.indicator.getHealthFacility());
			indicator.setReferredMonth(this.indicator.getReferredMonth());

			this.indicatorService.createIndicator(this.getUserContext(), indicator, indicator.getForm(),
			        this.getAnswers());
		}

		final List<DuplicatedIndicator> duplicatedIndicators = this.indicatorQueryService.findDuplicatedIndicators();

		assertFalse(duplicatedIndicators.isEmpty());
		assertTrue(duplicatedIndicators.size() == 1);
	}

	@Test
	public void shouldFindAnalysisTableBySelectedFilter() {

		final List<AnalysisTable> analysisTables = this.indicatorQueryService.findAnalysisTableBySelectedFilter(
		        this.indicator.getHealthFacility().getDistrict(), this.indicator.getReferredMonth(),
		        this.indicator.getReferredMonth());

		assertFalse(analysisTables.isEmpty());
	}
}
