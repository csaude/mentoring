/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.mentorship.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.answer.model.Answer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Eusebio Jose Maposse
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "MENTORSHIPS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Mentorship extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@Column(name = "PERFORMED_DATE", nullable = false)
	private LocalDate performedDate;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTORED_ID", nullable = false)
	private Tutored tutored;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", nullable = false)
	private Form form;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HEALTH_FACILITY_ID", nullable = false)
	private HealthFacility healthFacility;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SESSION_ID")
	private Session session;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "mentorship")
	private List<Answer> answers;

	public Mentorship() {
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public LocalDateTime getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public LocalDate getPerformedDate() {
		return this.performedDate;
	}

	public void setPerformedDate(final LocalDate performedDate) {
		this.performedDate = performedDate;
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(final Tutor tutor) {
		this.tutor = tutor;
	}

	public Tutored getTutored() {
		return this.tutored;
	}

	public void setTutored(final Tutored tutored) {
		this.tutored = tutored;
	}

	public Form getForm() {
		return this.form;
	}

	public void setForm(final Form form) {
		this.form = form;
	}

	public HealthFacility getHealthFacility() {
		return this.healthFacility;
	}

	public void setHealthFacility(final HealthFacility healthFacility) {
		this.healthFacility = healthFacility;
	}

	public Session getSession() {
		return this.session;
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	public List<Answer> getAnswers() {
		return Collections.unmodifiableList(this.answers);
	}

	public void addAnswer(final Answer answer) {

		if (this.answers == null) {
			this.answers = new ArrayList<>();
		}

		this.answers.add(answer);
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "tutor", "tutored", "form", "healthFacility", "session",
		        "answers", "answerHelpers");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "tutor", "tutored", "form", "healthFacility", "session",
		        "answers", "answerHelpers");
	}
}
