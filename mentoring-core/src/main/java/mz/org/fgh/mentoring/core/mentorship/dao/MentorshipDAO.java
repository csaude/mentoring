/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface MentorshipDAO extends GenericDAO<Mentorship, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO";

	public static class QUERY {
		public static final String countMentorshipByHealthFacility = "SELECT m.healthFacility, COUNT(m.id) FROM Mentorship m WHERE m.healthFacility=:healthFacility AND m.lifeCycleStatus=:lifeCycleStatus GROUP BY m.healthFacility";

	}

	public static class QUERY_NAME {
		public static final String countMentorshipByHealthFacility = "Mentorship.countMentorshipByHealthFacility";

	}

	public List<Mentorship> findBySelectedFilter(String code, final String tutor, final String tutored,
			LifeCycleStatus lifeCycleStatus);

	public List<Mentorship> countMentorshipByHealthFacility(HealthFacility healthFacility,
			final LifeCycleStatus lifeCycleStatus);

}
