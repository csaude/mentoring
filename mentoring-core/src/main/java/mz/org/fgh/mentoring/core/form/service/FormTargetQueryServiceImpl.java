/**
 *
 */
package mz.org.fgh.mentoring.core.form.service;

import static mz.org.fgh.mentoring.core.form.service.FormTargetQueryServiceImpl.NAME;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.dao.FormTargetDAO;
import mz.org.fgh.mentoring.core.form.model.FormTarget;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Service(NAME)
public class FormTargetQueryServiceImpl implements FormTargetQueryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.form.service.FormTargetQueryServiceImpl";

	@Inject
	private FormTargetDAO FormTargetDAO;

	@Override
	public List<FormTarget> findFormTargetByTutor(final Tutor tutor) throws BusinessException {
		return this.FormTargetDAO.findByTutor(tutor, LifeCycleStatus.ACTIVE);
	}

}
