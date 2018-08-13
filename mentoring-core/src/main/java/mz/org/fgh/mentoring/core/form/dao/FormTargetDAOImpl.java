/**
 *
 */
package mz.org.fgh.mentoring.core.form.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Repository(FormTargetDAOImpl.NAME)
public class FormTargetDAOImpl extends GenericDAOImpl<FormTarget, Long> implements FormTargetDAO {

	public static final String NAME = "mz.org.fgh.mentoring.core.form.dao.FormTargetDAOImpl";

	@Override
	public List<FormTarget> findByTutor(final Tutor tutor, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(FormTargetDAO.QUERY_NAME.findByTutor,
		        new ParamBuilder().add("tutorUuid", tutor.getUuid()).add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
