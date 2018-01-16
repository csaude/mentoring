/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.service;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.dao.IndicatorDAO;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;

/**
 * @author Stélio Moiane
 *
 */
@Service(IndicatorService.NAME)
public class IndicatorServiceImpl extends AbstractService implements IndicatorService {

	@Inject
	private IndicatorDAO indicatorDAO;

	@Inject
	private AnswerService answerService;

	@Inject
	private IndicatorQueryService indicatorQueryService;

	@Inject
	private PropertyValues propertyValues;

	@Override
	public Indicator createIndicator(final UserContext userContext, final Indicator indicator, final Form form,
	        final List<Answer> answers) throws BusinessException {

		if (answers.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.indicator.with.no.answers"));
		}

		final String code = this.indicatorDAO.generateCode("IN", 8, "0");
		indicator.setCode(code);

		indicator.setForm(form);
		this.indicatorDAO.create(userContext.getUuid(), indicator);

		for (final Answer answer : answers) {
			answer.setIndicator(indicator);
			answer.setForm(form);
			this.answerService.createAnswer(userContext, answer);
		}

		return indicator;
	}

	@Override
	public Indicator updateIndicator(final UserContext userContext, final Indicator indicator,
	        final List<Answer> answers, final Indicator foundIndicator) throws BusinessException {

		foundIndicator.setTutor(indicator.getTutor());
		this.indicatorDAO.update(userContext.getUuid(), foundIndicator);

		for (final Answer answer : answers) {
			this.updateValue(userContext, answer, foundIndicator.getAnswers());
		}

		return foundIndicator;
	}

	private void updateValue(final UserContext userContext, final Answer answer, final Set<Answer> answers)
	        throws BusinessException {

		for (final Answer answerFound : answers) {
			if (answer.getQuestion().getUuid().equals(answerFound.getQuestion().getUuid())) {
				answerFound.setValue(answer.getValue());
				this.answerService.updateAnswer(userContext, answerFound);
				return;
			}
		}
	}

	@Override
	public Indicator updateIndicator(final UserContext userContext, final Indicator indicator)
	        throws BusinessException {
		return this.indicatorDAO.update(userContext.getUuid(), indicator);
	}

	@Override
	public Indicator synchronizeIndicator(final UserContext userContext, final Indicator indicator, final Form form,
	        final List<Answer> answers) throws BusinessException {

		final List<Indicator> foundIndicators = this.indicatorQueryService
		        .findIndicatorsByHealthFacilityFormAndReferredMonth(indicator.getHealthFacility(), form,
		                indicator.getReferredMonth());

		if (!foundIndicators.isEmpty()) {
			final Indicator foundIndicator = foundIndicators.get(0);
			this.updateIndicator(userContext, indicator, answers, foundIndicator);
		}
		else {
			this.createIndicator(userContext, indicator, form, answers);
		}

		return indicator;
	}
}
