/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.quetion;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormQuetionTemplate;
import mz.org.fgh.mentoring.core.form.quetion.dao.FormQuetionDAO;
import mz.org.fgh.mentoring.core.form.quetion.model.FormQuetion;
import mz.org.fgh.mentoring.core.form.quetion.service.FormQuetionService;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

import org.junit.Test;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class FormQuetionServiceTest extends AbstractSpringTest {

	private FormQuetion formQuetion;

	@Inject
	private FormService formService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormQuetionService formQuetionService;

	@Inject
	private FormQuetionDAO formQuetionDAO;

	@Inject
	private SectorService sectorService;

	@Override
	public void setUp() throws BusinessException {

		this.formQuetion = EntityFactory.gimme(FormQuetion.class, FormQuetionTemplate.VALID);
	}

	@Test
	public void shouldCreateFormQuetion() throws BusinessException {

		sectorService.createSector(getUserContext(), formQuetion.getForm().getSector());

		questionService.createQuestion(getUserContext(), formQuetion.getQuestion());

		formService.createForm(getUserContext(), formQuetion.getForm());

		formQuetionService.createFormQuetion(getUserContext(), formQuetion);

		TestUtil.assertCreation(this.formQuetion);
	}

	@Test
	public void shouldUpdateFormQuetion() throws BusinessException {

		sectorService.createSector(getUserContext(), formQuetion.getForm().getSector());

		questionService.createQuestion(getUserContext(), formQuetion.getQuestion());
		
		formService.createForm(getUserContext(), formQuetion.getForm());

		formQuetionService.createFormQuetion(getUserContext(), formQuetion);

		final FormQuetion updateFormQuetion = this.formQuetionDAO.findById(this.formQuetion.getId());

		this.formQuetionService.updateFormQuetion(getUserContext(), updateFormQuetion);

		TestUtil.assertUpdate(updateFormQuetion);

	}

}
