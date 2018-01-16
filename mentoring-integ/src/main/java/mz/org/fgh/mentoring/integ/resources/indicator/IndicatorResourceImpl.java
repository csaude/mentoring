/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.indicator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.model.SampleIndicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryService;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.integ.resources.mentorship.AnswerHelper;

/**
 * @author Stélio Moiane
 *
 */
@Service(IndicatorResource.NAME)
@Path("indicators")
public class IndicatorResourceImpl implements IndicatorResource {

	@Inject
	private IndicatorService indicatorService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	@Inject
	private QuestionQueryService questionQueryService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	@Override
	public JResponse<IndicatorBeanResource> synchronizeIndicators(final IndicatorBeanResource resource)
	        throws BusinessException {

		for (final IndicatorHelper indicatorHelper : resource.getIndicators()) {

			final Indicator indicator = indicatorHelper.getIndicator();

			final Tutor tutor = this.getTutor(resource);

			final Form form = this.getForm(resource.getUserContext(), indicator);

			final HealthFacility healthFacility = this.getHealthFaclity(resource.getUserContext(), indicator);

			indicator.setTutor(tutor);
			indicator.setHealthFacility(healthFacility);

			final List<Answer> answers = new ArrayList<>();

			for (final AnswerHelper answerHelper : indicatorHelper.getAnswerHelpers()) {

				final Question question = this.questionQueryService.findQuestionByUuid(resource.getUserContext(),
				        answerHelper.getQuestionUuid());
				final Answer answer = question.getQuestionType().getAnswer();
				answer.setQuestion(question);
				answer.setValue(answerHelper.getValue());

				answers.add(answer);
			}

			this.indicatorService.synchronizeIndicator(resource.getUserContext(), indicator, form, answers);
			resource.addIndicatorUuid(indicator.getUuid());
		}

		resource.setIndicators(null);
		return JResponse.ok(resource).build();
	}

	private HealthFacility getHealthFaclity(final UserContext userContext, final Indicator indicator)
	        throws BusinessException {
		return this.healthFacilityQueryService.findHealthFacilityByUuid(userContext,
		        indicator.getHealthFacility().getUuid());
	}

	private Form getForm(final UserContext userContext, final Indicator indicator) throws BusinessException {
		return this.formQueryService.findFormByUuid(userContext, indicator.getForm().getUuid());
	}

	private Tutor getTutor(final IndicatorBeanResource indicatorBeanResource) throws BusinessException {

		final UserContext userContext = indicatorBeanResource.getUserContext();

		return this.tutorQueryService.fetchTutorByUuid(userContext, userContext.getUuid());
	}

	@Override
	public JResponse<List<SampleIndicator>> findSimpleIndicators(final String districtUuid,
	        final String healthFacilityUuid, final String formUuid, final String startDate, final String endDate)
	        throws BusinessException {

		final IndicatorHelper indicatorHelper = new IndicatorHelper(districtUuid, healthFacilityUuid, formUuid);

		final List<SampleIndicator> sampleIndicators = this.indicatorQueryService.findSampleIndicatorsBySelectedFilter(
		        indicatorHelper.getDistrict(), indicatorHelper.getHealthFacility(), indicatorHelper.getForm(),
		        indicatorHelper.getLocalDate(startDate), indicatorHelper.getLocalDate(endDate));

		return JResponse.ok(sampleIndicators).build();
	}
}
