/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.session.model.Session;

/**
 * @author Stélio Moiane
 *
 */
@Repository(SessionDAO.NAME)
public class SessionDAOImpl extends GenericDAOImpl<Session, Long> implements SessionDAO {

}
