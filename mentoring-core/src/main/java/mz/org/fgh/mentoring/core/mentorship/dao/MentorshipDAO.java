/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.mentorship.model.SubmitedSessions;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface MentorshipDAO extends GenericDAO<Mentorship, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO";

	public List<Mentorship> fetchBySelectedFilter(String code, final String tutorName, final String tutoredName,
			final String formName, final String healthFacility, final LifeCycleStatus lifeCycleStatus);

	class QUERY {
		public static final String findNumberOfSessionsPerHealthFacility = "SELECT hf.HEALTH_FACILITY, COUNT(m.ID) as TOTAL, MAX(m.CREATED_AT) as LAST_UPDATE FROM MENTORSHIPS m INNER JOIN HEALTH_FACILITIES hf on hf.ID = m.HEALTH_FACILITY WHERE m.LIFE_CYCLE_STATUS = :lifeCycleStatus GROUP BY hf.HEALTH_FACILITY ORDER BY hf.HEALTH_FACILITY";
	}

	class QUERY_NAME {
		public static final String findNumberOfSessionsPerHealthFacility = "SubmitedSessions.findNumberOfSessionsPerHealthFacility";
	}

	public List<SubmitedSessions> findNumberOfSessionsPerHealthFacility(LifeCycleStatus lifeCycleStatus);

}
