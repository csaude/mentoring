/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

import java.util.List;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorLocationDAO extends GenericDAO<TutorLocation, Long> {

    class QUERY {
        public static final String findByTutorId = "SELECT t FROM TutorLocation t INNER JOIN FETCH t.location WHERE t.tutor.id = :id AND t.lifeCycleStatus = :lifeCycleStatus";
    }

    class QUERY_NAME {
        public static final String findByTutorId = "TutorLocation.findByTutorId";
    }
    List<TutorLocation> findByTutorId(Long id, LifeCycleStatus lifeCycleStatus);
}
