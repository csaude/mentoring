/*
 * Friends in Global Health - FGH © 2016
 */

package mz.org.fgh.mentoring.core.mentorship.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Entity
@Table(name = "MENTORSHIPS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Mentorship extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@Column(name = "START_DATE", nullable = false)
	private LocalDate startDate;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@Column(name = "END_DATE", nullable = false)
	private LocalDate endDate;

	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTORED_ID", nullable = false)
	private Tutored tutored;

	public LocalDate getStartDate() {
		return this.startDate;
	}

	public void setStartDate(final LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setEndDate(final LocalDate endDate) {
		this.endDate = endDate;
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

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "tutor", "tutored");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "tutor", "tutored");
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
