package mz.org.fgh.mentoring.core.formquestion.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.form.FormBuilder;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * Created by Willa aka Baba Imu on 3/9/18.
 */
public class FormQuestionServiceTest extends AbstractSpringTest {
	@Inject
	private FormQuestionQueryService formQuestionQueryService;

	@Inject
	private FormBuilder formBuilder;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.form = this.formBuilder.build();
	}

	@Test
	public void getFormQuestionByFormId_shouldReturnAListOfFormQuestionGivenFormId() throws BusinessException {
		final List<FormQuestion> formQuestions = this.formQuestionQueryService.findFormQuestionByForm(this.form);

		Assert.assertFalse(formQuestions.isEmpty());
	}
}
