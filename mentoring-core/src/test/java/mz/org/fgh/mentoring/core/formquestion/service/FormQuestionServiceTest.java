package mz.org.fgh.mentoring.core.formquestion.service;

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
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.ProgrammaticAreaTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

/**
 * Created by Willa aka Baba Imu on 3/9/18.
 */
public class FormQuestionServiceTest extends AbstractSpringTest {
	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private FormQuestionQueryService formQuestionQueryService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private QuestionCategoryService questionCategoryService;

	private final Set<Question> questions = new HashSet<>();
	private Form form;

	@Override
	public void setUp() throws BusinessException {
		final ProgrammaticArea programmaticArea = EntityFactory.gimme(ProgrammaticArea.class,
		        ProgrammaticAreaTemplate.VALID);
		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
		this.form.setProgrammaticArea(
		        this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), programmaticArea));

		final List<FormQuestion> formQuestions = EntityFactory.gimme(FormQuestion.class, 10,
		        FormQuestionTemplate.WITH_NO_FORM);

		for (final FormQuestion formQuestion : formQuestions) {
			this.questionCategoryService.createQuestionCategory(this.getUserContext(),
			        formQuestion.getQuestion().getQuestionsCategory());
			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
			this.questions.add(formQuestion.getQuestion());
		}

		this.formService.createForm(this.getUserContext(), this.form, new HashSet<>(formQuestions));
	}

	@Test
	public void getFormQuestionByFormId_shouldReturnAListOfFormQuestionGivenFormId() throws BusinessException {
		final List<FormQuestion> formQuestions = this.formQuestionQueryService.findFormQuestionByForm(this.form);

		assertFalse(formQuestions.isEmpty());
		assertEquals(this.questions.size(), formQuestions.size());
	}
}
