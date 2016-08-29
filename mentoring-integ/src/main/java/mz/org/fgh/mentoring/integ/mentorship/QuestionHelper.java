/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.mentorship;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionHelper extends Question {

	private static final long serialVersionUID = 1L;

	private String value;

	public QuestionHelper() {
	}

	public String getValue() {
		return this.value;
	}

	public Question getQuestionObject() {
		final Question question = new Question();
		question.setId(this.getId());
		question.setQuestionType(this.getQuestionType());

		return question;
	}
}
