/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

import java.util.List;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQuestionService {

	String NAME = "mz.org.fgh.mentoring.core.formquestion.service.FormQuestionService";

	FormQuestion createFormQuestion(final UserContext userContext, final FormQuestion formQuestion) throws BusinessException;
	FormQuestion updateFormQuestion(final UserContext userContext, final FormQuestion formQuestion) throws BusinessException;

	/**
	 *
	 * @param formId
	 * @return Set of formQuestions associated with the form with passed code
	 * @throws BusinessException
	 */
	List<FormQuestion> getFormQuestionByFormId(final Long formId)  throws BusinessException;
}
