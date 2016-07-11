package mz.org.fgh.mentoring.core.question.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import mz.org.fgh.mentoring.core.util.QuestionType;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "QUESTIONS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Question {

	
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
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	
}
