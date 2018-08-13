/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
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

	List<PerformedSession> findBySelectedFilter(final District distric, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final LocalDate startDate,
	        final LocalDate endDate, final LifeCycleStatus lifeCycleStatus);

	class QUERY {
		public static final String findNumberOfSessionsPerDistrict = "SELECT NEW mz.org.fgh.mentoring.core.session.model.SubmitedSessions(d.district, pa.name, COUNT(s.id), MAX(s.createdAt)) FROM Session s INNER JOIN s.mentorships m INNER JOIN m.form f INNER JOIN f.programmaticArea pa INNER JOIN m.healthFacility hf INNER JOIN hf.district d WHERE s.lifeCycleStatus = :lifeCycleStatus GROUP BY d.district, pa.name ORDER BY d.district";
	}

	class QUERY_NAME {
		public static final String findNumberOfSessionsPerDistrict = "SubmitedSessions.findNumberOfSessionsPerDistrict";
	}

	List<SubmitedSessions> findNumberOfSessionsPerDistrict(LifeCycleStatus lifeCycleStatus);

	List<PerformedSession> findByTutorAndForm(Tutor tutor, Form form, LocalDate startDate, LocalDate endDate);
}
