/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Ignore;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class FormServiceTest extends AbstractSpringTest {

	@Inject
	private FormService formService;

	@Inject
	private FormDAO formDAO;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private QuestionService questionService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {
		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.form.getProgrammaticArea());
	}

	@Test(expected = BusinessException.class)
	public void shouldNotCreateFormWithoutQuestions() throws BusinessException {

		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		TestUtil.assertCreation(this.form);
	}

	@Test
	public void shouldCreateForm() throws BusinessException {

		final Question question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), question);

		final Set<Question> questions = new HashSet<>();
		questions.add(question);

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questions);

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);
		assertFalse(fetchedByFormId.getFromQuestions().isEmpty());

		fetchedByFormId.getFromQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
		});
	}

	@Ignore
	public void shouldUpdateForm() throws BusinessException {

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.form.getProgrammaticArea());

		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		final Form updateForm = this.formDAO.findById(this.form.getId());

		this.formService.updateForm(this.getUserContext(), updateForm);

		TestUtil.assertUpdate(updateForm);
	}
}
