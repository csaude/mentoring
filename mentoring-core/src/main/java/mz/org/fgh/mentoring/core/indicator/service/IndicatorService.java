/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;

/**
 * @author Stélio Moiane
 *
 */
public interface IndicatorService {

	String NAME = "mz.org.fgh.mentoring.core.indicator.service.IndicatorService";

	Indicator createIndicator(final UserContext userContext, final Indicator indicator, final Form form,
	        final List<Answer> answers) throws BusinessException;
}
