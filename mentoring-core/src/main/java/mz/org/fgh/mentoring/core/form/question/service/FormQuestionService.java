/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.question.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQuestionService {

	String NAME = "mz.org.fgh.mentoring.core.form.question.service.FormQuestionService";

	FormQuestion createFormQuestion(final UserContext userContext, final FormQuestion formQuestion) throws BusinessException;
	
	FormQuestion createFormQuestion(final UserContext userContext, final Form form, List<Question> questions) throws BusinessException;


	FormQuestion updateFormQuestion(final UserContext userContext, final FormQuestion formQuestion) throws BusinessException;
}
