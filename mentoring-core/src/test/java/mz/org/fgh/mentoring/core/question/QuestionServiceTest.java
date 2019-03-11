/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class QuestionServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionDAO questionDao;

	@Inject
	private QuestionCategoryService questionCategoryService;

	@Override
	public void setUp() throws BusinessException {
	}

	@Test
	public void shouldCreateTextQuestion() throws BusinessException {
		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.TEXT_QUESTION);
		this.questionCategoryService.createQuestionCategory(this.getUserContext(), question.getQuestionsCategory());
		this.questionService.createQuestion(this.getUserContext(), question);
		TestUtil.assertCreation(question);
		assertEquals(QuestionType.TEXT, question.getQuestionType());
	}

	@Test
	public void shouldCreateNumericQuestion() throws BusinessException {
		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.NUMERIC_QUESTION);
		this.questionCategoryService.createQuestionCategory(this.getUserContext(), question.getQuestionsCategory());
		this.questionService.createQuestion(this.getUserContext(), question);
		TestUtil.assertCreation(question);
		assertEquals(QuestionType.NUMERIC, question.getQuestionType());
	}

	@Test
	public void shouldUpdateQuestion() throws BusinessException {
		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionCategoryService.createQuestionCategory(this.getUserContext(), question.getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), question);

		final Question questionUpdate = this.questionDao.findById(question.getId());

		this.questionService.updateQuestion(this.getUserContext(), questionUpdate);

		TestUtil.assertUpdate(questionUpdate);
	}
}
