/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.question.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQuestionService {

	String NAME = "mz.org.fgh.mentoring.core.form.question.service.FormQuestionService";

	FormQuestion createFormQuestion(final UserContext userContext, final FormQuestion formQuestion)
			throws BusinessException;

	FormQuestion updateFormQuestion(final UserContext userContext, final FormQuestion formQuestion)
			throws BusinessException;
}
