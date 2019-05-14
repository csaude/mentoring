/**
 *
 */
package mz.org.fgh.mentoring.core.question.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
public interface QuestionCategoryService {

	QuestionsCategory createQuestionCategory(UserContext userContext, QuestionsCategory questionsCategory)
	        throws BusinessException;

	QuestionsCategory updateQuestionCategory(UserContext userContext, QuestionsCategory questionsCategory)
	        throws BusinessException;
}
