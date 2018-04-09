/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.SqlResultSetMappings;
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
import mz.org.fgh.mentoring.core.indicator.dao.IndicatorDAO;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.util.LocalDateAdapter;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Stélio Moiane
 *
 */
@SqlResultSetMappings({
        @SqlResultSetMapping(name = SampleIndicator.NAME, classes = {
                @ConstructorResult(targetClass = SampleIndicator.class, columns = {
                        @ColumnResult(name = "DISTRICT", type = String.class), @ColumnResult(name = "HEALTH_FACILITY"),
                        @ColumnResult(name = "NAME"), @ColumnResult(name = "COLLECTED", type = Long.class),
                        @ColumnResult(name = "REJECTED", type = BigDecimal.class),
                        @ColumnResult(name = "RECEIVED", type = BigDecimal.class),
                        @ColumnResult(name = "TRANSPORTED", type = BigDecimal.class), }) }),
        @SqlResultSetMapping(name = AnalysisTable.NAME, classes = {
                @ConstructorResult(targetClass = AnalysisTable.class, columns = { @ColumnResult(name = "NAME"),
                        @ColumnResult(name = "COLLECTED_SAMPLES", type = Long.class),
                        @ColumnResult(name = "REFERRED_SAMPLES", type = BigDecimal.class),
                        @ColumnResult(name = "REJECTED_SAMPLES", type = BigDecimal.class),
                        @ColumnResult(name = "RECEIVED_RESULT", type = BigDecimal.class),
                        @ColumnResult(name = "TRANSPORTED", type = BigDecimal.class),
                        @ColumnResult(name = "REJECTED", type = BigDecimal.class),
                        @ColumnResult(name = "RESULT", type = BigDecimal.class) }) }) })
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@NamedQueries({
        @NamedQuery(name = IndicatorDAO.QUERY_NAME.findByHealthFacilityFormAndReferredMonth, query = IndicatorDAO.QUERY.findByHealthFacilityFormAndReferredMonth),
        @NamedQuery(name = IndicatorDAO.QUERY_NAME.findDuplicated, query = IndicatorDAO.QUERY.findDuplicated) })
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

	@XmlTransient
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "indicator")
	private final Set<Answer> answers = new HashSet<>();

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

	public Set<Answer> getAnswers() {
		return Collections.unmodifiableSet(this.answers);
	}

	public void setHealthFacility(final HealthFacility healthFacility) {
		this.healthFacility = healthFacility;
	}

	@Override
	public boolean equals(final Object that) {
		return EqualsBuilder.reflectionEquals(this, that, "tutor", "form", "healthFacility", "answers");
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, "tutor", "form", "healthFacility", "answers");
	}
}