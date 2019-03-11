/**
 *
 */
package mz.org.fgh.mentoring.core.question.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.dao.QuestionCategoryDAO;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
@Service(QuestionCategoryServiceImpl.NAME)
public class QuestionCategoryServiceImpl extends AbstractService implements QuestionCategoryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.question.service.QuestionCategoryServiceImpl";

	@Inject
	private QuestionCategoryDAO questionCategoryDAO;

	@Override
	public QuestionsCategory createQuestionCategory(final UserContext context,
	        final QuestionsCategory questionsCategory) throws BusinessException {
		return this.questionCategoryDAO.create(context.getUuid(), questionsCategory);
	}

	@Override
	public QuestionsCategory updateQuestionCategory(final UserContext context,
	        final QuestionsCategory questionsCategory) throws BusinessException {
		return this.questionCategoryDAO.update(context.getUuid(), questionsCategory);
	}
}
