/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerHelper {

	private String questionUuid;

	private String answerUuid;

	private String value;

	public AnswerHelper() {
	}

	public AnswerHelper(final String questionUuid, final String answerUuid, final String value) {
		this.questionUuid = questionUuid;
		this.answerUuid = answerUuid;
		this.value = value;
	}

	public String getQuestionUuid() {
		return this.questionUuid;
	}

	public String getAnswerUuid() {
		return this.answerUuid;
	}

	public String getValue() {
		return this.value;
	}
}
