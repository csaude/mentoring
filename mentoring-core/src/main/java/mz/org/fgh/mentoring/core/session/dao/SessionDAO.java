/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface SessionDAO extends GenericDAO<Session, Long> {

	String NAME = "mz.org.fgh.mentoring.core.session.dao.SessionDAO";

	class QUERY {
		public static final String findNumberOfSessionsPerDistrict = "SELECT NEW mz.org.fgh.mentoring.core.session.model.SubmitedSessions(d.district, pa.name, COUNT(s.id), MAX(s.createdAt)) FROM Session s INNER JOIN s.mentorships m INNER JOIN m.form f INNER JOIN f.programmaticArea pa INNER JOIN m.healthFacility hf INNER JOIN hf.district d WHERE s.lifeCycleStatus = :lifeCycleStatus GROUP BY d.district, pa.name ORDER BY d.district";
		public static final String findWithDuplicatedUuids = "SELECT s FROM Session s WHERE s.lifeCycleStatus = :lifeCycleStatus GROUP BY s.uuid HAVING COUNT(s.uuid) > 1";
		public static final String fetchSessionsByUuid = "SELECT DISTINCT (s) FROM Session s INNER JOIN FETCH s.mentorships m WHERE s.uuid = :sessionUuid AND s.lifeCycleStatus = :lifeCycleStatus ORDER BY s.createdAt DESC";
	}

	class QUERY_NAME {
		public static final String findNumberOfSessionsPerDistrict = "SubmitedSessions.findNumberOfSessionsPerDistrict";
		public static final String findWithDuplicatedUuids = "Session.findWithDuplicatedUuids";
		public static final String fetchSessionsByUuid = "Session.fetchSessionsByUuid";
	}

	List<PerformedSession> findBySelectedFilter(final District distric, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus);

	List<PerformedSession> findBySelectedFilterList(final District distric, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus);

	List<SubmitedSessions> findNumberOfSessionsPerDistrict(LifeCycleStatus lifeCycleStatus);

	List<PerformedSession> findByTutorAndForm(Tutor tutor, Form form, LocalDate startDate, LocalDate endDate);

	List<Session> findWithDuplicatedUuids(LifeCycleStatus lifeCycleStatus);

	List<Session> fetchSessionsByUuid(String sessionUuid, LifeCycleStatus lifeCycleStatus);
	
	List<PerformedSession> findBySelectedFilterHTS(final LocalDate startDate, final LocalDate endDate);
	
	List<PerformedSession> findBySelectedFilterNarrative(final LocalDate startDate, final LocalDate endDate);
	
	List<PerformedSession> findBySelectedFilterLast12Months();
	
	List<PerformedSession> findByTutor(Tutor tutor, LocalDate startDate, LocalDate endDate);
	
	List<PerformedSession> findBySelectedFilterIndicators(final LocalDate startDate, final LocalDate endDate);
}
