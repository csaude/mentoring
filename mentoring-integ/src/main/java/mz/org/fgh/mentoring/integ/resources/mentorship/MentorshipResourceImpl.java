/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.service.CareerQueryService;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormQueryService;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionQueryService;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutor.service.TutorQueryService;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.tutored.service.TutoredQueryService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(MentorshipResource.NAME)
@Path("mentorships")
public class MentorshipResourceImpl extends AbstractResource implements MentorshipResource {

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Inject
	private QuestionQueryService questionQueryService;

	@Inject
	private TutorQueryService tutorQueryService;

	@Inject
	private TutoredQueryService tutoredQueryService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private FormQueryService formQueryService;

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	@Inject
	private CareerQueryService careerQueryService;

	@Override
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
			throws BusinessException {

		final Mentorship mentorship = this.mentorshipService.createMentorship(mentorshipBeanResource.getUserContext(),
				mentorshipBeanResource.getMentorship(), mentorshipBeanResource.getForm(),
				mentorshipBeanResource.getAnswers());

		return JResponse.ok(mentorship).build();
	}

	@Override
	public JResponse<List<Mentorship>> findBySelectedFilter(final String code, final String tutor, final String tutored)
			throws BusinessException {

		final List<Mentorship> mentorships = this.mentorshipQueryService.findBySelectedFilter(this.getUserContetx(),
				code, tutor, tutored);
		return JResponse.ok(mentorships).build();
	}

	@Override
	public JResponse<MentorshipBeanResource> syncronizeMentorships(final MentorshipBeanResource resource)
			throws BusinessException {

		for (final MentorshipHelper mentorshipHelper : resource.getMentorships()) {

			final Mentorship mentorship = mentorshipHelper.getMentorship();

			final Tutor tutor = this.getTutor(resource);

			final Tutored tutored = this.getTutored(resource.getUserContext(), mentorship);

			final Form form = this.getForm(resource.getUserContext(), mentorship);

			final HealthFacility healthFacility = this.getHealthFaclity(resource.getUserContext(), mentorship);

			mentorship.setTutor(tutor);
			mentorship.setTutored(tutored);
			mentorship.setHealthFacility(healthFacility);

			final List<Answer> answers = new ArrayList<>();

			for (final AnswerHelper answerHelper : mentorshipHelper.getAnswers()) {

				final Question question = this.questionQueryService.findQuestionByUuid(this.getUserContetx(),
						answerHelper.getQuestionUuid());
				final Answer answer = question.getQuestionType().getAnswer();
				answer.setQuestion(question);
				answer.setValue(answerHelper.getValue());

				answers.add(answer);
			}

			this.mentorshipService.createMentorship(resource.getUserContext(), mentorship, form, answers);
			resource.addMentorshipUuid(mentorship.getUuid());
		}

		resource.setMentorships(null);
		return JResponse.ok(resource).build();
	}

	private HealthFacility getHealthFaclity(final UserContext userContext, final Mentorship mentorship)
			throws BusinessException {
		return this.healthFacilityQueryService.findHealthFacilityByUuid(userContext,
				mentorship.getHealthFacility().getUuid());
	}

	private Form getForm(final UserContext userContext, final Mentorship mentorship) throws BusinessException {
		return this.formQueryService.findFormByUuid(userContext, mentorship.getForm().getUuid());
	}

	private Tutored getTutored(final UserContext userContext, final Mentorship mentorship) throws BusinessException {

		Tutored tutored = null;
		final Tutored syncedTutored = mentorship.getTutored();

		try {
			tutored = this.tutoredQueryService.findTutoredByUuid(userContext, syncedTutored.getUuid());
		} catch (final BusinessException e) {
			final Career career = this.careerQueryService.findCarrerByuuid(userContext,
					syncedTutored.getCareer().getUuid());

			syncedTutored.setCareer(career);

			tutored = this.tutoredService.createTutored(userContext, mentorship.getTutored());
		}

		return tutored;
	}

	private Tutor getTutor(final MentorshipBeanResource mentorshipBeanResource) throws BusinessException {

		final UserContext userContext = mentorshipBeanResource.getUserContext();

		final List<Tutor> tutors = this.tutorQueryService.findTutorsBySelectedFilter(userContext, null, null, null,
				null, userContext.getPhoneNumber());

		return tutors.get(0);
	}
}
