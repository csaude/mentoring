/**
 *
 */
package mz.org.fgh.mentoring.core.form.service;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.form.model.FormTarget;

/**
 * @author Stélio Moiane
 *
 */
public interface FormTargetService {

	FormTarget createFormTarget(final UserContext userContext, final FormTarget formTarget);

}
