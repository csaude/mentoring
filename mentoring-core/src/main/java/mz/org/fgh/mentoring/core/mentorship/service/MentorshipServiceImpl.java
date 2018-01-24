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
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.service.SessionService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
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

	@Inject
	private SessionService sessionService;

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private TutoredQueryService tutoredQueryService;

	@Inject
	private CareerQueryService careerQueryService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Inject
	private QuestionQueryService questionQueryService;

	@Override
	public Mentorship createMentorship(final UserContext userContext, final Mentorship mentorship)
	        throws BusinessException {

		if (mentorship.getAnswers().isEmpty()) {
			throw new BusinessException(this.propertyValues.getPropValues("cannot.create.mentoship.with.no.answers"));
		}

		final String code = this.mentorshipDAO.generateCode("MT", 8, "0");
		mentorship.setCode(code);

		this.mentorshipDAO.create(userContext.getUuid(), mentorship);

		for (final Answer answer : mentorship.getAnswers()) {

			final Question question = this.questionQueryService.findQuestionByUuid(userContext,
			        answer.getQuestion().getUuid());

			answer.setQuestion(question);
			answer.setMentorship(mentorship);
			answer.setForm(mentorship.getForm());

			this.answerService.createAnswer(userContext, answer);
		}

		return mentorship;
	}

	@Override
	public Mentorship updateMentorship(final UserContext userContext, final Mentorship mentorship)
	        throws BusinessException {
		return this.mentorshipDAO.update(userContext.getUuid(), mentorship);
	}

	@Override
	public List<Session> synchronizeMentorships(final UserContext userContext, final List<Session> sessions)
	        throws BusinessException {

		for (final Session session : sessions) {

			if (session.getMentorships().isEmpty()) {
				throw new BusinessException(
				        this.propertyValues.getPropValues("cannot.create.session.without.mentorships"));
			}

			this.sessionService.createSession(userContext, session);

			for (final Mentorship mentorship : session.getMentorships()) {

				final Form form = this.formQueryService.findFormByUuid(userContext, mentorship.getForm().getUuid());

				final HealthFacility healthFacility = this.healthFacilityQueryService
				        .findHealthFacilityByUuid(userContext, mentorship.getHealthFacility().getUuid());

				final Tutor tutor = this.tutorQueryService.fetchTutorByUuid(userContext,
				        mentorship.getTutor().getUuid());

				final Tutored tutored = this.getTutored(userContext, mentorship);

				mentorship.setSession(session);
				mentorship.setForm(form);
				mentorship.setHealthFacility(healthFacility);
				mentorship.setTutor(tutor);
				mentorship.setTutored(tutored);

				this.createMentorship(userContext, mentorship);
			}
		}

		return sessions;
	}

	private Tutored getTutored(final UserContext userContext, final Mentorship mentorship) throws BusinessException {

		Tutored tutored = null;
		final Tutored syncedTutored = mentorship.getTutored();

		try {
			tutored = this.tutoredQueryService.findTutoredByUuid(userContext, syncedTutored.getUuid());
		}
		catch (final BusinessException e) {
			final Career career = this.careerQueryService.findCarrerByuuid(userContext,
			        syncedTutored.getCareer().getUuid());

			syncedTutored.setCareer(career);

			tutored = this.tutoredService.createTutored(userContext, mentorship.getTutored());
		}

		return tutored;
	}
}
