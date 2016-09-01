/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.question.dao.QuestionDAO;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "QUESTIONS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
@NamedQueries({ @NamedQuery(name = QuestionDAO.QUERY_NAME.findByFormCode, query = QuestionDAO.QUERY.findByFormCode)})
public class Question extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotEmpty
	@Column(name = "QUESTION", nullable = false)
	private String question;

	@NotNull
	@Column(name = "QUESTION_TYPE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private QuestionType questionType;
	
	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
	private  Set<FormQuestion> formQuestions = new HashSet<>();

	public Question() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(final String question) {
		this.question = question;
	}

	public QuestionType getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(final QuestionType questionType) {
		this.questionType = questionType;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "formQuestions");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "formQuestions");
	}

	public Set<FormQuestion> getFormQuestions() {
		return this.formQuestions;
	}

	public void setFormQuestions(final Set<FormQuestion> formQuestions) {
		this.formQuestions = formQuestions;
	}
}
