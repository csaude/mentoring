/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Repository(TutorDAO.NAME)
public class TutorDAOImpl extends GenericDAOImpl<Tutor, Long> implements TutorDAO {

	@Override
	public List<Tutor> findAll(final LifeCycleStatus lifeCycleStatus) {

		return this.findByNamedQuery(TutorDAO.QUERY_NAME.findAll,
				new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());

	}

}
