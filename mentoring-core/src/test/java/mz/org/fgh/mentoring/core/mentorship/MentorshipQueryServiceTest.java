/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.model.TextAnswer;
import mz.org.fgh.mentoring.core.career.service.CareerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.fixturefactory.QuestionTemplate;
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
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.service.QuestionCategoryService;
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

	@Inject
	private QuestionCategoryService questionCategoryService;

	private Mentorship mentorship;

	private Question question;

	@Override
	public void setUp() throws BusinessException {
		this.mentorship = EntityFactory.gimme(Mentorship.class, MentorshipTemplate.VALID);

		this.question = EntityFactory.gimme(Question.class, QuestionTemplate.VALID);
		this.questionCategoryService.createQuestionCategory(this.getUserContext(),
		        this.question.getQuestionsCategory());
		this.questionService.createQuestion(this.getUserContext(), this.question);
		this.prepareAndCreateMentorship(this.mentorship);
	}

	@Test
	public void shouldFetchMentorshipsBySeletedFilter() {

		final List<Mentorship> mentorships = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
		        this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), null,
		        null, LifeCycleStatus.ACTIVE.toString(), null, null);

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

		        this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), "patient",
		        null, LifeCycleStatus.ACTIVE.toString(), null, null);

		assertNotNull(results);
		assertTrue(results.size() > 0);
		assertEquals(IterationType.PATIENT, results.get(0).getIterationType());

		results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), this.mentorship.getCode(),
		        this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), "file",
		        null, LifeCycleStatus.ACTIVE.toString(), null, null);

		assertTrue((results == null) || (results.size() == 0));
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByIterationNumber() {
		final List<Mentorship> results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
		        this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), null, 1,
		        LifeCycleStatus.ACTIVE.toString(), null, null);

		assertNotNull(results);
		assertTrue(results.size() > 0);
		assertEquals(Integer.valueOf(1), results.get(0).getIterationNumber());
	}

	@Test(expected = IllegalArgumentException.class)
	public void fetchBySelectedFilterShouldThrowIfUnknownIterationTypeIsPassed() {
		this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), this.mentorship.getCode(),

		        this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
		        this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(),
		        "unknown type", 1, LifeCycleStatus.ACTIVE.toString(), null, null);
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByPerformedDateRange() throws BusinessException {
		this.prepareAndCreateMentorship(
		        EntityFactory.gimme(Mentorship.class, MentorshipTemplate.DATE_PERFORMED_MAY_12_2018));
		this.prepareAndCreateMentorship(
		        EntityFactory.gimme(Mentorship.class, MentorshipTemplate.DATE_PERFORMED_MAY_20_2018));

		List<Mentorship> mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null,
		        null, null, null, null, null, null, LifeCycleStatus.ACTIVE.toString(),
		        MentorshipTemplate.DATE_MAY_12_2018, null);

		assertNotNull(mentorshipList);
		assertTrue(mentorshipList.size() >= 1);
		assertTrue(mentorshipList.stream()
		        .anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_12_2018)));

		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
		        null, null, null, null, LifeCycleStatus.ACTIVE.toString(), MentorshipTemplate.DATE_MAY_12_2018,
		        MentorshipTemplate.DATE_MAY_20_2018);

		assertNotNull(mentorshipList);
		assertEquals(2, mentorshipList.size());
		assertTrue(mentorshipList.stream()
		        .anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_12_2018)));
		assertTrue(mentorshipList.stream()
		        .anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_20_2018)));

		// end date earlier before anything is performed
		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
		        null, null, null, null, LifeCycleStatus.ACTIVE.toString(), null, LocalDate.of(2018, 1, 1));

		assertTrue(mentorshipList.isEmpty());

		final LocalDate janNextYear = LocalDate.of(LocalDate.now().getYear() + 1, 1, 1); // January
		                                                                                 // next
		                                                                                 // year
		                                                                                 // -
		                                                                                 // future
		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
		        null, null, null, null, LifeCycleStatus.ACTIVE.toString(), janNextYear, LocalDate.of(2018, 1, 1));

		assertTrue(mentorshipList.isEmpty());
	}

	private void prepareAndCreateMentorship(@NotNull final Mentorship mentorship) throws BusinessException {
		this.careerService.createCareer(this.getUserContext(), mentorship.getTutor().getCareer());
		this.careerService.createCareer(this.getUserContext(), mentorship.getTutored().getCareer());
		this.tutorService.createTutor(this.getUserContext(), mentorship.getTutor());
		this.tutoredService.createTutored(this.getUserContext(), mentorship.getTutored());
		this.cabinetService.createCabinet(this.getUserContext(), mentorship.getCabinet());

		final Set<FormQuestion> questions = new HashSet<>();
		final FormQuestion formQuestion = new FormQuestion();
		formQuestion.setQuestion(this.question);
		formQuestion.setApplicable(Boolean.TRUE);

		questions.add(formQuestion);

		this.programmaticAreaService.createProgrammaticArea(this.getUserContext(),
		        mentorship.getForm().getProgrammaticArea());

		this.formService.createForm(this.getUserContext(), mentorship.getForm(), questions);

		this.districtService.createDistrict(this.getUserContext(), mentorship.getHealthFacility().getDistrict());
		this.heathFacilityService.createHealthFacility(this.getUserContext(), mentorship.getHealthFacility());

		final Answer answer = new TextAnswer();
		answer.setQuestion(this.question);
		answer.setValue("COMPETENTE");

		mentorship.addAnswer(answer);

		mentorship.setIterationType(IterationType.PATIENT);
		mentorship.setIterationNumber(1);

		this.mentorshipService.createMentorship(this.getUserContext(), mentorship);
	}
}