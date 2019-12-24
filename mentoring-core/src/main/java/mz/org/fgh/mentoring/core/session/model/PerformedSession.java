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
	
	/**
	 * Properties of HTS report
	 */
	private String tutoredName;
	private String door;
	private String timeOfDay;
	private Long atendidos;
	private Long previos;
	private Long testados;
	private Long positivos;
	private Long inscritos;
	
	/**
	 * Properties of Narrative report
	 */
	private Long saaj;
	private Long htcLink;
	private Long smi;
	private Long stiAdultsPrison;
	private Long ctAdultsPrison;
	private Long ctAdults;
	private Long apss;
	private Long adultVl;
	private Long tbHiv;
	private Long tpi;
	private Long nutrition;

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
	
	/**
	 * This constructor will build the HTS Summary
	 */
	public PerformedSession(
			final String districtName, 
			final String healthFacility, 
			final String performedDate,
			final String tutorName, 
			final String tutoredName, 
			final String cabinet,
			final String door, 
			final String timeOfDay, 
			final Long atendidos,
			final Long previos, 
			final Long testados, 
			final Long positivos, 
			final Long inscritos,
			final String createdAt) {
		this.district = districtName;
		this.healthFacility = healthFacility;
		this.performedDate =performedDate;
		this.tutorName = tutorName;
		this.cabinet = cabinet;
		this.tutoredName = tutoredName;
		this.door = door;
		this.timeOfDay = timeOfDay;
		this.atendidos = atendidos;
		this.previos = previos;
		this.testados = testados;
		this.positivos = positivos;
		this.inscritos = inscritos;
		this.createdAt=createdAt;
	}
	
	

	public PerformedSession(String district,Long saaj, Long htcLink, Long smi, Long stiAdultsPrison, Long ctAdultsPrison, Long ctAdults, Long apss,
			Long adultVl, Long tbHiv, Long tpi, Long nutrition) {
		this.district=district;
		this.saaj = saaj;
		this.htcLink = htcLink;
		this.smi = smi;
		this.stiAdultsPrison = stiAdultsPrison;
		this.ctAdultsPrison = ctAdultsPrison;
		this.ctAdults = ctAdults;
		this.apss = apss;
		this.adultVl = adultVl;
		this.tbHiv = tbHiv;
		this.tpi = tpi;
		this.nutrition = nutrition;
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

	public String getTutoredName() {
		return tutoredName;
	}

	public void setTutoredName(String tutoredName) {
		this.tutoredName = tutoredName;
	}

	public String getDoor() {
		return door;
	}

	public void setDoor(String door) {
		this.door = door;
	}

	public String getTimeOfDay() {
		return timeOfDay;
	}

	public void setTimeOfDay(String timeOfDay) {
		this.timeOfDay = timeOfDay;
	}

	public Long getAtendidos() {
		return atendidos;
	}

	public void setAtendidos(Long atendidos) {
		this.atendidos = atendidos;
	}

	public Long getPrevios() {
		return previos;
	}

	public void setPrevios(Long previos) {
		this.previos = previos;
	}

	public Long getTestados() {
		return testados;
	}

	public void setTestados(Long testados) {
		this.testados = testados;
	}

	public Long getPositivos() {
		return positivos;
	}

	public void setPositivos(Long positivos) {
		this.positivos = positivos;
	}

	public Long getInscritos() {
		return inscritos;
	}

	public void setInscritos(Long inscritos) {
		this.inscritos = inscritos;
	}

	public Long getSaaj() {
		return saaj;
	}

	public void setSaaj(Long saaj) {
		this.saaj = saaj;
	}

	public Long getHtcLink() {
		return htcLink;
	}

	public void setHtcLink(Long htcLink) {
		this.htcLink = htcLink;
	}

	public Long getSmi() {
		return smi;
	}

	public void setSmi(Long smi) {
		this.smi = smi;
	}

	public Long getCtAdults() {
		return ctAdults;
	}

	public void setCtAdults(Long ctAdults) {
		this.ctAdults = ctAdults;
	}

	public Long getApss() {
		return apss;
	}

	public void setApss(Long apss) {
		this.apss = apss;
	}

	public Long getAdultVl() {
		return adultVl;
	}

	public void setAdultVl(Long adultVl) {
		this.adultVl = adultVl;
	}

	public Long getTbHiv() {
		return tbHiv;
	}

	public void setTbHiv(Long tbHiv) {
		this.tbHiv = tbHiv;
	}

	public Long getTpi() {
		return tpi;
	}

	public void setTpi(Long tpi) {
		this.tpi = tpi;
	}

	public Long getNutrition() {
		return nutrition;
	}

	public void setNutrition(Long nutrition) {
		this.nutrition = nutrition;
	}

	public Long getStiAdultsPrison() {
		return stiAdultsPrison;
	}

	public void setStiAdultsPrison(Long stiAdultsPrison) {
		this.stiAdultsPrison = stiAdultsPrison;
	}

	public Long getCtAdultsPrison() {
		return ctAdultsPrison;
	}

	public void setCtAdultsPrison(Long ctAdultsPrison) {
		this.ctAdultsPrison = ctAdultsPrison;
	}
	
	
	
}
