/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.mentorship;

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

	private Mentorship mentorship;

	private List<QuestionHelper> questions;

	private Form form;

	private UserContext userContext;

	public MentorshipBeanResource() {
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public Form getForm() {
		return this.form;
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public List<Answer> getAnswers() {

		final List<Answer> answers = new ArrayList<>();

		this.questions.stream().forEach(question -> {
			final Answer answer = question.getQuestionType().getAnswer();
			answer.setValue(question.getValue());
			answer.setQuestion(question.getQuestionObject());
			answers.add(answer);
		});

		return answers;
	}
}
