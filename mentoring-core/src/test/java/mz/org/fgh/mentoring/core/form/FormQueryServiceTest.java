/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Stélio Moiane
 *
 */
public class FormQueryServiceTest extends AbstractSpringTest {

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private FormQueryService formQueryService;

	private Form createdform;

	@Override
	public void setUp() throws BusinessException {
		final Form form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), form.getProgrammaticArea());

		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), question);

		final Set<Question> questions = new HashSet<>();
		questions.add(question);

		this.createdform = this.formService.createForm(this.getUserContext(), form, questions);
	}

	@Test
	public void shouldFetchByForm() throws BusinessException {

		final Form fetchedFrom = this.formQueryService.fetchByForm(this.getUserContext(), this.createdform);

		assertEquals(this.createdform.getCode(), fetchedFrom.getCode());
		assertFalse(fetchedFrom.getFromQuestions().isEmpty());

		fetchedFrom.getFromQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), fetchedFrom.getCode());
			assertNotNull(formQuestion.getQuestion());
		});
	}
}
