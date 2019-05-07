/**
 *
 */
package mz.org.fgh.mentoring.core.client;

import java.util.List;
import java.util.logging.Logger;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.answer.service.AnswerQueryService;
import mz.org.fgh.mentoring.core.answer.service.AnswerService;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.service.MentorshipService;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.service.SessionQueryService;
import mz.org.fgh.mentoring.core.session.service.SessionService;

/**
 * @author Stélio Moiane
 *
 */
public class VoidDuplicatedSessionsClient extends ClientConfig<VoidDuplicatedSessionsClient> {

	private SessionQueryService sessionQueryService;

	private SessionService sessionService;

	private MentorshipService mentorshipService;

	private AnswerService answerService;

	private AnswerQueryService answerQueryService;

	private static Logger logger = Logger.getLogger(VoidDuplicatedSessionsClient.class.getName());

	@Override
	public int process(final VoidDuplicatedSessionsClient client) throws BusinessException {

		final List<Session> sessionsWithDuplicatedUuids = this.sessionQueryService.findSessionsWithDuplicatedUuids();

		if (sessionsWithDuplicatedUuids.isEmpty()) {
			return 0;
		}

		logger.info(
		        "A total of " + sessionsWithDuplicatedUuids.size() + " Session(s) will be cleaned in the system...");

		for (final Session duplicatedSession : sessionsWithDuplicatedUuids) {
			final List<Session> sessions = this.sessionQueryService.fetchSessionsByUuid(duplicatedSession.getUuid());

			final Session lastSession = sessions.get(0);

			sessions.stream().filter(session -> lastSession.getId() != session.getId()).forEach(session -> {
				this.inactiveSession(session);
			});
		}

		logger.info("A total of " + sessionsWithDuplicatedUuids.size()
		        + " Indicator(s) were successfully cleaned in the system...");

		return sessionsWithDuplicatedUuids.size();
	}

	private void inactiveSession(final Session session) {
		try {
			session.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
			this.sessionService.updateSession(this.getUserContext(), session);

			session.getMentorships().forEach(mentorship -> {
				this.inactiveMentorship(mentorship);
			});
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	private void inactiveMentorship(final Mentorship mentorship) {
		try {
			mentorship.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
			this.mentorshipService.updateMentorship(this.getUserContext(), mentorship);

			final List<Answer> answers = this.answerQueryService.fetchAnswersByMentorship(this.getUserContext(),
			        mentorship);

			answers.stream().forEach(answer -> {
				this.inactiveAnswer(answer);
			});
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	private void inactiveAnswer(final Answer answer) {
		answer.setLifeCycleStatus(LifeCycleStatus.INACTIVE);
		try {
			this.answerService.updateAnswer(this.getUserContext(), answer);
		}
		catch (final BusinessException e) {
			e.printStackTrace();
		}
	}

	public void setSessionQueryService(final SessionQueryService sessionQueryService) {
		this.sessionQueryService = sessionQueryService;
	}

	public void setSessionService(final SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public void setMentorshipService(final MentorshipService mentorshipService) {
		this.mentorshipService = mentorshipService;
	}

	public void setAnswerService(final AnswerService answerService) {
		this.answerService = answerService;
	}

	public void setAnswerQueryService(final AnswerQueryService answerQueryService) {
		this.answerQueryService = answerQueryService;
	}

	public static void main(final String[] args) throws BusinessException {

		logger.info("The Client is Starting to execute ........");

		final VoidDuplicatedSessionsClient client = new VoidDuplicatedSessionsClient();
		client.setup();

		client.setSessionQueryService(client.getBean(SessionQueryService.class));
		client.setSessionService(client.getBean(SessionService.class));
		client.setMentorshipService(client.getBean(MentorshipService.class));
		client.setAnswerQueryService(client.getBean(AnswerQueryService.class));
		client.setAnswerService(client.getBean(AnswerService.class));

		client.process(client);

		client.close();

		logger.info("The Client was executed with success ........");
	}
}
