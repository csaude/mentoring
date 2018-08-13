/**
 *
 */
package mz.org.fgh.mentoring.core.form.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface FormTargetDAO extends GenericDAO<FormTarget, Long> {

	class QUERY {
		public static final String findByTutor = "SELECT ft FROM FormTarget ft INNER JOIN FETCH ft.career c INNER JOIN FETCH ft.form f INNER JOIN FETCH f.programmaticArea INNER JOIN c.tutors t WHERE t.uuid = :tutorUuid AND ft.lifeCycleStatus =:lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String findByTutor = "FormTarget.findByTutor";
	}

	List<FormTarget> findByTutor(final Tutor tutor, final LifeCycleStatus lifeCycleStatus);
}
