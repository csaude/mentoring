/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MentorshipBeanResource {

	private UserContext userContext;

	private Mentorship mentorship;

	private List<QuestionHelper> questions;

	private Form form;

	private List<MentorshipHelper> mentorships;

	private List<String> mentorshipUuids;

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

	public Form getForm() {
		return this.form;
	}

	public List<MentorshipHelper> getMentorships() {
		return this.mentorships;
	}

	public void setMentorships(final List<MentorshipHelper> mentorships) {
		this.mentorships = mentorships;
	}

	public List<Answer> getAnswers() {

		final List<Answer> answers = new ArrayList<>();

		if (this.questions == null) {
			return answers;
		}

		for (final QuestionHelper question : this.questions) {
			final Answer answer = question.getQuestionType().getAnswer();
			answer.setValue(question.getValue());
			answer.setQuestion(question.getQuestionObject());
			answers.add(answer);
		}

		return answers;
	}

	public void addMentorshipUuid(final String uuid) {

		if (this.mentorshipUuids == null) {
			this.mentorshipUuids = new ArrayList<>();
		}

		this.mentorshipUuids.add(uuid);
	}
}
