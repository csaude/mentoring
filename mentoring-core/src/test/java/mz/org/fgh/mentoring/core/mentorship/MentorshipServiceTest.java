/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import java.time.LocalDate;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTamplate;
import mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.tutor.service.TutorService;
import mz.org.fgh.mentoring.core.tutored.service.TutoredService;

import org.junit.Test;

/**
 * @author Eusebio Jose Maposse
 *
 *
 */
public class MentorshipServiceTest extends AbstractSpringTest {

	private Mentorship mentorship;

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private TutorService tutorService;

	@Inject
	private TutoredService tutoredService;

	@Inject
	private MentorshipDAO mentorshipDAO;

	@Override
	public void setUp() throws BusinessException {

		this.mentorship = EntityFactory.gimme(Mentorship.class,
				MentorshipTamplate.VALID);

	}

	@Test
	public void shouldCreateMentorship() throws BusinessException {

		tutorService.createTutor(getUserContext(), mentorship.getTutor());
		tutoredService.createTutored(getUserContext(), mentorship.getTutored());
		this.mentorshipService.createMentorship(this.getUserContext(),
				mentorship);

		TestUtil.assertCreation(this.mentorship);
	}

	@Test
	public void shouldUpdateMentorship() throws BusinessException {

		tutorService.createTutor(getUserContext(), mentorship.getTutor());
		tutoredService.createTutored(getUserContext(), mentorship.getTutored());
		this.mentorshipService.createMentorship(this.getUserContext(),
				mentorship);

		final Mentorship mentorshipUpdate = this.mentorshipDAO
				.findById(this.mentorship.getId());

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		mentorshipUpdate.setStartDate(startDate);
		mentorshipUpdate.setEndDate(endDate);

		this.mentorshipService.updateMentorship(getUserContext(),
				mentorshipUpdate);

		TestUtil.assertUpdate(mentorshipUpdate);

	}

}
