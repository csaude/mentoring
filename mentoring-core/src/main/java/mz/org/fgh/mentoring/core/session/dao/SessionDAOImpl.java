/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Repository(SessionDAO.NAME)
public class SessionDAOImpl extends GenericDAOImpl<Session, Long> implements SessionDAO {
	
	private static final String DATE_PATTERN = "yyyy-MM-dd";

	@Override
	public List<PerformedSession> findBySelectedFilter(final District district, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);
		final Root<Session> root = createQuery.from(Session.class);
		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("form").join("programmaticArea");
		mentorships.join("healthFacility").join("district");
		mentorships.join("tutor");
		final Join<Mentorship, Cabinet> cabinets = mentorships.join("cabinet", JoinType.LEFT);

		createQuery.select(criteriaBuilder.construct(PerformedSession.class, mentorships.get("form").get("name"),
		        criteriaBuilder.countDistinct(root.get("id"))));

		final List<Predicate> predicates = new ArrayList<>();

		if (district != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("healthFacility").get("district").get("uuid"),
			        district.getUuid()));
		}

		if (healthFacility != null) {
			predicates.add(
			        criteriaBuilder.equal(mentorships.get("healthFacility").get("uuid"), healthFacility.getUuid()));
		}

		if (programmaticArea != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("programmaticArea").get("uuid"),
			        programmaticArea.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (cabinet != null) {
			predicates.add(criteriaBuilder.equal(cabinets.get("uuid"), cabinet.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(mentorships.get("form").get("id"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("form").get("name")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<SubmitedSessions> findNumberOfSessionsPerDistrict(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.findNumberOfSessionsPerDistrict,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process(), SubmitedSessions.class);
	}

	@Override
	public List<PerformedSession> findByTutorAndForm(final Tutor tutor, final Form form, final LocalDate startDate,
	        final LocalDate endDate) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();

		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);

		final Root<Session> root = createQuery.from(Session.class);

		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("tutor");
		mentorships.join("form");
		mentorships.join("healthFacility").join("district");

		createQuery.select(criteriaBuilder.construct(PerformedSession.class,
		        mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"),
		        criteriaBuilder.countDistinct(root.get("id"))));

		final List<Predicate> predicates = new ArrayList<>();

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), LifeCycleStatus.ACTIVE));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("healthFacility").get("district").get("district")),
		        criteriaBuilder.asc(mentorships.get("healthFacility").get("healthFacility")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<Session> findWithDuplicatedUuids(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.findWithDuplicatedUuids,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Session> fetchSessionsByUuid(final String sessionUuid, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.fetchSessionsByUuid,
		        new ParamBuilder().add("sessionUuid", sessionUuid).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<PerformedSession> findBySelectedFilterList(final District district, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);
		final Root<Session> root = createQuery.from(Session.class);
		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("form").join("programmaticArea");
		mentorships.join("healthFacility").join("district");
		mentorships.join("tutor").join("career");
		final Join<Mentorship, Cabinet> cabinets = mentorships.join("cabinet", JoinType.LEFT);

		createQuery.select(criteriaBuilder.construct(PerformedSession.class, mentorships.get("form").get("name"),
		        root.get("createdAt"), root.get("performedDate"),
		        mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"), cabinets.get("name"),
		        mentorships.get("tutor").get("name"), mentorships.get("tutor").get("surname"),
		        mentorships.get("tutor").get("career").get("position"), root.get("startDate"), root.get("endDate"),
		        root.get("status")));

		final List<Predicate> predicates = new ArrayList<>();

		if (district != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("healthFacility").get("district").get("uuid"),
			        district.getUuid()));
		}

		if (healthFacility != null) {
			predicates.add(
			        criteriaBuilder.equal(mentorships.get("healthFacility").get("uuid"), healthFacility.getUuid()));
		}

		if (programmaticArea != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("programmaticArea").get("uuid"),
			        programmaticArea.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (cabinet != null) {
			predicates.add(criteriaBuilder.equal(cabinets.get("uuid"), cabinet.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(root.get("id"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("form").get("name")),
		        criteriaBuilder.asc(root.get("performedDate")),
		        criteriaBuilder.asc(mentorships.get("healthFacility").get("district").get("district")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	/**
	 *
	 */
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PerformedSession> findBySelectedFilterHTS(LocalDate startDate,LocalDate endDate) {

		final StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT d.DISTRICT AS 'districtName', h.HEALTH_FACILITY AS 'healthFacility', DATE_FORMAT(s.PERFORMED_DATE,'%d-%m-%Y') AS 'performedDate', CONCAT(t.NAME,' ',t.SURNAME) AS 'tutorName', CONCAT(tr.NAME,' ',tr.SURNAME) AS 'tutoredName', c.NAME AS 'cabinet', CASE m.DOOR WHEN 'P1' THEN '1' WHEN 'P2' THEN '2' WHEN 'P3' THEN '3' WHEN 'P4' THEN '4' END AS 'door', CASE m.TIME_OF_DAY WHEN 'DAY' THEN 'Dia' WHEN 'LATE_NIGHT' THEN 'Tarde/Noite' ELSE '' END AS 'timeOfDay', atendidos.NUMERIC_VALUE AS 'atendidos', previos.NUMERIC_VALUE AS 'previos', testados.NUMERIC_VALUE AS  'testados', positivos.NUMERIC_VALUE AS 'positivos', inscritos.NUMERIC_VALUE AS 'inscritos',DATE_FORMAT(s.CREATED_AT,'%d-%m-%Y %H:%i:%s') AS 'createdAt' FROM SESSIONS s INNER JOIN MENTORSHIPS m ON s.ID=m.SESSION_ID INNER JOIN HEALTH_FACILITIES h ON m.HEALTH_FACILITY_ID=h.ID INNER JOIN TUTORS t ON m.TUTOR_ID=t.ID INNER JOIN TUTOREDS tr ON m.TUTORED_ID=tr.ID INNER JOIN CABINETS c ON m.CABINET_ID=c.ID INNER JOIN FORMS f ON m.FORM_ID=f.ID INNER JOIN DISTRICTS d ON h.DISTRICT_ID = d.ID LEFT JOIN (SELECT MENTORSHIP_ID, NUMERIC_VALUE FROM ANSWERS a,QUESTIONS q WHERE a.QUESTION_ID=q.ID AND q.CODE='MTQ00000588') atendidos ON atendidos.MENTORSHIP_ID=m.ID LEFT JOIN (SELECT MENTORSHIP_ID, NUMERIC_VALUE FROM ANSWERS a,QUESTIONS q WHERE a.QUESTION_ID=q.ID AND q.CODE='MTQ00000589') previos ON previos.MENTORSHIP_ID=m.ID LEFT JOIN (SELECT MENTORSHIP_ID, NUMERIC_VALUE FROM ANSWERS a,QUESTIONS q WHERE a.QUESTION_ID=q.ID AND q.CODE='MTQ00000590') testados ON testados.MENTORSHIP_ID=m.ID LEFT JOIN (SELECT MENTORSHIP_ID, NUMERIC_VALUE FROM ANSWERS a,QUESTIONS q WHERE a.QUESTION_ID=q.ID AND q.CODE='MTQ00000591') positivos ON positivos.MENTORSHIP_ID=m.ID LEFT JOIN (SELECT MENTORSHIP_ID, NUMERIC_VALUE FROM ANSWERS a,QUESTIONS q WHERE a.QUESTION_ID=q.ID AND q.CODE='MTQ00000592') inscritos ON inscritos.MENTORSHIP_ID=m.ID  WHERE s.LIFE_CYCLE_STATUS='ACTIVE' AND m.LIFE_CYCLE_STATUS='ACTIVE' AND s.PERFORMED_DATE>='"+startDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))+"' AND s.PERFORMED_DATE<='"+endDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))+"' AND f.CODE='MT00000045' ORDER BY 1,2,3 ASC");

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString());
		
		List<Object[]> performedSessionsHTS = query.getResultList();
		 
		List<PerformedSession> performedSessions= new ArrayList<PerformedSession>(0);
		
		for (Object[] ps : performedSessionsHTS) {
			PerformedSession p= new PerformedSession(
					ps[0].toString(), 
					ps[1].toString(), 
					ps[2].toString(), 
					ps[3].toString(), 
					ps[4].toString(), 
					ps[5].toString(), 
					ps[6].toString(), 
					ps[7].toString(), 
					Long.valueOf(ps[8].toString()),
					Long.valueOf(ps[9].toString()), 
					Long.valueOf(ps[10].toString()), 
					Long.valueOf(ps[11].toString()), 
					Long.valueOf(ps[12].toString()), 
					ps[13].toString());
			
			performedSessions.add(p);
			
		}

		return performedSessions;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerformedSession> findBySelectedFilterNarrative(LocalDate startDate, LocalDate endDate) {
		
		final StringBuilder nativeQuery = new StringBuilder();
		
		String start=startDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		String end=endDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN));
		
		nativeQuery.append("SELECT d.PROVINCE, (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, CABINETS c WHERE m.FORM_ID=f.ID AND m.CABINET_ID=c.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND c.ID IN (12) AND f.CODE IN ('MT00000001','MT00000033') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "') AS 'saaj', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000291','MTQ00000292','MTQ00000293') AND f.CODE IN ('MT00000033','MT00000034') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO'))) AS 'htcLink', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f WHERE m.FORM_ID=f.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND f.CODE IN ('MT00000036','MT00000038','MT00000039') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "') AS 'smi', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000312','MTQ00000313','MTQ00000314') AND f.CODE IN ('MT00000035') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO')) AND m.HEALTH_FACILITY_ID=128) AS 'stiAdultsPrison', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f WHERE m.FORM_ID=f.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND f.CODE IN ('MT00000035') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND m.HEALTH_FACILITY_ID=128) AS 'ctAdultsPrison', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f WHERE m.FORM_ID=f.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND f.CODE IN ('MT00000035') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "') AS 'ctAdults', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000417','MTQ00000418','MTQ00000419','MTQ00000420','MTQ00000421','MTQ00000422','MTQ00000423','MTQ00000424','MTQ00000425','MTQ00000426','MTQ00000427','MTQ00000428','MTQ00000429','MTQ00000430','MTQ00000431','MTQ00000432','MTQ00000433') AND f.CODE IN ('MT00000040') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO'))) AS 'apss', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000317','MTQ00000318','MTQ00000319','MTQ00000521') AND f.CODE IN ('MT00000035','MT00000036','MT00000037','MT00000038','MT00000039','MT00000043') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO'))) AS 'adultVl', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000307','MTQ00000308','MTQ00000309','MTQ00000310','MTQ00000311','MTQ00000376') AND f.CODE IN ('MT00000035','MT00000037') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO'))) AS 'tbHiv', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000328') AND f.CODE IN ('MT00000035') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND EXISTS ( SELECT a2.ID FROM ANSWERS a2 WHERE a2.MENTORSHIP_ID=m.ID AND a2.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO'))) AS 'tpi', (SELECT COUNT(DISTINCT(m.SESSION_ID)) FROM MENTORSHIPS m, FORMS f, QUESTIONS q, ANSWERS a WHERE m.FORM_ID=f.ID AND a.QUESTION_ID=q.ID AND m.LIFE_CYCLE_STATUS='ACTIVE' AND m.ID=a.MENTORSHIP_ID AND q.CODE IN ('MTQ00000305','MTQ00000306') AND f.CODE IN ('MT00000035') AND m.PERFORMED_DATE>='" + start + "' AND m.PERFORMED_DATE<='" + end + "' AND a.TEXT_VALUE IN ('COMPETENTE','NAO SATISFATORIO')) AS 'nutrition' FROM DISTRICTS d GROUP BY PROVINCE");

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString());
		
		List<Object[]> performedSessionsHTS = query.getResultList();
		 
		List<PerformedSession> performedSessions= new ArrayList<PerformedSession>(0);
		
		for (Object[] ps : performedSessionsHTS) {
			PerformedSession p= new PerformedSession(
					ps[0].toString(),
					Long.valueOf(ps[1].toString()), 
					Long.valueOf(ps[2].toString()),
					Long.valueOf(ps[3].toString()),
					Long.valueOf(ps[4].toString()),
					Long.valueOf(ps[5].toString()),
					Long.valueOf(ps[6].toString()),
					Long.valueOf(ps[7].toString()),
					Long.valueOf(ps[8].toString()),
					Long.valueOf(ps[9].toString()),
					Long.valueOf(ps[10].toString()),
					Long.valueOf(ps[11].toString()));
			
			performedSessions.add(p);
			
		}

		return performedSessions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerformedSession> findBySelectedFilterLast12Months() {
		
final StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("SELECT CONCAT( MONTHNAME(m.PERFORMED_DATE) ,' ',YEAR(m.PERFORMED_DATE)) AS 'monthName', CONCAT(YEAR(m.PERFORMED_DATE),'',DATE_FORMAT(m.PERFORMED_DATE,'%m')) AS 'month', COUNT(DISTINCT(SESSION_ID)) AS sessions FROM MENTORSHIPS m WHERE m.LIFE_CYCLE_STATUS='ACTIVE'  GROUP BY CONCAT( MONTH(m.PERFORMED_DATE) ,' ',YEAR(m.PERFORMED_DATE)) ORDER BY CONCAT(YEAR(m.PERFORMED_DATE),'', DATE_FORMAT(m.PERFORMED_DATE,'%m')) DESC LIMIT 12");

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString());
		
		List<Object[]> performedSessionsHTS = query.getResultList();
		 
		List<PerformedSession> performedSessions= new ArrayList<PerformedSession>(0);
		
		for (Object[] ps : performedSessionsHTS) {
			PerformedSession p= new PerformedSession(
					ps[0].toString(),
					ps[1].toString(), 
					Long.valueOf(ps[2].toString()));
			
			performedSessions.add(p);
			
		}

		return performedSessions;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerformedSession> findByTutor(Tutor tutor, LocalDate startDate, LocalDate endDate) {
		
final StringBuilder nativeQuery = new StringBuilder();
		
		nativeQuery.append("SELECT f.NAME AS 'formName', COUNT(DISTINCT(m.SESSION_ID)) AS 'totalPerformed' FROM MENTORSHIPS m,FORMS f,TUTORS t WHERE m.PERFORMED_DATE>='"+startDate+"' AND m.PERFORMED_DATE<='"+endDate+"' AND m.FORM_ID=f.ID AND m.TUTOR_ID=t.ID AND t.uuid='"+tutor.getUuid()+"' AND m.LIFE_CYCLE_STATUS='ACTIVE' GROUP BY f.ID");

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString());
		
		List<Object[]> performedSessionsHTS = query.getResultList();
		 
		List<PerformedSession> performedSessions= new ArrayList<PerformedSession>(0);
		
		for (Object[] ps : performedSessionsHTS) {
			PerformedSession p= new PerformedSession(
					ps[0].toString(),
					Long.valueOf(ps[1].toString()));
			
			performedSessions.add(p);
			
		}

		return performedSessions;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PerformedSession> findBySelectedFilterIndicators(LocalDate startDate, LocalDate endDate) {
		final StringBuilder nativeQuery = new StringBuilder();
		nativeQuery.append("SELECT f.NAME,COUNT(i.ID) AS 'total' FROM INDICATORS i, FORMS f WHERE i.FORM_ID=f.ID AND i.LIFE_CYCLE_STATUS='ACTIVE' AND i.PERFORMED_DATE >='"+startDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))+"' AND i.PERFORMED_DATE <='"+endDate.format(DateTimeFormatter.ofPattern(DATE_PATTERN))+"' AND f.NAME LIKE '%PROCEDIMENTO OPERACIONAL%' GROUP BY i.FORM_ID ORDER BY 1 ASC" );

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString());
		
		List<Object[]> performedSessionsHTS = query.getResultList();
		 
		List<PerformedSession> performedSessions= new ArrayList<PerformedSession>(0);
		
		for (Object[] ps : performedSessionsHTS) {
			PerformedSession p= new PerformedSession(
					ps[0].toString(), 
					Long.valueOf(ps[1].toString()));
			
			performedSessions.add(p);
			
		}

		return performedSessions;
	}
}
