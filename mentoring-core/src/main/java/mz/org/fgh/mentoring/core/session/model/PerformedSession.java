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
	private Long preventionVCT;
	private Long preventionPICT;
	private Long preventionIndexCase;
	private Long preventionSaaj;
	private Long preventionHtcLink;
	private Long preventionANC;
	private Long ctStiAdultsPrison;
	private Long ctAdultsPrison;
	private Long ctAdultsVLPrison;
	private Long ctTbHiv;
	private Long ctApss;
	private Long ctAdults;
	private Long ctAdultsVL;
	private Long ctInh;
	private Long ctNutrition;
	private Long ctApssTutoreds;
	private Long ctApssSessions;
	private Long ctEAC;
	private Long ctCervical;
	private Long tbSessions;
	private Long tbSessionsCt;
	private Long tbInh;
	private Long tbSessionsPediatric;
	private Long pediatricNutrition;
	private Long pediatricStarART;
	private Long pediatricAMA;
	private Long pediatricTB;
	private Long pediatricVL;
	
	/**
	 * Properties of POP report
	 */
	private String elaborado;
	private String aprovado;
	private String revisado;

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
	
	

	

	public PerformedSession(String district, Long preventionVCT, Long preventionPICT, Long preventionIndexCase,
			Long preventionSaaj, Long preventionHtcLink, Long preventionANC, Long ctStiAdultsPrison,
			Long ctAdultsPrison, Long ctAdultsVLPrison, Long ctTbHiv, Long ctApss, Long ctAdults, Long ctAdultsVL,
			Long ctInh, Long ctNutrition, Long ctApssTutoreds, Long ctHCW, Long ctEAC, Long ctCervical, Long tbSessions,
			Long tbSessionsCt, Long tbInh, Long tbSessionsPediatric, Long pediatricNutrition, Long pediatricStarART,
			Long pediatricAMA, Long pediatricTB, Long pediatricVL) {
		this.district = district;
		this.preventionVCT = preventionVCT;
		this.preventionPICT = preventionPICT;
		this.preventionIndexCase = preventionIndexCase;
		this.preventionSaaj = preventionSaaj;
		this.preventionHtcLink = preventionHtcLink;
		this.preventionANC = preventionANC;
		this.ctStiAdultsPrison = ctStiAdultsPrison;
		this.ctAdultsPrison = ctAdultsPrison;
		this.ctAdultsVLPrison = ctAdultsVLPrison;
		this.ctTbHiv = ctTbHiv;
		this.ctApss = ctApss;
		this.ctAdults = ctAdults;
		this.ctAdultsVL = ctAdultsVL;
		this.ctInh = ctInh;
		this.ctNutrition = ctNutrition;
		this.ctApssTutoreds = ctApssTutoreds;
		this.ctApssSessions = ctHCW;
		this.ctEAC = ctEAC;
		this.ctCervical = ctCervical;
		this.tbSessions = tbSessions;
		this.tbSessionsCt = tbSessionsCt;
		this.tbInh = tbInh;
		this.tbSessionsPediatric = tbSessionsPediatric;
		this.pediatricNutrition = pediatricNutrition;
		this.pediatricStarART = pediatricStarART;
		this.pediatricAMA = pediatricAMA;
		this.pediatricTB = pediatricTB;
		this.pediatricVL = pediatricVL;
	}
	
	

	public PerformedSession(String district, String healthFacility,String performedDate,String tutorName, String formName,
			  String elaborado, String aprovado, String revisado, String createdAt) {
		
		this.district = district;
		this.healthFacility = healthFacility;
		this.performedDate = performedDate;
		this.tutorName = tutorName;
		this.formName = formName;
		this.elaborado = elaborado;
		this.aprovado = aprovado;
		this.revisado = revisado;
		this.createdAt = createdAt;
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

	public Long getPreventionVCT() {
		return preventionVCT;
	}

	public void setPreventionVCT(Long preventionVCT) {
		this.preventionVCT = preventionVCT;
	}

	public Long getPreventionPICT() {
		return preventionPICT;
	}

	public void setPreventionPICT(Long preventionPICT) {
		this.preventionPICT = preventionPICT;
	}

	public Long getPreventionIndexCase() {
		return preventionIndexCase;
	}

	public void setPreventionIndexCase(Long preventionIndexCase) {
		this.preventionIndexCase = preventionIndexCase;
	}

	public Long getPreventionSaaj() {
		return preventionSaaj;
	}

	public void setPreventionSaaj(Long preventionSaaj) {
		this.preventionSaaj = preventionSaaj;
	}

	public Long getPreventionHtcLink() {
		return preventionHtcLink;
	}

	public void setPreventionHtcLink(Long preventionHtcLink) {
		this.preventionHtcLink = preventionHtcLink;
	}

	public Long getPreventionANC() {
		return preventionANC;
	}

	public void setPreventionANC(Long preventionANC) {
		this.preventionANC = preventionANC;
	}

	public Long getCtStiAdultsPrison() {
		return ctStiAdultsPrison;
	}

	public void setCtStiAdultsPrison(Long ctStiAdultsPrison) {
		this.ctStiAdultsPrison = ctStiAdultsPrison;
	}

	public Long getCtAdultsPrison() {
		return ctAdultsPrison;
	}

	public void setCtAdultsPrison(Long ctAdultsPrison) {
		this.ctAdultsPrison = ctAdultsPrison;
	}

	public Long getCtAdultsVLPrison() {
		return ctAdultsVLPrison;
	}

	public void setCtAdultsVLPrison(Long ctAdultsVLPrison) {
		this.ctAdultsVLPrison = ctAdultsVLPrison;
	}

	public Long getCtTbHiv() {
		return ctTbHiv;
	}

	public void setCtTbHiv(Long ctTbHiv) {
		this.ctTbHiv = ctTbHiv;
	}

	public Long getCtApss() {
		return ctApss;
	}

	public void setCtApss(Long ctApss) {
		this.ctApss = ctApss;
	}

	public Long getCtAdults() {
		return ctAdults;
	}

	public void setCtAdults(Long ctAdults) {
		this.ctAdults = ctAdults;
	}

	public Long getCtAdultsVL() {
		return ctAdultsVL;
	}

	public void setCtAdultsVL(Long ctAdultsVL) {
		this.ctAdultsVL = ctAdultsVL;
	}

	public Long getCtInh() {
		return ctInh;
	}

	public void setCtInh(Long ctInh) {
		this.ctInh = ctInh;
	}

	public Long getCtNutrition() {
		return ctNutrition;
	}

	public void setCtNutrition(Long ctNutrition) {
		this.ctNutrition = ctNutrition;
	}

	public Long getCtApssTutoreds() {
		return ctApssTutoreds;
	}

	public void setCtApssTutoreds(Long ctApssTutoreds) {
		this.ctApssTutoreds = ctApssTutoreds;
	}

	public Long getCtApssSessions() {
		return ctApssSessions;
	}

	public void setCtApssSessions(Long ctHCW) {
		this.ctApssSessions = ctHCW;
	}

	public Long getCtEAC() {
		return ctEAC;
	}

	public void setCtEAC(Long ctEAC) {
		this.ctEAC = ctEAC;
	}

	public Long getCtCervical() {
		return ctCervical;
	}

	public void setCtCervical(Long ctCervical) {
		this.ctCervical = ctCervical;
	}

	public Long getTbSessions() {
		return tbSessions;
	}

	public void setTbSessions(Long tbSessions) {
		this.tbSessions = tbSessions;
	}

	public Long getTbSessionsCt() {
		return tbSessionsCt;
	}

	public void setTbSessionsCt(Long tbSessionsCt) {
		this.tbSessionsCt = tbSessionsCt;
	}

	public Long getTbInh() {
		return tbInh;
	}

	public void setTbInh(Long tbInh) {
		this.tbInh = tbInh;
	}

	public Long getTbSessionsPediatric() {
		return tbSessionsPediatric;
	}

	public void setTbSessionsPediatric(Long tbSessionsPediatric) {
		this.tbSessionsPediatric = tbSessionsPediatric;
	}

	public Long getPediatricNutrition() {
		return pediatricNutrition;
	}

	public void setPediatricNutrition(Long pediatricNutrition) {
		this.pediatricNutrition = pediatricNutrition;
	}

	public Long getPediatricStarART() {
		return pediatricStarART;
	}

	public void setPediatricStarART(Long pediatricStarART) {
		this.pediatricStarART = pediatricStarART;
	}

	public Long getPediatricAMA() {
		return pediatricAMA;
	}

	public void setPediatricAMA(Long pediatricAMA) {
		this.pediatricAMA = pediatricAMA;
	}

	public Long getPediatricTB() {
		return pediatricTB;
	}

	public void setPediatricTB(Long pediatricTB) {
		this.pediatricTB = pediatricTB;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getElaborado() {
		return elaborado;
	}

	public void setElaborado(String elaborado) {
		this.elaborado = elaborado;
	}

	public String getAprovado() {
		return aprovado;
	}

	public void setAprovado(String aprovado) {
		this.aprovado = aprovado;
	}

	public String getRevisado() {
		return revisado;
	}

	public void setRevisado(String revisado) {
		this.revisado = revisado;
	}

	public Long getPediatricVL() {
		return pediatricVL;
	}

	public void setPediatricVL(Long pediatricVL) {
		this.pediatricVL = pediatricVL;
	}

	
	
}
