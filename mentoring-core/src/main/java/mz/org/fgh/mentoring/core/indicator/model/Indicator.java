/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;

import mz.co.mozview.frameworks.core.model.GenericEntity;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
@Table(name = "INDICATORS", uniqueConstraints = @UniqueConstraint(columnNames = { "CODE" }))
public class Indicator extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(name = "CODE", nullable = false, length = 50)
	private String code;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
	@Column(name = "PERFORMED_DATE", nullable = false)
	private LocalDateTime performedDate;

	@NotNull
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@Column(name = "REFERRED_MONTH", nullable = false)
	private LocalDate referredMonth;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TUTOR_ID", nullable = false)
	private Tutor tutor;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FORM_ID", nullable = false)
	private Form form;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "HEALTH_FACILITY_ID", nullable = false)
	private HealthFacility healthFacility;

	public String getCode() {
		return this.code;
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public LocalDateTime getPerformedDate() {
		return this.performedDate;
	}

	public void setPerformedDate(final LocalDateTime performedDate) {
		this.performedDate = performedDate;
	}

	public LocalDate getReferredMonth() {
		return this.referredMonth;
	}

	public void setReferredMonth(final LocalDate referredMonth) {
		this.referredMonth = referredMonth;
	}

	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(final Tutor tutor) {
		this.tutor = tutor;
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

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "tutor", "form", "healthFacility");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "tutor", "form", "healthFacility");
	}
}
