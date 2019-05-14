/**
 *
 */
package mz.org.fgh.mentoring.core.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.question.dao.QuestionCategoryDAO;

/**
 * @author Stélio Moiane
 *
 */
@NamedQueries(@NamedQuery(name = QuestionCategoryDAO.QUERY_NAME.findAll, query = QuestionCategoryDAO.QUERY.findAll))
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "QUESTION_CATEGORIES")
public class QuestionsCategory extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CATEGORY", nullable = false, length = 100)
	private String category;

	public String getCategory() {
		return this.category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}
}
