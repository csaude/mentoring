/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.mentorship;

import javax.inject.Inject;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.co.mozview.frameworks.core.fixtureFactory.util.TestUtil;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.MentorshipTamplate;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;

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

	@Override
	public void setUp() throws BusinessException {

		this.mentorship = EntityFactory.gimme(Mentorship.class,
				MentorshipTamplate.VALID);

	}

	@Test
	public void shouldCreateMentorship() throws BusinessException {

		this.mentorshipService.createMentorship(this.getUserContext(),
				mentorship);

		TestUtil.assertCreation(this.mentorship);
	}
	

}
