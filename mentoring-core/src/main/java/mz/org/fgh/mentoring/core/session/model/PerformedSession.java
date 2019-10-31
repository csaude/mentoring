/**
 *
 */
package mz.org.fgh.mentoring.core.session.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import mz.org.fgh.mentoring.core.util.LocalDateAdapter;
import mz.org.fgh.mentoring.core.util.LocalDateTimeAdapter;

/**
 * @author Stélio Moiane
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PerformedSession {

	private String district;
	private String healthFacility;
	private String formName;
	private Long totalPerformed;
	private String createdAt;
	private String performedDate;
	private String tutorName;
	private String position;
	private String startDate;
	private String endDate;
	private SessionStatus status;
	private String cabinet;

	public PerformedSession() {
	}

	public PerformedSession(final String formName, final Calendar createdAt, final LocalDate performedDate,
	        final String district, final String healthFacility, final String cabinet, final String tutorName,
	        final String tutorSurname, final String position, final LocalDateTime startDate,
	        final LocalDateTime endDate, final SessionStatus status) {
		this.formName = formName;
		this.createdAt = new LocalDateTimeAdapter()
		        .marshal(LocalDateTime.ofInstant(createdAt.toInstant(), createdAt.getTimeZone().toZoneId()));
		this.performedDate = new LocalDateAdapter().marshal(performedDate);
		this.district = district;
		this.healthFacility = healthFacility;
		this.cabinet = cabinet;
		this.tutorName = tutorName + " " + tutorSurname;
		this.position = position;
		this.startDate = new LocalDateTimeAdapter().marshal(startDate);
		this.endDate = new LocalDateTimeAdapter().marshal(endDate);
		this.status = status;
	}

	public PerformedSession(final String formName, final Long totalPerformed) {
		this.formName = formName;
		this.totalPerformed = totalPerformed;
	}

	public PerformedSession(final String district, final String healthFacility, final Long totalPerformed) {
		this.district = district;
		this.healthFacility = healthFacility;
		this.totalPerformed = totalPerformed;
	}

	public String getDistrict() {
		return this.district;
	}

	public String getHealthFacility() {
		return this.healthFacility;
	}

	public String getFormName() {
		return this.formName;
	}

	public Long getTotalPerformed() {
		return this.totalPerformed;
	}

	public String getCreatedAt() {
		return this.createdAt;
	}

	public String getPerformedDate() {
		return this.performedDate;
	}

	public String getTutorName() {
		return this.tutorName;
	}

	public String getPosition() {
		return this.position;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public String getEndDate() {
		return this.endDate;
	}

	public SessionStatus getStatus() {
		return this.status;
	}

	public String getCabinet() {
		return this.cabinet;
	}
}
