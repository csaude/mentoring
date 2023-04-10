/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTemplate;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipQueryService;

/**
 * @author Stélio Moiane
 *
 */
public class MentorshipQueryServiceTest extends AbstractSpringTest {

	@Inject
	private MentorshipQueryService mentorshipQueryService;

	@Inject
	private MentorshipBuilder mentorshipBuilder;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.mentorship = this.mentorshipBuilder.mentorship().build();
	}

	@Test
	public void shouldFetchMentorshipsBySeletedFilter() {

		final List<Mentorship> mentorships = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
				this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
				this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), null,
				null, LifeCycleStatus.ACTIVE.toString(), null, null);

		Assert.assertFalse(mentorships.isEmpty());

		for (final Mentorship mentorship : mentorships) {
			Assert.assertNotNull(mentorship.getTutor());
			Assert.assertNotNull(mentorship.getTutored());
			Assert.assertNotNull(mentorship.getForm());
			Assert.assertNotNull(mentorship.getHealthFacility());
		}
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByIterationType() {
		List<Mentorship> results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
				this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),

				this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), "patient",
				null, LifeCycleStatus.ACTIVE.toString(), null, null);

		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() > 0);
		Assert.assertEquals(IterationType.PATIENT, results.get(0).getIterationType());

		results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), this.mentorship.getCode(),
				this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
				this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), "file",
				null, LifeCycleStatus.ACTIVE.toString(), null, null);

		Assert.assertTrue(results == null || results.size() == 0);
	}

	@Test
	public void fetchBySelectedFilterShouldSearchByIterationNumber() {

		final List<Mentorship> results = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(),
				this.mentorship.getCode(), this.mentorship.getTutor().getName(), this.mentorship.getTutored().getName(),
				this.mentorship.getForm().getName(), this.mentorship.getHealthFacility().getHealthFacility(), null, 1,
				LifeCycleStatus.ACTIVE.toString(), null, null);

		Assert.assertNotNull(results);
		Assert.assertTrue(results.size() > 0);
		Assert.assertEquals(Integer.valueOf(1), results.get(0).getIterationNumber());
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

		this.mentorshipBuilder.mentorship().withPerformedDate(MentorshipTemplate.DATE_MAY_12_2018).build();
		this.mentorshipBuilder.mentorship().withPerformedDate(MentorshipTemplate.DATE_MAY_20_2018).build();

		List<Mentorship> mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null,
				null, null, null, null, null, null, LifeCycleStatus.ACTIVE.toString(),
				null, MentorshipTemplate.DATE_MAY_12_2018);

		Assert.assertNotNull(mentorshipList);
		Assert.assertTrue(mentorshipList.size() >= 1);
		Assert.assertTrue(mentorshipList.stream()
				.anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_12_2018)));

		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
				null, null, null, null, LifeCycleStatus.ACTIVE.toString(), MentorshipTemplate.DATE_MAY_12_2018,
				MentorshipTemplate.DATE_MAY_20_2018);

		Assert.assertNotNull(mentorshipList);
		Assert.assertEquals(2, mentorshipList.size());
		Assert.assertTrue(mentorshipList.stream()
				.anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_12_2018)));
		Assert.assertTrue(mentorshipList.stream()
				.anyMatch(mship -> mship.getPerformedDate().equals(MentorshipTemplate.DATE_MAY_20_2018)));

		// end date earlier before anything is performed
		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
				null, null, null, null, LifeCycleStatus.ACTIVE.toString(), null, LocalDate.of(2018, 1, 1));

		Assert.assertTrue(mentorshipList.isEmpty());

		final LocalDate janNextYear = LocalDate.of(LocalDate.now().getYear() + 1, 1, 1); // January
		// next
		// year
		// -
		// future
		mentorshipList = this.mentorshipQueryService.fetchBySelectedFilter(this.getUserContext(), null, null, null,
				null, null, null, null, LifeCycleStatus.ACTIVE.toString(), janNextYear, LocalDate.of(2018, 1, 1));

		Assert.assertTrue(mentorshipList.isEmpty());
	}
}