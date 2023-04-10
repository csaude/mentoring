/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.form.FormBuilder;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;

/**
 * @author Stélio Moiane
 *
 */
public class QuestionQueryServiceTest extends AbstractSpringTest {

	@Inject
	private QuestionQueryService questionQueryService;

	@Inject
	private FormBuilder formBuilder;

	private Form form;

	@Override
	public void setUp() throws BusinessException {
		this.form = this.formBuilder.build();
	}

	@Test
	public void shouldFindQuestionBySelectedFilter() {

		final String code = null;
		final String question = null;
		final QuestionType questionType = null;

		final List<Question> questions = this.questionQueryService.findQuestionsBySelectedFilter(this.getUserContext(),
				code, question, questionType);

		Assert.assertFalse(questions.isEmpty());
	}

	@Test
	public void shouldFindByForm() {
		final List<Question> questions = this.questionQueryService.findByFormCode(this.form.getCode());
		Assert.assertFalse(questions.isEmpty());
	}
}
