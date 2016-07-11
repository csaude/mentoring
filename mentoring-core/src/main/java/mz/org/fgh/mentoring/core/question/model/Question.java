package mz.org.fgh.mentoring.core.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "QUESTIONS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Question extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotEmpty
	@Column(name = "QUESTION", nullable = false, length = 50)
	private String question;

	@NotEmpty
	@Column(name = "QUESTION_TYPE")
	@Enumerated(EnumType.STRING)
	private QuestionType questionType;

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
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

}
