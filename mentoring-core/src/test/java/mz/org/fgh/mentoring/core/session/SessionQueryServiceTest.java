/**
 *
 */
package mz.org.fgh.mentoring.core.session;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.mentorship.MentorshipBuilder;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.core.session.service.SessionService;

/**
 * @author Stélio Moiane
 *
 */
public class SessionQueryServiceTest extends AbstractSpringTest {

	@Inject
	private SessionService sessionService;

	@Inject
	private SessionQueryService sessionQueryService;

	@Inject
	private MentorshipBuilder mentorshipBuilder;

	private Session session;

	private Mentorship mentorship;

	@Override
	public void setUp() throws BusinessException {
		this.createSession();
		this.createMentorship();
	}

	private void createMentorship() throws BusinessException {
		this.mentorship = this.mentorshipBuilder.mentorship().withSession(this.session).build();
	}

	private Session createSession() throws BusinessException {
		this.session = EntityFactory.gimme(Session.class, SessionTemplate.VALID);
		this.sessionService.createSession(this.getUserContext(), this.session);
		return this.session;
	}

	@Test
	public void shouldFindPerformedSessionsBySelectedFilter() {

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsBySelectedFilter(
				this.mentorship.getHealthFacility().getDistrict(), this.mentorship.getHealthFacility(),
				this.mentorship.getForm().getProgrammaticArea(), this.mentorship.getForm(), this.mentorship.getTutor(),
				this.mentorship.getCabinet(), startDate, endDate);

		Assert.assertFalse(performedSessions.isEmpty());
		Assert.assertEquals(this.session.getPerformedDate(), startDate);
	}

	@Test
	public void shouldFindPerformedSessionsBySelectedFilterList() {

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		final List<PerformedSession> performedSessions = this.sessionQueryService
				.findPerformedSessionsBySelectedFilterList(this.mentorship.getHealthFacility().getDistrict(),
						this.mentorship.getHealthFacility(), this.mentorship.getForm().getProgrammaticArea(),
						this.mentorship.getForm(), this.mentorship.getTutor(), this.mentorship.getCabinet(), startDate,
						endDate);

		Assert.assertFalse(performedSessions.isEmpty());
		Assert.assertEquals(this.session.getPerformedDate(), startDate);
	}

	@Test
	public void shouldFindMentoringSessionByDistrictAndHealthFacility() {

		final List<SubmitedSessions> submitedSessions = this.sessionQueryService
				.findNumberOfSessionsPerDistrict(this.getUserContext());

		Assert.assertFalse(submitedSessions.isEmpty());

		submitedSessions.forEach(submitedSession -> {
			Assert.assertNotNull(submitedSession.getDistrict());
			Assert.assertNotNull(submitedSession.getProgrammaticArea());
			Assert.assertNotNull(submitedSession.getTotalSubmited());
			Assert.assertNotNull(submitedSession.getLastUpdate());
		});
	}

	@Test
	public void shouldFindPerformedSessionsByTutorAndForm() {

		final LocalDate startDate = LocalDate.now();
		final LocalDate endDate = LocalDate.now();

		final List<PerformedSession> performedSessions = this.sessionQueryService.findPerformedSessionsByTutorAndForm(
				this.mentorship.getTutor(), this.mentorship.getForm(), startDate, endDate);

		Assert.assertFalse(performedSessions.isEmpty());
		Assert.assertEquals(this.session.getPerformedDate(), startDate);
	}

	@Test
	public void shouldFindSessionsWithDuplicatedUuid() throws BusinessException {

		for (int i = 0; i < 3; i++) {
			this.session.setId(null);
			this.session.setCreatedAt(null);
			this.session.setCreatedBy(null);

			this.sessionService.createSession(this.getUserContext(), this.session);
			this.createMentorship();
		}

		final List<Session> sessions = this.sessionQueryService.findSessionsWithDuplicatedUuids();
		Assert.assertFalse(sessions.isEmpty());
	}

	@Test
	public void shouldFetchSessionsByUuid() throws BusinessException {
		final List<Session> sessions = this.sessionQueryService.fetchSessionsByUuid(this.session.getUuid());
		Assert.assertFalse(sessions.isEmpty());
	}
}
