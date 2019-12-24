/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.email.MailSenderService;
import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.util.RandomStringFactory;
import mz.co.mozview.frameworks.core.util.StringNormalizer;
import mz.co.mozview.frameworks.core.webservices.adapter.Entry;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.co.mozview.frameworks.core.webservices.service.ClientWS;
import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Service(TutorService.NAME)
public class TutorServiceImpl extends AbstractService implements TutorService {

	private static final int PASSWORD_LENTH = 4;

	@Inject
	private TutorDAO tutorDAO;

	@Inject
	private PropertyValues propertyValues;

	@Inject
	private ClientWS clientWS;

	@Inject
	private Environment environment;

	@Inject
	private MailSenderService mailSenderService;

	@Override
	public Tutor createTutor(final UserContext userContext, final Tutor tutor) throws BusinessException {

		final String code = this.tutorDAO.generateCode("MTT", 8, "0");
		tutor.setCode(code);
		tutor.setName(StringNormalizer.normalizeAndUppCase(tutor.getName()));
		tutor.setSurname(StringNormalizer.normalizeAndUppCase(tutor.getSurname()));

		return this.tutorDAO.create(userContext.getUuid(), tutor);
	}

	@Override
	public Tutor updateTutor(final UserContext userContext, final Tutor tutor) throws BusinessException {

		tutor.setName(StringNormalizer.normalizeAndUppCase(tutor.getName()));
		tutor.setSurname(StringNormalizer.normalizeAndUppCase(tutor.getSurname()));

		this.tutorDAO.update(userContext.getUuid(), tutor);

		return tutor;
	}

	@Override
	public Tutor resetPassword(final UserContext userContext, final Tutor tutor) throws BusinessException {

		if (!tutor.isUser()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.reset.password.for.non.user"));
		}

		userContext.setUuid(tutor.getUuid());
		userContext.setPassword(RandomStringFactory.generate(PASSWORD_LENTH).toLowerCase());
		userContext.addProperty(Entry.RESET, Boolean.TRUE);

		this.clientWS.putJSON(this.environment.getProperty("account.manager.service.update.user"), userContext);

		this.sendResetedPassword(userContext, tutor);

		this.tutorDAO.update(userContext.getUuid(), tutor);

		return tutor;
	}

	private void sendResetedPassword(final UserContext userContext, final Tutor tutor) throws BusinessException {
		this.mailSenderService.subject(this.propertyValues.getPropValues("tutor.email.reset.password"));
		this.mailSenderService.to(tutor.getEmail());
		this.mailSenderService.templateName("/reset-password-template.vm");

		final Map<String, Object> params = new HashMap<>();
		params.put("password", userContext.getPassword());
		params.put("username", tutor.getName().toLowerCase() + "." + tutor.getSurname().toLowerCase());

		this.mailSenderService.send(params);
	}
}
