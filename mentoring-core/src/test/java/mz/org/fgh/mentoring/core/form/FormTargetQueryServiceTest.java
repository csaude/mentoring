/**
 *
 */
package mz.org.fgh.mentoring.core.form;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.FormTargetTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.form.service.FormTargetQueryService;
import mz.org.fgh.mentoring.core.form.service.FormTargetService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 */
public class FormTargetQueryServiceTest extends AbstractSpringTest {

	@Inject
	private CareerService careerService;

	@Inject
	private FormTargetService formTargetService;

	@Inject
	private TutorService tutorService;

	@Inject
	private FormTargetQueryService formTargetQueryService;

	@Inject
	private FormBuilder formBuilder;

	private Tutor tutor;

	@Override
	public void setUp() throws BusinessException {

		final FormTarget formTarget = EntityFactory.gimme(FormTarget.class, FormTargetTemplate.VALID);

		this.careerService.createCareer(this.getUserContext(), formTarget.getCareer());

		final Form form = this.formBuilder.build();

		formTarget.setForm(form);
		this.formTargetService.createFormTarget(this.getUserContext(), formTarget);

		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);
		this.tutor.setEmail("stelio.moiane@fgh.org.mz");
		this.tutor.setCareer(formTarget.getCareer());

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

	}

	@Test
	public void shouldFindFormTargetByTutor() throws BusinessException {
		final List<FormTarget> formTargets = this.formTargetQueryService.findFormTargetByTutor(this.tutor);
		Assert.assertFalse(formTargets.isEmpty());

		for (final FormTarget formTarget : formTargets) {
			Assert.assertNotNull(formTarget.getCareer().getUuid());
			Assert.assertNotNull(formTarget.getForm().getUuid());
		}
	}
}
