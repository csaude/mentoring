/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.question.service;

import java.util.List;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.question.dao.FormQuestionDAO;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.model.Question;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQuestionService.NAME)
public class FormQuestionServiceImpl extends AbstractService implements
		FormQuestionService {

	@Inject
	private FormQuestionDAO formQuestionDao;

	@Override
	public FormQuestion createFormQuestion(final UserContext userContext,
			final FormQuestion formQuestion) throws BusinessException {

		return formQuestionDao.create(userContext.getId(), formQuestion);
	}

	@Override
	public FormQuestion updateFormQuestion(final UserContext userContext,
			final FormQuestion formQuestion) throws BusinessException {

		return formQuestionDao.update(userContext.getId(), formQuestion);
	}

	@Override
	public FormQuestion createFormQuestion(UserContext userContext, Form form,
			List<Question> questions) throws BusinessException {

		return null;
	}
}
