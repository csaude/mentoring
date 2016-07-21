/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.service;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;

import org.springframework.stereotype.Service;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(QuestionService.NAME)
public class QuestionServiceImpl extends AbstractService implements QuestionService {

	@Inject
	private QuestionDAO questionDao;



	@Override
	public Question createQuestion(final UserContext userContext, final  Question question) throws BusinessException {

		// TODO generate code just a sample
		final String code = this.questionDao.generateCode("MT", 8, "0");
		question.setCode(code);

		return this.questionDao.create(userContext.getId(), question);
	}

	@Override
	public Question updateQuestion(final UserContext userContext, final Question question) throws BusinessException {

		return this.questionDao.update(userContext.getId(), question);

	}
}
