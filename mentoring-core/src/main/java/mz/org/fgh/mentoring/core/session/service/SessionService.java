/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.session.model.Session;

/**
 * @author Stélio Moiane
 *
 */
public interface SessionService {

	Session createSession(final UserContext userContext, final Session session) throws BusinessException;

}
