/**
 *
 */
package mz.org.fgh.mentoring.core.answer.model;

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
public class AnswerHelper {

	private String questionUuid;

	private String answerUuid;

	private Question question;

	private String value;

	public AnswerHelper() {
	}

	public String getQuestionUuid() {
		return this.questionUuid;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(final Question question) {
		this.question = question;
	}

	public String getAnswerUuid() {
		return this.answerUuid;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(final String value) {
		this.value = value;
	}
}
