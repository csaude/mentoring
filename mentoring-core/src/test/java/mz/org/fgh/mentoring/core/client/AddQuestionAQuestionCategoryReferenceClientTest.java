/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class AddQuestionAQuestionCategoryReferenceClientTest extends AbstractSpringTest {

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionCategoryService questionCategoryService;

	@Inject
	private QuestionQueryService questionQueryService;

	private AddQuestionCategoryAndUpdateQuestionCategoryClient client;

	@Override
	public void setUp() throws BusinessException {

		final List<Question> questions = EntityFactory.gimme(Question.class, 10, QuestionTemplate.TEXT_QUESTION);

		for (final Question question : questions) {
			this.questionCategoryService.createQuestionCategory(this.getUserContext(), question.getQuestionsCategory());
			this.questionService.createQuestion(this.getUserContext(), question);
		}

		this.client = new AddQuestionCategoryAndUpdateQuestionCategoryClient();
	}

	@Test
	public void shouldAddQuestionCategoryAndUpdateQuestionReference() throws BusinessException {

		this.client.setQuestionCategoryService(this.questionCategoryService);
		this.client.setQuestionService(this.questionService);
		this.client.setQuestionQueryService(this.questionQueryService);

		final int process = this.client.process(this.client);

		assertEquals(process, QuestionCategory.values().length);
	}
}
