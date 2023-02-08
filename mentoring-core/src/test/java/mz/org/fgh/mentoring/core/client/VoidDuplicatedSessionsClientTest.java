/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.fixtureFactory.EntityFactory;
import mz.org.fgh.mentoring.core.answer.service.AnswerQueryService;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.config.AbstractSpringTest;
import mz.org.fgh.mentoring.core.fixturefactory.SessionTemplate;
import mz.org.fgh.mentoring.core.mentorship.MentorshipBuilder;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.core.session.service.SessionService;

/**
 * @author Stélio Moiane
 *
 */
public class VoidDuplicatedSessionsClientTest extends AbstractSpringTest {

	@Inject
	private SessionService sessionService;

	@Inject
	private MentorshipService mentorshipService;

	@Inject
	private SessionQueryService sessionQueryService;

	@Inject
	private AnswerService answerService;

	@Inject
	private AnswerQueryService answerQueryService;

	@Inject
	private MentorshipBuilder mentorshipBuilder;

	@Override
	public void setUp() throws BusinessException {

		final Session session = EntityFactory.gimme(Session.class, SessionTemplate.VALID);

		for (int i = 0; i < 3; i++) {

			session.setId(null);
			session.setCreatedAt(null);
			session.setCreatedBy(null);

			this.sessionService.createSession(this.getUserContext(), session);
			this.mentorshipBuilder.mentorship().withSession(session).build();
		}
	}

	@Test
	public void shouldVoidSessionsWithDuplicatedUuid() throws BusinessException {
		final VoidDuplicatedSessionsClient client = new VoidDuplicatedSessionsClient();

		client.setSessionQueryService(this.sessionQueryService);
		client.setSessionService(this.sessionService);
		client.setMentorshipService(this.mentorshipService);
		client.setAnswerQueryService(this.answerQueryService);
		client.setAnswerService(this.answerService);

		final int records = client.process(client);

		Assert.assertFalse(records == 0);
	}
}
