/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.session.model.Session;

/**
 * @author Stélio Moiane
 *
 */
public interface SessionDAO extends GenericDAO<Session, Long> {

	public static final String NAME = "mz.org.fgh.mentoring.core.session.dao.SessionDAO";

}
