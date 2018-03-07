/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.form;

import com.sun.jersey.api.JResponse;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

import java.util.List;

/**
 * @author Stélio Moiane
 *
 */
public interface FormQuestionServiceResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.form.FormQuestionServiceResource";

	JResponse<List<FormQuestion>> fetchFormQuestions(Long formId) throws BusinessException;
}
