/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */

public interface TutorDAO extends GenericDAO<Tutor, Long> {

	String NAME = "mz.org.fgh.mentoring.core.tutor.dao.TutorDAO";

	class QUERY {
		public static final String fetchByUuid = "SELECT t FROM Tutor t INNER JOIN FETCH t.career c WHERE t.uuid = :uuid";
	}

	class QUERY_NAME {
		public static final String fetchByUuid = "Tutor.fetchByUuid";
	}

	List<Tutor> findBySelectedFilter(final String code, final String name, final String surname,
			final String phoneNumber, CareerType careerType, LifeCycleStatus lifeCycleStatus);

	Tutor fetchByUuid(final String uuid);
}
