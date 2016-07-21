/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.quetion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuetionBeanResource {

	private UserContext userContext;
	private Question question;

	public QuetionBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
