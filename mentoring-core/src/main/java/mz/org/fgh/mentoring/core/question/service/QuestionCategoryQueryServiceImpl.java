/**
 *
 */
package mz.org.fgh.mentoring.core.question.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.question.dao.QuestionCategoryDAO;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
@Service(QuestionCategoryQueryServiceImpl.NAME)
public class QuestionCategoryQueryServiceImpl implements QuestionCategoryQueryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.question.service.QuestionCategoryQueryServiceImpl";

	@Inject
	private QuestionCategoryDAO questionCategoryDAO;

	@Override
	public List<QuestionsCategory> findAllQuestionCategories() throws BusinessException {
		return this.questionCategoryDAO.findAll(LifeCycleStatus.ACTIVE);
	}
}
