/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface FormQuestionQueryService {

	String NAME = "mz.org.fgh.mentoring.core.formquestion.service.FormQuestionQueryService";

	List<FormQuestion> fetchFormQuestionsByTutor(final UserContext userContext);

	List<FormQuestion> findFormQuestionByForm(final Form form) throws BusinessException;
}
