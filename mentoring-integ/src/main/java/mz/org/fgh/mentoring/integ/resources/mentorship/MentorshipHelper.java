/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.answer.model.AnswerHelper;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MentorshipHelper {

	private Mentorship mentorship;

	private List<AnswerHelper> answers;

	public MentorshipHelper() {
	}

	public Mentorship getMentorship() {
		return this.mentorship;
	}

	public void setMentorship(final Mentorship mentorship) {
		this.mentorship = mentorship;
	}

	public List<AnswerHelper> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	public void addAnswerHelper(final AnswerHelper answerHelper) {

		if (this.answers == null) {
			this.answers = new ArrayList<>();
		}

		this.answers.add(answerHelper);
	}
}
