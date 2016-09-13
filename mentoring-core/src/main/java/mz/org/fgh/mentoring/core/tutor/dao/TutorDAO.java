/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */

public interface TutorDAO extends GenericDAO<Tutor, Long> {

	String NAME = "mz.org.fgh.mentoring.core.tutor.dao.TutorDAO";

	public static class QUERY {
		public static final String findAll = "SELECT t FROM Tutor t WHERE t.lifeCycleStatus = :lifeCycleStatus";
	}

	public static class QUERY_NAME {

		public static final String findAll = "Tutor.findAll";

	}

	public List<Tutor> findBySelectedFilter(final String code, final String name, final String surname,
			final String phoneNumber, String carrer, LifeCycleStatus lifeCycleStatus);

}
