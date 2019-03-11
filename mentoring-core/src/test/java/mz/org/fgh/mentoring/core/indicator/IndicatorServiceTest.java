/**
 *
 */
package mz.org.fgh.mentoring.core.indicator;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
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
public class IndicatorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

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
	private IndicatorService indicatorService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	@Inject
	private QuestionCategoryService questionCategoryService;

	private Indicator indicator;

	private FormQuestion formQuestion;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.indicator.getTutor());

		this.formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);
		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        this.formQuestion.getQuestion().getQuestionsCategory());
		this.questionService.createQuestion(this.getUserContext(), this.formQuestion.getQuestion());

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(this.formQuestion);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.indicator.getForm().getProgrammaticArea());

		this.form = this.indicator.getForm();
		this.formService.createForm(this.getUserContext(), this.form, formQuestions);

		this.districtService.createDistrict(this.getUserContext(), this.indicator.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.indicator.getHealthFacility());

	}

	@Test
	public void shouldCreateIndicator() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.formQuestion.getQuestion());
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.form, Arrays.asList(answer));

		TestUtil.assertCreation(this.indicator);
	}

	@Test
	public void shouldUpdateIndicator() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.formQuestion.getQuestion());
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.form, Arrays.asList(answer));

		final Answer answerUpdated = new BooleanAnswer();
		answerUpdated.setQuestion(this.formQuestion.getQuestion());
		answerUpdated.setValue(String.valueOf(Boolean.FALSE));

		final List<Indicator> foundIndicators = this.indicatorQueryService
		        .findIndicatorsByHealthFacilityFormAndReferredMonth(this.indicator.getHealthFacility(), this.form,
		                this.indicator.getReferredMonth());

		final Indicator foundIndicator = foundIndicators.get(0);

		this.indicatorService.updateIndicator(this.getUserContext(), this.indicator, Arrays.asList(answerUpdated),
		        foundIndicator);

		TestUtil.assertUpdate(foundIndicator);
		for (final Answer answerFound : foundIndicator.getAnswers()) {
			TestUtil.assertUpdate(answerFound);
			assertEquals(answerUpdated.getValue(), answerFound.getValue());
		}
	}

	@Test
	public void shouldSynchronizeIndicators() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.formQuestion.getQuestion());
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.synchronizeIndicator(this.getUserContext(), this.indicator, this.form,
		        Arrays.asList(answer));

		TestUtil.assertCreation(this.indicator);
	}
}
