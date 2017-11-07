/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.answer;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.answer.model.Answer;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class AnswerBeanResource {

	private Answer answer;

	private String value;

	public AnswerBeanResource(final Answer answer, final String value) {
		this.answer = answer;
		this.value = value;
	}

	public AnswerBeanResource() {
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public String getValue() {
		return this.value;
	}
}
