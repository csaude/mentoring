/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(FormQuestionService.NAME)
public class FormQuestionServiceImpl extends AbstractService implements FormQuestionService {

	@Inject
	private FormQuestionDAO formQuestionDAO;

	@Override
	public FormQuestion createFormQuestion(final UserContext userContext, final FormQuestion formQuestion)
			throws BusinessException {

		return this.formQuestionDAO.create(userContext.getUuid(), formQuestion);
	}

	@Override
	public FormQuestion updateFormQuestion(final UserContext userContext, final FormQuestion formQuestion)
			throws BusinessException {
		return this.formQuestionDAO.update(userContext.getUuid(), formQuestion);
	}
}
