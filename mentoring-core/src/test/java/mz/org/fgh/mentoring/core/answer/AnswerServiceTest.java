/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.BooleanAnswer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.BooleanAnswerTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TextAnswerTemplate;

/**
 * @author Stélio Moiane
 *
 */
public class AnswerServiceTest extends AbstractSpringTest {

	@Inject
	private AnswerService answerService;

	@Override
	public void setUp() throws BusinessException {

	}

	@Test
	public void shouldCreateTextAnswer() throws BusinessException {
		final Answer answer = EntityFactory.gimme(TextAnswer.class, TextAnswerTemplate.VALID);

		this.answerService.createAnswer(this.getUserContext(), answer);

		TestUtil.assertCreation(answer);
	}

	@Test
	public void shouldCreateBooleanAnswer() throws BusinessException {
		final Answer answer = EntityFactory.gimme(BooleanAnswer.class, BooleanAnswerTemplate.VALID);

		this.answerService.createAnswer(this.getUserContext(), answer);

		TestUtil.assertCreation(answer);
	}
}
