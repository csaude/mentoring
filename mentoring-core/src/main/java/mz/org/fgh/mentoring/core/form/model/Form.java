/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
import mz.org.fgh.mentoring.core.form.dao.FormDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;
import mz.org.fgh.mentoring.core.partner.model.Partner;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "FORMS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
@NamedQueries({ @NamedQuery(name = FormDAO.QUERY_NAME.fetchByFormId, query = FormDAO.QUERY.fetchByFormId),
	@NamedQuery(name = FormDAO.QUERY_NAME.findSampleIndicators, query = FormDAO.QUERY.findSampleIndicators) })
public class Form extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotEmpty
	@Column(name = "NAME", nullable = false, length = 150)
	private String name;

	@NotEmpty
	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROGRAMMATIC_AREA_ID", nullable = false)
	private ProgrammaticArea programmaticArea;

	@NotNull
	@Column(name = "FORM_TYPE", nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private FormType formType;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "form")
	private Set<FormQuestion> formQuestions;

	@NotNull
	@Column(name = "TARGET_PATIENT", nullable = false)
	private Integer targetPatient;

	@NotNull
	@Column(name = "TARGET_FILE", nullable = false)
	private Integer targetFile;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARTNER_ID")
	private Partner partner;

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public ProgrammaticArea getProgrammaticArea() {
		return this.programmaticArea;
	}

	public void setProgrammaticArea(final ProgrammaticArea programmaticArea) {
		this.programmaticArea = programmaticArea;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public FormType getFormType() {
		return this.formType;
	}

	public void setFormType(final FormType formType) {
		this.formType = formType;
	}

	public Set<FormQuestion> getFormQuestions() {
		return Collections.unmodifiableSet(this.formQuestions);
	}

	public void addFormQuestion(final FormQuestion formQuestion) {
		if (this.formQuestions == null) {
			this.formQuestions = new HashSet<>();
		}

		this.formQuestions.add(formQuestion);
	}

	public Integer getTargetPatient() {
		return this.targetPatient;
	}

	public void setTargetPatient(final Integer targetPatient) {
		this.targetPatient = targetPatient;
	}

	public Integer getTargetFile() {
		return this.targetFile;
	}

	public void setTargetFile(final Integer targetFile) {
		this.targetFile = targetFile;
	}

	public Partner getPartner() {
		return this.partner;
	}

	public void setPartner(final Partner partner) {
		this.partner = partner;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "programmaticArea", "formQuestions");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "programmaticArea", "formQuestions");
	}
}
