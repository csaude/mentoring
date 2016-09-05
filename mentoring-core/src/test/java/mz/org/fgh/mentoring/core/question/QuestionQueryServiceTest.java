/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.util.QuestionCategory;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionQueryServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionService questionService;

	@Inject
	private QuestionQueryService questionQueryService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	private List<Question> questions;

	private Set<Question> createdQuestions = new HashSet<>();

	private ProgrammaticArea programmaticArea;

	@Inject
	private FormService formService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.questions = (EntityFactory.gimme(Question.class, 5, QuestionTemplate.VALID));
		programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);
		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);

		for (final Question question : this.questions) {

			this.questionService.createQuestion(this.getUserContext(), question);
			createdQuestions.add(question);

		}
		form.setProgrammaticArea(programmaticArea);
		programmaticAreaService.createProgrammaticArea(getUserContext(), programmaticArea);
		formService.createForm(getUserContext(), form, createdQuestions);

	}

	@Test
	public void shouldFindQuestionBySelectedFilter() {

		final String code = null;
		final String question = null;
		final QuestionType questionType = null;
		final QuestionCategory questionCategory = null;

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContext(),
				code, question, questionType, questionCategory);

		assertFalse(questions.isEmpty());
		assertEquals(this.questions.size(), questions.size());
	}

	@Test
	public void shouldFindByForm() {
		final List<Question> questions = questionQueryService.findByFormCode(form.getCode());
		assertFalse(questions.isEmpty());

	}

}
