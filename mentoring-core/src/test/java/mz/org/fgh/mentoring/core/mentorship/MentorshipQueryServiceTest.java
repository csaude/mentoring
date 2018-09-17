/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

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
import mz.org.fgh.mentoring.core.fixturefactory.FormQuestionTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.form.service.FormService;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.location.service.CabinetService;
import mz.org.fgh.mentoring.core.location.service.DistrictService;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityService;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService;
import mz.org.fgh.mentoring.core.question.service.QuestionService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

/**
 * @author Stélio Moiane
 *
 */
public class MentorshipQueryServiceTest extends AbstractSpringTest {

	@Inject
	private MentorshipService mentorshipService;

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

	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Inject
	private CabinetService cabinetService;

	private Mentorship mentorship;

	private FormQuestion formQuestion;

	private Form form;

	@Override
	public void setUp() throws BusinessException {

		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), this.mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), this.mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), this.mentorship.getTutored());
		this.cabinetService.createCabinet(this.getUserContext(), this.mentorship.getCabinet());

		this.formQuestion = EntityFactory.gimme(FormQuestion.class, FormQuestionTemplate.VALID);
		this.questionService.createQuestion(this.getUserContext(), this.formQuestion.getQuestion());

		final Set<FormQuestion> formQuestions = new HashSet<>();
		formQuestions.add(this.formQuestion);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        this.mentorship.getForm().getProgrammaticArea());

		this.form = this.mentorship.getForm();
		this.formService.createForm(this.getUserContext(), this.form, formQuestions);

		this.districtService.createDistrict(this.getUserContext(), this.mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), this.mentorship.getHealthFacility());

		final Answer answer = new TextAnswer();
		answer.setQuestion(this.formQuestion.getQuestion());
		answer.setValue("COMPETENTE");

		this.mentorship.addAnswer(answer);
		this.mentorship.setForm(this.form);

		this.mentorship.setIterationType(IterationType.PATIENT);
		this.mentorship.setIterationNumber(1);

		this.mentorshipService.createMentorship(this.getUserContext(), this.mentorship);
	}

	@Test
	public void shouldFetchMentorshipsBySeletedFilter() {

		final List<Mentorship> mentorships = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
		        this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.form.getName(), this.mentorship.getHealthFacility().getHealthFacility(), null, null);

		assertFalse(mentorships.isEmpty());

		for (final Mentorship mentorship : mentorships) {
			assertNotNull(mentorship.getTutor());
			assertNotNull(mentorship.getTutored());
			assertNotNull(mentorship.getForm());
			assertNotNull(mentorship.getHealthFacility());
		}
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByIterationType() {
		List<Mentorship> results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
		        this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.form.getName(), this.mentorship.getHealthFacility().getHealthFacility(), "patient", null);

		assertNotNull(results);
		assertTrue(results.size() > 0);
		assertEquals(IterationType.PATIENT, results.get(0).getIterationType());

		results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), this.mentorship.getCode(),
		        this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(), this.form.getName(),
		        this.mentorship.getHealthFacility().getHealthFacility(), "file", null);

		assertTrue((results == null) || (results.size() == 0));
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByIterationNumber() {
		final List<Mentorship> results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
		        this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.form.getName(), this.mentorship.getHealthFacility().getHealthFacility(), null, 1);

		assertNotNull(results);
		assertTrue(results.size() > 0);
		assertEquals(Integer.valueOf(1), results.get(0).getIterationNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void fetchBySelectedFilterShouldThrowIfUnknownIterationTypeIsPassed() {
		this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), this.mentorship.getCode(),
		        this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(), this.form.getName(),
		        this.mentorship.getHealthFacility().getHealthFacility(), "unknown type", 1);
	}
}
