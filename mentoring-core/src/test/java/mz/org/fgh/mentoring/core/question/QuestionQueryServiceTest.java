/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question;

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
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;

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

	@Inject
	private QuestionCategoryService questionCategoryService;

	private Set<FormQuestion> formQuestions;

	private ProgrammaticArea programmaticArea;

	@Inject
	private FormService formService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.formQuestions = new HashSet<>(
		        EntityFactory.gimme(FormQuestion.class, 5, FormQuestionTemplate.WITH_NO_FORM));

		this.programmaticArea = EntityFactory.gimme(ProgrammaticArea.class, ProgrammaticAreaTemplate.VALID);
		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);

		for (final FormQuestion formQuestion : this.formQuestions) {

			this.questionCategoryService.createQuestionCategory(this.getUserContext(),
			        formQuestion.getQuestion().getQuestionsCategory());

			this.questionService.createQuestion(this.getUserContext(), formQuestion.getQuestion());
		}

		this.form.setProgrammaticArea(this.programmaticArea);
		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(), this.programmaticArea);
		this.formService.createForm(this.getUserContext(), this.form, this.formQuestions);

	}

	@Test
	public void shouldFindQuestionBySelectedFilter() {

		final String code = null;
		final String question = null;
		final QuestionType questionType = null;

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContext(),
		        code, question, questionType);

		assertFalse(questions.isEmpty());
	}

	@Test
	public void shouldFindByForm() {
		final List<Question> questions = this.questionQueryService.findByFormCode(this.form.getCode());
		assertFalse(questions.isEmpty());
	}
}
