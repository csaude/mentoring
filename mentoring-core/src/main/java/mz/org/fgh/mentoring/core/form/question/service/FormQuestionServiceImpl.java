/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.form.question.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQuestionService.NAME)
public class FormQuestionServiceImpl extends AbstractService implements
		FormQuestionService {


	@Override
	public FormQuestion createFormQuestion(final UserContext userContext,
			final FormQuestion formQuestion) throws BusinessException {

		return null;
	}

	@Override
	public FormQuestion updateFormQuestion(final UserContext userContext,
			final FormQuestion formQuestion) throws BusinessException {

		return null;
	}
}
