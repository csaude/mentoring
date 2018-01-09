/**
 *
 */
package mz.org.fgh.mentoring.core.indicator;

import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_REJECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMER_OF_RECEIVED_SAMPLES;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionProcessor;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
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

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.indicator.getForm().getProgrammaticArea());

		final Form form = this.indicator.getForm();
		this.formService.createForm(this.getUserContext(), form, questions);

		this.districtService.createDistrict(this.getUserContext(), this.indicator.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.indicator.getHealthFacility());

		final Answer answer1 = new NumericAnswer();
		answer1.setQuestion(question1);
		answer1.setValue(String.valueOf(50));

		final Answer answer2 = new NumericAnswer();
		answer2.setQuestion(question2);
		answer2.setValue(String.valueOf(10));

		final Answer answer3 = new NumericAnswer();
		answer3.setQuestion(question3);
		answer3.setValue(String.valueOf(40));

		final Answer answer4 = new NumericAnswer();
		answer4.setQuestion(question4);
		answer4.setValue(String.valueOf(30));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, form,
		        Arrays.asList(answer1, answer2, answer3, answer4));
	}

	@Test
	public void shouldFindSampleIndicatorsBySelectedFilter() throws BusinessException {

		final List<SampleIndicator> sampleIndicators = this.indicatorQueryService.findSampleIndicatorsBySelectedFilter(
		        this.indicator.getHealthFacility().getDistrict(), this.indicator.getHealthFacility(),
		        this.indicator.getForm(), this.indicator.getReferredMonth(), this.indicator.getReferredMonth());

		assertFalse(sampleIndicators.isEmpty());
		assertEquals(4, sampleIndicators.size());
	}
}
