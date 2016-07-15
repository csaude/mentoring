/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.question;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.form.question.model.FormQuestion;
import mz.org.fgh.mentoring.core.form.question.service.FormQuestionService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

import org.junit.Test;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class FormQuestionServiceTest extends AbstractSpringTest {

	@Inject
	private FormService formService;

	private FormQuestion formQuestion;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormQuestionService formQuestionService;

	@Inject
	private SectorService sectorService;

	@Override
	public void setUp() throws BusinessException {

		this.formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.VALID);

	}

	@Test
	public void shouldCreateFormQuestion() throws BusinessException {

		sectorService.createSector(getUserContext(), formQuestion.getForm().getSector());

		formService.createForm(getUserContext(), formQuestion.getForm());

		formQuestion.getForm().getQuestions()
				.add(questionService.createQuestion(getUserContext(), formQuestion.getQuestion()));

		formQuestionService.createFormQuestion(getUserContext(), formQuestion);

		TestUtil.assertCreation(this.formQuestion);

	}

}
