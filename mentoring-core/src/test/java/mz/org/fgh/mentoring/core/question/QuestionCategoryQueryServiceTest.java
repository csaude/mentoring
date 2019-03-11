/**
 *
 */
package mz.org.fgh.mentoring.core.question;

import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionCategoryTemplate;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionCategoryQueryServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionCategoryService questionCategoryService;

	@Inject
	private QuestionCategoryQueryService questionCategoryQueryService;

	@Override
	public void setUp() throws BusinessException {
		final List<QuestionsCategory> questionCategories = EntityFactory.gimme(QuestionsCategory.class, 10,
		        QuestionCategoryTemplate.VALID);

		for (final QuestionsCategory questionsCategory : questionCategories) {
			this.questionCategoryService.createQuestionCategory(this.getUserContext(), questionsCategory);
		}
	}

	@Test
	public void shouldFindAllQuestionCategories() throws BusinessException {
		final List<QuestionsCategory> questionsCategories = this.questionCategoryQueryService
		        .findAllQuestionCategories();

		assertFalse(questionsCategories.isEmpty());
	}

}
