/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
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

	@Inject
	private QuestionCategoryService questionCategoryService;

	private ProgrammaticArea programmaticArea;

	private Form form;

	private FormQuestion formQuestion;

	private List<FormQuestion> formQuestions;

	@Override
	public void setUp() throws BusinessException {

		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);

		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);

		this.form.setProgrammaticArea(
		        this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.programmaticArea));
		this.formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.WITH_NO_FORM);

		this.formQuestions = EntityFactory.gimme(FormQuestion.class, 10, FormQuestionTemplate.WITH_NO_FORM);

		for (final FormQuestion formQuestion : this.formQuestions) {

			this.questionCategoryService.createQuestionCategory(this.getUserContext(),
			        formQuestion.getQuestion().getQuestionsCategory());

			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
		}
	}

	@Test(expected = BusinessException.class)
	public void shouldNotCreateFormWithoutQuestions() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		TestUtil.assertCreation(createdForm);
	}

	@Test(expected = BusinessException.class)
	public void shouldNotUpdateFormWithoutQuestions() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		final Form updateForm = this.formDAO.findById(createdForm.getId());

		final Form updatedForm = this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>());

		TestUtil.assertUpdate(updatedForm);
	}

	@Test
	public void shouldCreateForm() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form,
		        new HashSet<>(this.formQuestions));

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);

		assertFalse(fetchedByFormId.getFormQuestions().isEmpty());

		fetchedByFormId.getFormQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
		});
	}

	@Test
	public void shouldUpdateForm() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form,
		        new HashSet<>(this.formQuestions));

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        this.formQuestion.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), this.formQuestion.getQuestion());
		this.formQuestions.add(this.formQuestion);

		final Form updateForm = this.formDAO.findById(createdForm.getId());

		this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>(this.formQuestions));

		TestUtil.assertUpdate(updateForm);
	}

	@Test
	public void createForm_shouldCreateFormWithSequence() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form,
		        new HashSet<>(this.formQuestions));

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);

		final Set<FormQuestion> formQuestions = fetchedByFormId.getFormQuestions();
		assertFalse(formQuestions.isEmpty());

		formQuestions.stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
			assertTrue(formQuestion.getSequence() != null);
			assertTrue(formQuestion.getSequence() > 0);
		});
	}

	@Test
	public void updateForm_shouldUpdateFormTheQuestionSequenceIfProvided() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form,
		        new HashSet<>(this.formQuestions));

		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        this.formQuestion.getQuestion().getQuestionsCategory());

		this.questionService.createQuestion(this.getUserContext(), this.formQuestion.getQuestion());
		this.formQuestions.add(this.formQuestion);

		Form updateForm = this.formDAO.findById(createdForm.getId());

		updateForm = this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>(this.formQuestions));

		final Set<FormQuestion> formQuestions = updateForm.getFormQuestions();

		assertNotNull(formQuestions);
		assertFalse(formQuestions.isEmpty());
		assertTrue(formQuestions.stream().allMatch(formQuestion -> {
			final Integer questionSequence = formQuestion.getSequence();
			return (questionSequence != null) && (questionSequence.intValue() > 0);
		}));

		TestUtil.assertUpdate(updateForm);
	}
}
