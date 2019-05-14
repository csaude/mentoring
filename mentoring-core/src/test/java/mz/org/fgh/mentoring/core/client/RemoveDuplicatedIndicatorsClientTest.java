/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_REJECTED_SAMPLES;
import static mz.org.fgh.mentoring.core.indicator.model.SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES;
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
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionProcessor;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryService;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
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

	@Inject
	private QuestionCategoryService questionCategoryService;

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

		this.formService.createForm(this.getUserContext(), indicator.getForm(), this.getFormQuestions());

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

		for (final FormQuestion formQuestion : this.getFormQuestions()) {
			final NumericAnswer answer = new NumericAnswer();
			answer.setQuestion(formQuestion.getQuestion());
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

	private Set<FormQuestion> getFormQuestions() throws BusinessException {

		final FormQuestion formQuestion1 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM,
		        new QuestionProcessor());
		formQuestion1.setUuid(NUMBER_OF_COLLECTED_SAMPLES.getValue());

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        formQuestion1.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), formQuestion1.getQuestion());

		final FormQuestion formQuestion2 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM,
		        new QuestionProcessor());
		formQuestion2.setUuid(NUMBER_OF_TRANSPORTED_SAMPLES.getValue());

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        formQuestion2.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), formQuestion2.getQuestion());

		final FormQuestion formQuestion3 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM,
		        new QuestionProcessor());
		formQuestion3.setUuid(NUMBER_OF_REJECTED_SAMPLES.getValue());

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        formQuestion3.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), formQuestion3.getQuestion());

		final FormQuestion formQuestion4 = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM,
		        new QuestionProcessor());
		formQuestion4.setUuid(NUMBER_OF_RECEIVED_SAMPLES.getValue());

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        formQuestion4.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), formQuestion4.getQuestion());

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(formQuestion1);
		formQuestions.add(formQuestion2);
		formQuestions.add(formQuestion3);
		formQuestions.add(formQuestion4);

		return formQuestions;
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
