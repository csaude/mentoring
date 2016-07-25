/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionQueryServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionQueryService questionQueryService;

	private List<Question> questions;

	@Override
	public void setUp() throws BusinessException {
		this.questions = EntityFactory.gimme(Question.class, 5, QuestionTemplate.VALID);

		for (final Question question : this.questions) {
			this.questionService.createQuestion(this.getUserContext(), question);
		}
	}

	@Test
	public void shouldFindQuestionBySelectedFilter() {

		final String code = null;
		final String question = null;
		final QuestionType questionType = null;

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContext(),
				code, question, questionType);

		assertFalse(questions.isEmpty());
		assertEquals(this.questions.size(), questions.size());
	}
}
