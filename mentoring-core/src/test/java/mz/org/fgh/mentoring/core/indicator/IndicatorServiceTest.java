/**
 *
 */
package mz.org.fgh.mentoring.core.indicator;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.IndicatorTemplate;
import mz.org.fgh.mentoring.core.form.FormBuilder;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryService;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class IndicatorServiceTest extends AbstractSpringTest {

	@Inject
	private IndicatorService indicatorService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	@Inject
	private CareerService careerService;

	@Inject
	private TutorService tutorService;

	@Inject
	private DistrictService districtService;

	@Inject
	private HealthFacilityService healthFacilityService;

	@Inject
	private FormBuilder formBuilder;

	private Indicator indicator;

	private Form form;

	private Question question;

	@Override
	public void setUp() throws BusinessException {
		this.indicator = EntityFactory.gimme(Indicator.class, IndicatorTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.indicator.getTutor().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.indicator.getTutor());
		this.districtService.createDistrict(this.getUserContext(), this.indicator.getHealthFacility().getDistrict());
		this.healthFacilityService.createHealthFacility(this.getUserContext(), this.indicator.getHealthFacility());

		this.form = this.formBuilder.build();
		this.question = this.form.getFormQuestions().iterator().next().getQuestion();
	}

	@Test
	public void shouldCreateIndicator() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.question);
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.form, Arrays.asList(answer));

		TestUtil.assertCreation(this.indicator);
	}

	@Test
	public void shouldUpdateIndicator() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.question);
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.createIndicator(this.getUserContext(), this.indicator, this.form, Arrays.asList(answer));

		final Answer answerUpdated = new BooleanAnswer();
		answerUpdated.setQuestion(this.question);
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
			Assert.assertEquals(answerUpdated.getValue(), answerFound.getValue());
		}
	}

	@Test
	public void shouldSynchronizeIndicators() throws BusinessException {

		final Answer answer = new BooleanAnswer();
		answer.setQuestion(this.question);
		answer.setValue(String.valueOf(Boolean.TRUE));

		this.indicatorService.synchronizeIndicator(this.getUserContext(), this.indicator, this.form,
				Arrays.asList(answer));

		TestUtil.assertCreation(this.indicator);
	}
}
