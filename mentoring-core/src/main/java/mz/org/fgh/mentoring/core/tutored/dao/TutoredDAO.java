/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface TutoredDAO extends GenericDAO<Tutored, Long> {

	String NAME = "mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO";

	class QUERY {
		public static final String fetchByUser = "SELECT t FROM Tutored t INNER JOIN FETCH t.career WHERE t.createdBy = :userUuid AND t.lifeCycleStatus = :lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String fetchByUser = "Tutored.findByUser";
	}

	List<Tutored> findBySelectedFilter(final String uuid, final String code, final String name, final String surname,
			String phoneNumber, final String tutored, LifeCycleStatus lifeCycleStatus);

	List<Tutored> fetchByUser(final String userUuid, final LifeCycleStatus lifeCycleStatus);
}
