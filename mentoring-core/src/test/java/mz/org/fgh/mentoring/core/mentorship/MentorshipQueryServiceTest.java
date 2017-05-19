/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTamplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Eusebio Jose Maposse
 *
 */
public class MentorshipQueryServiceTest extends AbstractSpringTest {

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private QuestionService questionService;

	@Inject
	private FormService formService;

	@Inject
	private CareerService careerService;

	@Inject
	private ProgrammaticAreaService programmaticAreaService;

	@Inject
	private HealthFacilityService heathFacilityService;

	@Inject
	private DistrictService districtService;

	private Mentorship mentorship;

	private Question question;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTamplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());

		this.question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), this.question);

		final Set<Question> questions = new HashSet<>();
		questions.add(this.question);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
				this.mentorship.getForm().getProgrammaticArea());

		this.form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), this.form, questions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		final Answer answer = new TextAnswer();

		answer.setQuestion(this.question);
		answer.setValue("COMPETENTE");

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship, this.form,
				Arrays.asList(answer));

	}

	@Test
	public void countMentorshipByHealthFacility() {

		List<Mentorship> mentorships = mentorshipQueryService
				.countMentorshipByHealthFacility(mentorship.getHealthFacility());

		assertFalse(mentorships.isEmpty());
	}

}
