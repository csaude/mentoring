/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.service;

import java.util.Set;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormService {

	String NAME = "mz.org.fgh.mentoring.core.form.service.FormService";

	Form createForm(final UserContext userContext, final Form form, Set<FormQuestion> formQuestions)
	        throws BusinessException;

	Form updateForm(final UserContext userContext, final Form form, Set<FormQuestion> formQuestions)
	        throws BusinessException;
}
