/**
 *
 */
package mz.org.fgh.mentoring.core.session.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "SESSIONS")
public class Session extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	@NotNull
	@Column(name = "STATUS", nullable = false, length = 20)
	@Enumerated(EnumType.STRING)
	private SessionStatus status;

	@Column(name = "REASON")
	private String reason;

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "session")
	private List<Mentorship> mentorships;

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

	public SessionStatus getStatus() {
		return this.status;
	}

	public void setStatus(final SessionStatus status) {
		this.status = status;
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(final String reason) {
		this.reason = reason;
	}

	public List<Mentorship> getMentorships() {
		return Collections.unmodifiableList(this.mentorships);
	}

	public void addMentorship(final Mentorship mentorship) {

		if (this.mentorships == null) {
			this.mentorships = new ArrayList<>();
		}

		this.mentorships.add(mentorship);
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "mentorships");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "mentorships");
	}
}
