/**
 *
 */
package mz.org.fgh.mentoring.core.session.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.session.dao.SessionDAO;
import mz.org.fgh.mentoring.core.session.model.Session;

/**
 * @author Stélio Moiane
 *
 */
@Service(SessionServiceImpl.NAME)
public class SessionServiceImpl extends AbstractService implements SessionService {

	@Inject
	private SessionDAO sessionDAO;

	public static final String NAME = "mz.org.fgh.mentoring.core.session.service.SessionServiceImpl";

	@Override
	public Session createSession(final UserContext userContext, final Session session) throws BusinessException {
		return this.sessionDAO.create(userContext.getUuid(), session);
	}
}
