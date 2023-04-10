/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

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
	private FormBuilder formBuilder;

	private Form form;

	@Override
	public void setUp() throws BusinessException {
		this.form = this.formBuilder.build();
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

		final Form fetchedByFormId = this.formDAO.fetchByFormId(this.form.getId());

		TestUtil.assertCreation(this.form);

		Assert.assertFalse(fetchedByFormId.getFormQuestions().isEmpty());

		fetchedByFormId.getFormQuestions().stream().forEach(formQuestion -> {
			Assert.assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
		});
	}

	@Test
	public void shouldUpdateForm() throws BusinessException {

		final Form updateForm = this.formDAO.findById(this.form.getId());

		this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>(this.form.getFormQuestions()));

		TestUtil.assertUpdate(updateForm);
	}

	@Test
	public void createForm_shouldCreateFormWithSequence() throws BusinessException {

		final Form fetchedByFormId = this.formDAO.fetchByFormId(this.form.getId());

		TestUtil.assertCreation(this.form);

		final Set<FormQuestion> formQuestions = fetchedByFormId.getFormQuestions();
		Assert.assertFalse(formQuestions.isEmpty());

		formQuestions.stream().forEach(formQuestion -> {
			Assert.assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
			Assert.assertTrue(formQuestion.getSequence() != null);
			Assert.assertTrue(formQuestion.getSequence() > 0);
		});
	}

	@Test
	public void updateForm_shouldUpdateFormTheQuestionSequenceIfProvided() throws BusinessException {

		Form updateForm = this.formDAO.findById(this.form.getId());

		updateForm = this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>(this.form.getFormQuestions()));

		final Set<FormQuestion> formQuestions = updateForm.getFormQuestions();

		Assert.assertNotNull(formQuestions);
		Assert.assertFalse(formQuestions.isEmpty());
		Assert.assertTrue(formQuestions.stream().allMatch(formQuestion -> {
			final Integer questionSequence = formQuestion.getSequence();
			return questionSequence != null && questionSequence.intValue() > 0;
		}));

		TestUtil.assertUpdate(updateForm);
	}
}
