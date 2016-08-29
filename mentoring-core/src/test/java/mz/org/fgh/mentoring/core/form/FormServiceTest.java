/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
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

	public Set<Question> questions = new HashSet<>();

	private ProgrammaticArea programmaticArea;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);

		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);

		form.setProgrammaticArea(programmaticAreaService.createProgrammaticArea(getUserContext(), programmaticArea));

		List<Question> createdQuestions = (EntityFactory.gimme(Question.class, 2, QuestionTemplate.VALID));

		for (Question question : createdQuestions) {

			this.questionService.createQuestion(this.getUserContext(), question);

			questions.add(question);
		}

		this.formService.createForm(this.getUserContext(), this.form, questions);

	}

	@Test(expected = BusinessException.class)
	public void shouldNotCreateFormWithoutQuestions() throws BusinessException {

		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		TestUtil.assertCreation(this.form);
	}

	@Test
	public void shouldCreateForm() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questions);

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);

		assertFalse(fetchedByFormId.getFromQuestions().isEmpty());

		fetchedByFormId.getFromQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
		});
	}

	@Test
	public void shouldUpdateForm() throws BusinessException {

		final Form updateForm = this.formDAO.findById(this.form.getId());
		
		questions.add(questionService.createQuestion(getUserContext(), EntityFactory.gimme(Question.class, QuestionTemplate.VALID)));

		this.formService.updateForm(this.getUserContext(), updateForm, questions);

		TestUtil.assertUpdate(updateForm);
	}
}
