/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
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

	private Question newQuestion;

	@Override
	public void setUp() throws BusinessException {

		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);

		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);

		form.setProgrammaticArea(programmaticAreaService.createProgrammaticArea(getUserContext(), programmaticArea));
		newQuestion = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);

		List<Question> createdQuestions = (EntityFactory.gimme(Question.class, 10, QuestionTemplate.VALID));

		for (Question question : createdQuestions) {

			this.questionService.createQuestion(this.getUserContext(), question);

			questions.add(question);
		}

	}

	@Test(expected = BusinessException.class)
	public void shouldNotCreateFormWithoutQuestions() throws BusinessException {

		Form createdForm = this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		TestUtil.assertCreation(createdForm);
	}

	@Test(expected = BusinessException.class)
	public void shouldNotUpdateFormWithoutQuestions() throws BusinessException {

		Form createdForm = this.formService.createForm(this.getUserContext(), this.form, new HashSet<>());

		final Form updateForm = this.formDAO.findById(createdForm.getId());

		Form updatedForm = this.formService.updateForm(this.getUserContext(), updateForm, new HashSet<>());

		TestUtil.assertUpdate(updatedForm);
	}

	@Test
	public void shouldCreateForm() throws BusinessException {

		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questions);

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);

		assertFalse(fetchedByFormId.getFormQuestions().isEmpty());

		fetchedByFormId.getFormQuestions().stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
		});
	}

	@Test
	public void shouldUpdateForm() throws BusinessException {

		Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questions);
		
		questions.add(this.questionService.createQuestion(this.getUserContext(), newQuestion));

		final Form updateForm = this.formDAO.findById(createdForm.getId());

		this.formService.updateForm(this.getUserContext(), updateForm, questions);

		TestUtil.assertUpdate(updateForm);
	}

	@Test
	public void createForm_shouldCreateFormWithSequence() throws BusinessException {
		// Create a question sequence Map.
		Map<Integer, Question>  questionsSequence = createAMapOfSequenceToQuestion();
		final Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questionsSequence);

		final Form fetchedByFormId = this.formDAO.fetchByFormId(createdForm.getId());

		TestUtil.assertCreation(createdForm);

		Set<FormQuestion> formQuestions = fetchedByFormId.getFormQuestions();
		assertFalse(formQuestions.isEmpty());

		formQuestions.stream().forEach(formQuestion -> {
			assertEquals(formQuestion.getForm().getCode(), this.form.getCode());
			assertTrue(formQuestion.getSequence() != null);
			assertTrue(formQuestion.getSequence() > 0);
		});
	}

	@Test
	public void updateForm_shouldUpdateFormTheQuestionSequenceIfProvided() throws BusinessException {

		Form createdForm = this.formService.createForm(this.getUserContext(), this.form, questions);

		questions.add(this.questionService.createQuestion(this.getUserContext(), newQuestion));

		Form updateForm = this.formDAO.findById(createdForm.getId());

		// Create a question sequence Map.
		Map<Integer, Question>  questionsSequence = createAMapOfSequenceToQuestion();

		updateForm = this.formService.updateForm(this.getUserContext(), updateForm, questionsSequence);

		Set<FormQuestion> formQuestions = updateForm.getFormQuestions();

		assertNotNull(formQuestions);
		assertFalse(formQuestions.isEmpty());
		assertTrue(formQuestions.stream().allMatch(formQuestion -> {
			Integer questionSequence = formQuestion.getSequence();
			return questionSequence != null && questionSequence.intValue() > 0;
		}));
		TestUtil.assertUpdate(updateForm);
	}

	private Map<Integer, Question> createAMapOfSequenceToQuestion() {
		// Create a question sequence Map.
		Map<Integer, Question>  questionsSequence = new HashMap<>();
		Iterator<Question> iterator = questions.iterator();
		Integer sequence = 1;
		while(iterator.hasNext()) {
			questionsSequence.put(sequence++, iterator.next());
		}

		return questionsSequence;
	}
}
