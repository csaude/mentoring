/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.StringNormalizer;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(QuestionService.NAME)
public class QuestionServiceImpl extends AbstractService implements QuestionService {

	@Inject
	private QuestionDAO questionDao;

	@Override
	public Question createQuestion(final UserContext userContext, final Question question) throws BusinessException {

		final String code = this.questionDao.generateCode("MTQ", 8, "0");
		question.setCode(code);
		question.setQuestion(StringNormalizer.normalizeAndUppCase(question.getQuestion()));

		this.questionDao.create(userContext.getUuid(), question);

		return question;
	}

	@Override
	public Question updateQuestion(final UserContext userContext, final Question question) throws BusinessException {
		question.setQuestion(StringNormalizer.normalizeAndUppCase(question.getQuestion()));

		this.questionDao.update(userContext.getUuid(), question);

		return question;
	}
}
