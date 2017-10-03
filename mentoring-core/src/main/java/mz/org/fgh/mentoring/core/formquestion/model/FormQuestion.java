/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.formquestion.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO;
import mz.org.fgh.mentoring.core.question.model.Question;

/**
 * @author Eusebio Jose Maposse
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "FORMS_QUESTIONS", uniqueConstraints = @UniqueConstraint(columnNames = { "FORM_ID", "QUESTION_ID" }))
@NamedQueries({ @NamedQuery(name = FormQuestionDAO.QUERY_NAME.findByFormId, query = FormQuestionDAO.QUERY.findByFormId),
        @NamedQuery(name = FormQuestionDAO.QUERY_NAME.findByFormIdAndQuestionId, query = FormQuestionDAO.QUERY.findByFormIdAndQuestionId),
        @NamedQuery(name = FormQuestionDAO.QUERY_NAME.fetchByTutor, query = FormQuestionDAO.QUERY.fetchByTutor) })
public class FormQuestion extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", nullable = false)
	private Form form;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUESTION_ID", nullable = false)
	private Question question;

	@NotNull
	@Column(name = "MANDATORY", nullable = false)
	private boolean mandatory;

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(final Question question) {
		this.question = question;
	}

	public boolean isMandatory() {
		return this.mandatory;
	}

	public void setMandatory(final boolean mandatory) {
		this.mandatory = mandatory;
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
