/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTemplate;
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.sector.service.SectorService;

import org.junit.Test;

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
	private SectorService sectorService;

	private Form form;

	@Override
	public void setUp() throws BusinessException {
		this.form = EntityFactory.gimme(Form.class, FormTemplate.VALID);
	}

	@Test
	public void shouldCreateForm() throws BusinessException {

		this.sectorService.createSector(this.getUserContext(), form.getSector());

		formService.createForm(getUserContext(), form);

		TestUtil.assertCreation(this.form);
	}

	@Test
	public void shouldUpdateForm() throws BusinessException {

		this.sectorService.createSector(this.getUserContext(), form.getSector());

		formService.createForm(getUserContext(), form);

		final Form updateForm = this.formDAO.findById(this.form.getId());

		this.formService.updateForm(this.getUserContext(), updateForm);

		TestUtil.assertUpdate(updateForm);

	}

}
