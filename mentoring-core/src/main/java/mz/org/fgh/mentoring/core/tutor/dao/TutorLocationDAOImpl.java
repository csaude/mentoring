/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutored.dao.TutoredDAO;
import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

import java.util.List;

/**
 * @author Stélio Moiane
 *
 */
@Repository(TutorLocationDAOImpl.NAME)
public class TutorLocationDAOImpl extends GenericDAOImpl<TutorLocation, Long> implements TutorLocationDAO {

	public static final String NAME = "mz.org.fgh.mentoring.core.tutor.dao.TutorLocationDAOImpl";

	@Override
	public List<TutorLocation> findByTutorId(Long id, LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(QUERY_NAME.findByTutorId, new ParamBuilder().add("id", id).add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
