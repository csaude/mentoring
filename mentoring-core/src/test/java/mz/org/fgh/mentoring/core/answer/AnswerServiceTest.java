/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.answer;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Ignore;
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
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TextAnswerTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class AnswerServiceTest extends AbstractSpringTest {

	@Inject
	private AnswerService answerService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.form.getProgrammaticArea());

		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), question);

		final Set<Question> questions = new HashSet<>();
		questions.add(question);

		this.formService.createForm(this.getUserContext(), this.form, questions);
	}

	@Test
	@Ignore
	public void shouldCreateTextAnswer() throws BusinessException {
		final Answer answer = EntityFactory.gimme(TextAnswer.class, TextAnswerTemplate.VALID);
		answer.setForm(this.form);

		this.answerService.createAnswer(this.getUserContext(), answer);

		TestUtil.assertCreation(answer);
	}

	@Ignore
	@Test
	public void shouldCreateBooleanAnswer() throws BusinessException {
		final Answer answer = EntityFactory.gimme(BooleanAnswer.class, BooleanAnswerTemplate.VALID);
		answer.setForm(this.form);

		this.answerService.createAnswer(this.getUserContext(), answer);

		TestUtil.assertCreation(answer);
	}
}
