/**
 *
 */
package mz.org.fgh.mentoring.core.form.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.dao.FormTargetDAO;
import mz.org.fgh.mentoring.core.form.model.FormTarget;

/**
 * @author Stélio Moiane
 *
 */
@Service(FormTargetServiceImpl.NAME)
public class FormTargetServiceImpl extends AbstractService implements FormTargetService {

	public static final String NAME = "mz.org.fgh.mentoring.core.form.service.FormTargetServiceImpl";

	@Inject
	private FormTargetDAO formTargetDAO;

	@Override
	public FormTarget createFormTarget(final UserContext userContext, final FormTarget formTarget) {
		return this.formTargetDAO.create(userContext.getUuid(), formTarget);
	}
}
