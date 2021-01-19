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
	private Long cabinetId;
	
	/**
	 * Properties of Narrative report
	 */
	private Long preventionVCT;
	private Long preventionPICT;
	private Long preventionIndexCase;
	private Long preventionSaaj;
	private Long preventionHtcLink;
	private Long preventionANC;
	private Long preventionCPN;
	private Long ctStiAdultsPrison;
	private Long ctAdultsPrison;
	private Long ctAdultsVLPrison;
	private Long ctTbHiv;
	private Long ctApss;
	private Long ctAdults;
	private Long ctAdultsVL;
	private Long ctInh;
	private Long ctTbHivCt;
	private Long ctNutrition;
	private Long ctApssTutoreds;
	private Long ctApssSessions;
	private Long ctEAC;
	private Long ctMDC;
	private Long ctCervical;
	private Long ctStiAdults;
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
	
	/**
	 * Props for PMQ-TR_HIV Report
	 */
	private int formacao;
	private int instalacoes;
	private int seguranca;
	private int pretestagem;
	private int testagem;
	private int postestagem;
	private int avaliacao;
	private int total;
	private Long mentorship_id;
	
	/**
	 * COP20 Costed Workplan report
	 */
	private Long ind_11061;
	private Long ind_11011;
	private Long ind_11031;
	private Long ind_11041;
	private Long ind_11043;
	private Long ind_11073;
	private Long ind_42   ;
	private Long ind_10043;
	private Long ind_10045;
	private Long ind_04071;
	private Long ind_04073;
	private Long ind_04041;
	private Long ind_04077;
	private Long ind_04061;
	private Long ind_15051;
	private Long ind_06044;
	private Long ind_02041;
	private Long ind_01102;
	private Long ind_01031;
	private Long ind_01142;
	private Long ind_02063;
	private Long ind_01116;
	private Long ind_02071;
	private Long ind_02021;
	private Long ind_02023;
	private Long ind_08051;
	private Long ind_03029;
	private Long ind_030211;
	private Long ind_030213;
	private Long ind_03011;
	private Long ind_03013;
	private Long ind_05012;
	private Long ind_05031;
	private Long ind_05061;
	private Long ind_05052;
	private Long ind_05054;
	private Long ind_05057;
	private Long ind_19051;
	private Long ind_19015;
	

	public PerformedSession() {
	}
	
	
	






	public PerformedSession(String district, Long ind_11061, Long ind_11011, Long ind_11031, Long ind_11041,
			Long ind_11043, Long ind_11073, Long ind_42, Long ind_10043, Long ind_10045, Long ind_04071, Long ind_04073,
			Long ind_04041, Long ind_04077, Long ind_04061, Long ind_15051, Long ind_06044, Long ind_02041,
			Long ind_01102, Long ind_01031, Long ind_01142, Long ind_02063, Long ind_01116, Long ind_02071,
			Long ind_02021, Long ind_02023, Long ind_08051, Long ind_03029, Long ind_030211, Long ind_030213,
			Long ind_03011, Long ind_03013, Long ind_05012, Long ind_05031, Long ind_05061, Long ind_05052,
			Long ind_05054, Long ind_05057, Long ind_19051, Long ind_19015) {
		this.district = district;
		this.ind_11061 = ind_11061;
		this.ind_11011 = ind_11011;
		this.ind_11031 = ind_11031;
		this.ind_11041 = ind_11041;
		this.ind_11043 = ind_11043;
		this.ind_11073 = ind_11073;
		this.ind_42 = ind_42;
		this.ind_10043 = ind_10043;
		this.ind_10045 = ind_10045;
		this.ind_04071 = ind_04071;
		this.ind_04073 = ind_04073;
		this.ind_04041 = ind_04041;
		this.ind_04077 = ind_04077;
		this.ind_04061 = ind_04061;
		this.ind_15051 = ind_15051;
		this.ind_06044 = ind_06044;
		this.ind_02041 = ind_02041;
		this.ind_01102 = ind_01102;
		this.ind_01031 = ind_01031;
		this.ind_01142 = ind_01142;
		this.ind_02063 = ind_02063;
		this.ind_01116 = ind_01116;
		this.ind_02071 = ind_02071;
		this.ind_02021 = ind_02021;
		this.ind_02023 = ind_02023;
		this.ind_08051 = ind_08051;
		this.ind_03029 = ind_03029;
		this.ind_030211 = ind_030211;
		this.ind_030213 = ind_030213;
		this.ind_03011 = ind_03011;
		this.ind_03013 = ind_03013;
		this.ind_05012 = ind_05012;
		this.ind_05031 = ind_05031;
		this.ind_05061 = ind_05061;
		this.ind_05052 = ind_05052;
		this.ind_05054 = ind_05054;
		this.ind_05057 = ind_05057;
		this.ind_19051 = ind_19051;
		this.ind_19015 = ind_19015;
	}









	public PerformedSession(String district, String healthFacility, String performedDate,
			String tutorName,String tutoredName,  String cabinet, int formacao, int instalacoes, int seguranca,
			int pretestagem, int testagem, int postestagem, int avaliacao, int total,
			String createdAt, Long mentorship_id) {
		this.district = district;
		this.healthFacility = healthFacility;
		this.performedDate = performedDate;
		this.tutorName = tutorName;
		this.tutoredName = tutoredName;
		this.cabinet = cabinet;
		this.formacao = formacao;
		this.setInstalacoes(instalacoes);
		this.setSeguranca(seguranca);
		this.setPretestagem(pretestagem);
		this.setTestagem(testagem);
		this.setPostestagem(postestagem);
		this.setAvaliacao(avaliacao);
		this.setTotal(total);
		this.createdAt = createdAt;
		this.mentorship_id = mentorship_id;
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
	
	/**
	 * This constructor will build the HTS Summary Mobile
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
			final String createdAt,
			final Long cabinetId) {
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
		this.cabinetId=cabinetId;
	}
	
	

	

	public PerformedSession(String district, Long preventionVCT, Long preventionPICT, Long preventionIndexCase,
			Long preventionSaaj, Long preventionHtcLink, Long preventionANC,Long preventionCPN, Long ctStiAdultsPrison,
			Long ctAdultsPrison, Long ctAdultsVLPrison, Long ctTbHiv, Long ctApss, Long ctAdults, Long ctAdultsVL,
			Long ctInh, Long ctTbHivCt, Long ctNutrition, Long ctApssTutoreds, Long ctHCW, Long ctEAC, Long ctMDC,Long ctCervical, Long ctStiAdults,Long tbSessions,
			Long tbSessionsCt, Long tbInh, Long tbSessionsPediatric, Long pediatricNutrition, Long pediatricStarART,
			Long pediatricAMA, Long pediatricTB, Long pediatricVL) {
		this.district = district;
		this.preventionVCT = preventionVCT;
		this.preventionPICT = preventionPICT;
		this.preventionIndexCase = preventionIndexCase;
		this.preventionSaaj = preventionSaaj;
		this.preventionHtcLink = preventionHtcLink;
		this.preventionANC = preventionANC;
		this.preventionCPN = preventionCPN;
		this.ctStiAdultsPrison = ctStiAdultsPrison;
		this.ctAdultsPrison = ctAdultsPrison;
		this.ctAdultsVLPrison = ctAdultsVLPrison;
		this.ctTbHiv = ctTbHiv;
		this.ctApss = ctApss;
		this.ctAdults = ctAdults;
		this.ctAdultsVL = ctAdultsVL;
		this.ctInh = ctInh;
		this.ctTbHivCt = ctTbHivCt;
		this.ctNutrition = ctNutrition;
		this.ctApssTutoreds = ctApssTutoreds;
		this.ctApssSessions = ctHCW;
		this.ctEAC = ctEAC;
		this.ctMDC = ctMDC;
		this.ctCervical = ctCervical;
		this.ctStiAdults = ctStiAdults;
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

	public Long getCabinetId() {
		return cabinetId;
	}

	public void setCabinetId(Long cabinetId) {
		this.cabinetId = cabinetId;
	}

	public Long getPreventionCPN() {
		return preventionCPN;
	}

	public void setPreventionCPN(Long preventionCPN) {
		this.preventionCPN = preventionCPN;
	}

	public Long getCtTbHivCt() {
		return ctTbHivCt;
	}

	public void setCtTbHivCt(Long ctTbHivCt) {
		this.ctTbHivCt = ctTbHivCt;
	}

	public Long getCtMDC() {
		return ctMDC;
	}

	public void setCtMDC(Long ctMDC) {
		this.ctMDC = ctMDC;
	}

	public Long getCtStiAdults() {
		return ctStiAdults;
	}

	public void setCtStiAdults(Long ctStiAdults) {
		this.ctStiAdults = ctStiAdults;
	}



	public double getFormacao() {
		return formacao;
	}
	
	


	public Long getMentorship_id() {
		return mentorship_id;
	}



	public void setMentorship_id(Long mentorship_id) {
		this.mentorship_id = mentorship_id;
	}



	public int getInstalacoes() {
		return instalacoes;
	}



	public void setInstalacoes(int instalacoes) {
		this.instalacoes = instalacoes;
	}



	public int getSeguranca() {
		return seguranca;
	}



	public void setSeguranca(int seguranca) {
		this.seguranca = seguranca;
	}



	public int getPretestagem() {
		return pretestagem;
	}



	public void setPretestagem(int pretestagem) {
		this.pretestagem = pretestagem;
	}



	public int getTestagem() {
		return testagem;
	}



	public void setTestagem(int testagem) {
		this.testagem = testagem;
	}



	public int getPostestagem() {
		return postestagem;
	}



	public void setPostestagem(int postestagem) {
		this.postestagem = postestagem;
	}



	public int getAvaliacao() {
		return avaliacao;
	}



	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}



	public int getTotal() {
		return total;
	}



	public void setTotal(int total) {
		this.total = total;
	}



	public Long getInd_11061() {
		return ind_11061;
	}



	public void setInd_11061(Long ind_11061) {
		this.ind_11061 = ind_11061;
	}



	public Long getInd_11011() {
		return ind_11011;
	}



	public void setInd_11011(Long ind_11011) {
		this.ind_11011 = ind_11011;
	}



	public Long getInd_11031() {
		return ind_11031;
	}



	public void setInd_11031(Long ind_11031) {
		this.ind_11031 = ind_11031;
	}



	public Long getInd_11041() {
		return ind_11041;
	}



	public void setInd_11041(Long ind_11041) {
		this.ind_11041 = ind_11041;
	}



	public Long getInd_11043() {
		return ind_11043;
	}



	public void setInd_11043(Long ind_11043) {
		this.ind_11043 = ind_11043;
	}



	public Long getInd_11073() {
		return ind_11073;
	}



	public void setInd_11073(Long ind_11073) {
		this.ind_11073 = ind_11073;
	}



	public Long getInd_42() {
		return ind_42;
	}



	public void setInd_42(Long ind_42) {
		this.ind_42 = ind_42;
	}



	public Long getInd_10043() {
		return ind_10043;
	}



	public void setInd_10043(Long ind_10043) {
		this.ind_10043 = ind_10043;
	}



	public Long getInd_10045() {
		return ind_10045;
	}



	public void setInd_10045(Long ind_10045) {
		this.ind_10045 = ind_10045;
	}



	public Long getInd_04071() {
		return ind_04071;
	}



	public void setInd_04071(Long ind_04071) {
		this.ind_04071 = ind_04071;
	}



	public Long getInd_04073() {
		return ind_04073;
	}



	public void setInd_04073(Long ind_04073) {
		this.ind_04073 = ind_04073;
	}



	public Long getInd_04041() {
		return ind_04041;
	}



	public void setInd_04041(Long ind_04041) {
		this.ind_04041 = ind_04041;
	}



	public Long getInd_04061() {
		return ind_04061;
	}



	public void setInd_04061(Long ind_04061) {
		this.ind_04061 = ind_04061;
	}



	public Long getInd_15051() {
		return ind_15051;
	}



	public void setInd_15051(Long ind_15051) {
		this.ind_15051 = ind_15051;
	}



	public Long getInd_06044() {
		return ind_06044;
	}



	public void setInd_06044(Long ind_06044) {
		this.ind_06044 = ind_06044;
	}



	public Long getInd_02041() {
		return ind_02041;
	}



	public void setInd_02041(Long ind_02041) {
		this.ind_02041 = ind_02041;
	}



	public Long getInd_01102() {
		return ind_01102;
	}



	public void setInd_01102(Long ind_01102) {
		this.ind_01102 = ind_01102;
	}



	public Long getInd_01031() {
		return ind_01031;
	}



	public void setInd_01031(Long ind_01031) {
		this.ind_01031 = ind_01031;
	}



	public Long getInd_01142() {
		return ind_01142;
	}



	public void setInd_01142(Long ind_01142) {
		this.ind_01142 = ind_01142;
	}



	public Long getInd_02063() {
		return ind_02063;
	}



	public void setInd_02063(Long ind_02063) {
		this.ind_02063 = ind_02063;
	}



	public Long getInd_01116() {
		return ind_01116;
	}



	public void setInd_01116(Long ind_01116) {
		this.ind_01116 = ind_01116;
	}



	public Long getInd_02071() {
		return ind_02071;
	}



	public void setInd_02071(Long ind_02071) {
		this.ind_02071 = ind_02071;
	}



	public Long getInd_02021() {
		return ind_02021;
	}



	public void setInd_02021(Long ind_02021) {
		this.ind_02021 = ind_02021;
	}



	public Long getInd_02023() {
		return ind_02023;
	}



	public void setInd_02023(Long ind_02023) {
		this.ind_02023 = ind_02023;
	}



	public Long getInd_03029() {
		return ind_03029;
	}



	public void setInd_03029(Long ind_03029) {
		this.ind_03029 = ind_03029;
	}



	public Long getInd_03011() {
		return ind_03011;
	}



	public void setInd_03011(Long ind_03011) {
		this.ind_03011 = ind_03011;
	}



	public Long getInd_03013() {
		return ind_03013;
	}



	public void setInd_03013(Long ind_03013) {
		this.ind_03013 = ind_03013;
	}



	public Long getInd_05012() {
		return ind_05012;
	}



	public void setInd_05012(Long ind_05012) {
		this.ind_05012 = ind_05012;
	}



	public Long getInd_05031() {
		return ind_05031;
	}



	public void setInd_05031(Long ind_05031) {
		this.ind_05031 = ind_05031;
	}



	public Long getInd_05061() {
		return ind_05061;
	}



	public void setInd_05061(Long ind_05061) {
		this.ind_05061 = ind_05061;
	}



	public Long getInd_05052() {
		return ind_05052;
	}



	public void setInd_05052(Long ind_05052) {
		this.ind_05052 = ind_05052;
	}



	public Long getInd_05054() {
		return ind_05054;
	}



	public void setInd_05054(Long ind_05054) {
		this.ind_05054 = ind_05054;
	}



	public Long getInd_05057() {
		return ind_05057;
	}



	public void setInd_05057(Long ind_05057) {
		this.ind_05057 = ind_05057;
	}


	public Long getInd_04077() {
		return ind_04077;
	}


	public void setInd_04077(Long ind_04077) {
		this.ind_04077 = ind_04077;
	}


	public Long getInd_08051() {
		return ind_08051;
	}


	public void setInd_08051(Long ind_08051) {
		this.ind_08051 = ind_08051;
	}


	public Long getInd_030211() {
		return ind_030211;
	}


	public void setInd_030211(Long ind_030211) {
		this.ind_030211 = ind_030211;
	}


	public Long getInd_030213() {
		return ind_030213;
	}


	public void setInd_030213(Long ind_030213) {
		this.ind_030213 = ind_030213;
	}


	public Long getInd_19051() {
		return ind_19051;
	}


	public void setInd_19051(Long ind_19051) {
		this.ind_19051 = ind_19051;
	}


	public Long getInd_19015() {
		return ind_19015;
	}


	public void setInd_19015(Long ind_19015) {
		this.ind_19015 = ind_19015;
	}
	
	

	
	
}
