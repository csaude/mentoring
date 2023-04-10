/**
 *
 */
package mz.org.fgh.mentoring.core.form;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTargetTemplate;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetServiceTest extends AbstractSpringTest {

	@Inject
	private FormTargetService formTargetService;

	@Inject
	private CareerService careerService;

	@Inject
	private FormBuilder formBuilder;

	private FormTarget formTarget;

	@Override
	public void setUp() throws BusinessException {

		this.formTarget = EntityFactory.gimme(FormTarget.class, FormTargetTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), this.formTarget.getCareer());

		this.formTarget.setForm(this.formBuilder.build());
	}

	@Test
	public void shouldCreateFormTarget() {

		this.formTargetService.createFormTarget(this.getUserContext(), this.formTarget);

		TestUtil.assertCreation(this.formTarget);
	}
}
