/**
 *
 */
package mz.org.fgh.mentoring.core.question.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
public interface QuestionCategoryQueryService {

	List<QuestionsCategory> findAllQuestionCategories() throws BusinessException;
}
