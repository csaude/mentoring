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
		public static final String fetchByUuid = 	"SELECT t " +
													"FROM Tutor t 	INNER JOIN FETCH t.career c " +
													"				INNER JOIN FETCH t.partner p " +
													"WHERE t.uuid = :uuid";
		public static final String fetchByEmail = "SELECT t FROM Tutor t INNER JOIN FETCH t.career INNER JOIN FETCH t.partner p WHERE t.email = :email AND t.lifeCycleStatus = :lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String fetchByUuid = "Tutor.fetchByUuid";
		public static final String fetchByEmail = "Tutor.findByEmail";
	}

	List<Tutor> findBySelectedFilter(final String code, final String name, final String surname,
	        final String phoneNumber, CareerType careerType, LifeCycleStatus lifeCycleStatus);
	
	List<Tutor> findBySelectedFilter(final String code, final String name, final String surname,
	        final String phoneNumber, final String partnerUuid, CareerType careerType, LifeCycleStatus lifeCycleStatus);

	Tutor fetchByUuid(final String uuid);

	Tutor fecthByEmail(final String email, final LifeCycleStatus lifeCycleStatus);
}
