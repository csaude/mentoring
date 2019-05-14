/**
 *
 */
package mz.org.fgh.mentoring.integ.resources.question;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionCategoryBeanResource {

	private UserContext userContext;

	private QuestionsCategory questionsCategory;

	public QuestionCategoryBeanResource() {
	}

	public UserContext getUserContext() {
		return this.userContext;
	}

	public void setUserContext(final UserContext userContext) {
		this.userContext = userContext;
	}

	public QuestionsCategory getQuestionsCategory() {
		return this.questionsCategory;
	}

	public void setQuestionsCategory(final QuestionsCategory questionsCategory) {
		this.questionsCategory = questionsCategory;
	}
}
