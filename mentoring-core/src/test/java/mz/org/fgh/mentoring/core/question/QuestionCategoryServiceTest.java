/**
 *
 */
package mz.org.fgh.mentoring.core.question;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionCategoryTemplate;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionCategoryServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionCategoryService questionsCategoryService;

	@Override
	public void setUp() throws BusinessException {
	}

	@Test
	public void shouldCreateQuestionCategory() throws BusinessException {

		final QuestionsCategory questionsCategory = EntityFactory.gimme(QuestionsCategory.class,
		        QuestionCategoryTemplate.VALID);
		this.questionsCategoryService.createQuestionCategory(this.getUserContext(), questionsCategory);

		TestUtil.assertCreation(questionsCategory);
	}

	@Test
	public void shouldUpdateQuestionCategory() throws BusinessException {
		final QuestionsCategory questionsCategory = EntityFactory.gimme(QuestionsCategory.class,
		        QuestionCategoryTemplate.VALID);

		this.questionsCategoryService.createQuestionCategory(this.getUserContext(), questionsCategory);
		this.questionsCategoryService.updateQuestionCategory(this.getUserContext(), questionsCategory);

		TestUtil.assertUpdate(questionsCategory);
	}
}
