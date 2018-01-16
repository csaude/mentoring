/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;
import java.util.logging.Logger;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryService;
import mz.org.fgh.mentoring.core.indicator.service.IndicatorService;

/**
 * @author Stélio Moiane
 *
 */
public class RemoveDulicatedIndicatorsClient extends ClientConfig<RemoveDulicatedIndicatorsClient> {

	private IndicatorQueryService indicatorQueryService;

	private IndicatorService indicatorService;

	private AnswerService answerService;

	private static Logger logger = Logger.getLogger(RemoveDulicatedIndicatorsClient.class.getName());

	@Override
	public int process(final RemoveDulicatedIndicatorsClient client) throws BusinessException {

		final List<DuplicatedIndicator> duplicatedIndicators = this.indicatorQueryService.findDuplicatedIndicators();

		if (duplicatedIndicators.isEmpty()) {
			return 0;
		}

		logger.info("A total of " + duplicatedIndicators.size() + " Indicator(s) will be cleaned in the system...");

		for (final DuplicatedIndicator duplicatedIndicator : duplicatedIndicators) {

			final List<Indicator> foundIndicators = this.indicatorQueryService
			        .findIndicatorsByHealthFacilityFormAndReferredMonth(duplicatedIndicator.getHealthFacility(),
			                duplicatedIndicator.getForm(), duplicatedIndicator.getReferredMonth());

			final Indicator lastUpdateIndicator = foundIndicators.get(0);

			foundIndicators.stream().filter(indicator -> lastUpdateIndicator.getId() != indicator.getId())
			        .forEach(indicator -> {
				        indicator.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
				        try {
					        this.indicatorService.updateIndicator(this.getUserContext(), indicator);
				        }
				        catch (final BusinessException e) {
					        e.printStackTrace();
				        }

				        indicator.getAnswers().forEach(answer -> {
					        answer.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
					        try {
						        this.answerService.updateAnswer(this.getUserContext(), answer);
					        }
					        catch (final BusinessException e) {
						        e.printStackTrace();
					        }
				        });
			        });
		}

		logger.info("A total of " + duplicatedIndicators.size()
		        + " Indicator(s) were successfully cleaned in the system...");

		return duplicatedIndicators.size();
	}

	public void setIndicatorQueryService(final IndicatorQueryService indicatorQueryService) {
		this.indicatorQueryService = indicatorQueryService;
	}

	public void setIndicatorService(final IndicatorService indicatorService) {
		this.indicatorService = indicatorService;
	}

	public void setAnswerService(final AnswerService answerService) {
		this.answerService = answerService;
	}

	public static void main(final String[] args) throws BusinessException {
		logger.info("The Client is Starting to execute ........");

		final RemoveDulicatedIndicatorsClient client = new RemoveDulicatedIndicatorsClient();
		client.setup();

		client.setIndicatorService(client.getBean(IndicatorService.class));
		client.setIndicatorQueryService(client.getBean(IndicatorQueryService.class));
		client.setAnswerService(client.getBean(AnswerService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
