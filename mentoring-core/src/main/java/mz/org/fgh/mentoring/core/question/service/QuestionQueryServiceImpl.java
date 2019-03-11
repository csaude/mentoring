/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 */
@Service(QuestionQueryService.NAME)
public class QuestionQueryServiceImpl implements QuestionQueryService {

	@Inject
	private QuestionDAO questionDAO;

	@Override
	public List<Question> findQuestionsBySelectedFilter(final UserContext userContext, final String code,
			final String question, final QuestionType questionType, final QuestionCategory questionCategory) {

		return this.questionDAO.findBySelectedFilter(code, question, questionType, questionCategory,
				LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Question> findByFormCode(final String code) {
		return this.questionDAO.findByFormCode(code, LifeCycleStatus.ACTIVE);
	}

	@Override
	public Question findQuestionByUuid(final UserContext userContext, final String uuid) throws BusinessException {
		return this.questionDAO.findByuuid(uuid, LifeCycleStatus.ACTIVE);
	}
}
