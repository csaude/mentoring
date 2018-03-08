/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.integ.resources.mentorship.dto.SessionDTO;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MentorshipBeanResource {

	private UserContext userContext;

	private Mentorship mentorship;

	private List<SessionDTO> sessions;

	private List<String> sessionUuids;

	public MentorshipBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public void setUserContext(final UserContext userContext) {
		this.userContext = userContext;
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public List<Session> getSessions() {
		return this.sessions.stream().map(sessionDTO -> {
			final Session session = sessionDTO.getSession();

			sessionDTO.getMentorships().forEach(mentorshipDTO -> {
				final Mentorship mentorship = mentorshipDTO.getMentorship();

				mentorshipDTO.getAnswers().forEach(answerDTO -> {
					final Answer answer = answerDTO.getQuestion().getQuestionType().getAnswer();

					answer.setUuid(answerDTO.getAnswerUuid());
					answer.setValue(answerDTO.getValue());
					answer.setQuestion(answerDTO.getQuestion());

					mentorship.addAnswer(answer);
				});

				session.addMentorship(mentorship);
			});

			return session;
		}).collect(Collectors.toList());
	}

	public void setSessionUuids() {
		this.sessionUuids = this.sessions.stream().map(sessionDTO -> sessionDTO.getSession().getUuid())
		        .collect(Collectors.toList());
		this.sessions = null;
	}

	public List<String> getSessionUuids() {
		return Collections.unmodifiableList(this.sessionUuids);
	}

	public void addSessionDTO(final SessionDTO sessionDTO) {

		if (this.sessions == null) {
			this.sessions = new ArrayList<>();
		}

		this.sessions.add(sessionDTO);
	}
}
