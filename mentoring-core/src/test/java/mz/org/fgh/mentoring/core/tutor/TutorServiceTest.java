/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.email.MailSenderService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.TutorTemplate;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;

/**
 * @author Stélio Moiane
 *
 *
 */
public class TutorServiceTest extends AbstractSpringTest {

	@Inject
	private TutorService tutorService;

	@Inject
	private TutorDAO tutorDAO;

	private Tutor tutor;

	@Inject
	private CareerService carrerService;

	@Inject
	private MailSenderService mailSenderService;

	@Inject
	private PropertyValues propertyValues;

	@Override
	public void setUp() throws BusinessException {

		this.tutor = EntityFactory.gimme(Tutor.class, TutorTemplate.VALID);
		this.tutor.setEmail("stelio.moiane@fgh.org.mz");

		this.carrerService.createCareer(this.getUserContext(), this.tutor.getCareer());

	}

	@Test
	public void shouldCreateTutor() throws BusinessException {

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

		TestUtil.assertCreation(this.tutor);
	}

	@Test
	public void shouldUpdateTutor() throws BusinessException {

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

		final Tutor tutorUpdate = this.tutorDAO.findById(this.tutor.getId());

		this.tutorService.updateTutor(this.getUserContext(), tutorUpdate);

		TestUtil.assertUpdate(tutorUpdate);

	}

	@Test
	public void shouldSendTutorEmail() throws BusinessException {

		this.tutorService.createTutor(this.getUserContext(), this.tutor);

		this.mailSenderService.subject(this.propertyValues.getPropValues("tutor.email.subject"));
		this.tutor.setEmail("stelio.moiane@fgh.org.mz");
		this.mailSenderService.to(this.tutor.getEmail());
		this.mailSenderService.templateName("/create-user-template.vm");

		final Map<String, Object> params = new HashMap<>();
		params.put("tutor", this.tutor);
		params.put("password", "123456");

		this.mailSenderService.send(params);
	}

}
