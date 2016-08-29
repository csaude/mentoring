/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.PropertyValues;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Service(MentorshipService.NAME)
public class MentorshipServiceImpl extends AbstractService implements MentorshipService {

	@Inject
	private MentorshipDAO mentorshipDAO;

	@Inject
	private PropertyValues propertyValues;

	@Inject
	private AnswerService answerService;

	@Override
	public Mentorship createMentorship(final UserContext userContext, final Mentorship mentorship, final Form form,
			final List<Answer> answers) throws BusinessException {

		if (answers.isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.mentoship.with.no.answers"));
		}

		// TODO generate code just a sample
		final String code = this.mentorshipDAO.generateCode("MT", 8, "0");
		mentorship.setCode(code);

		mentorship.setForm(form);
		this.mentorshipDAO.create(userContext.getId(), mentorship);

		for (final Answer answer : answers) {
			answer.setMentorship(mentorship);
			answer.setForm(form);
			this.answerService.createAnswer(userContext, answer);
		}

		return mentorship;
	}

	@Override
	public Mentorship updateMentorship(final UserContext userContext, final Mentorship mentorship)
			throws BusinessException {

		return this.mentorshipDAO.update(userContext.getId(), mentorship);
	}
}
