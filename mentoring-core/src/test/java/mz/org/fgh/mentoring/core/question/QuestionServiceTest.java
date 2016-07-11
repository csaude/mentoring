/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.question;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

import org.junit.Test;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class QuestionServiceTest extends AbstractSpringTest {

	private Question question;

	@Inject
	private QuestionService questionService;

	@Override
	public void setUp() throws BusinessException {
		question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
	}

	@Test
	public void shouldCreateQuestion() throws BusinessException {
		questionService.createQuestion(getUserContext(), question);
		TestUtil.assertCreation(this.question);


	}

	@Test
	public void shouldUpdateQuestion() throws BusinessException {

	}

}
