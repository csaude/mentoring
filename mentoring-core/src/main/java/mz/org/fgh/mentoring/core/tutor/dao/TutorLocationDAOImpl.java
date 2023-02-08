/**
 *
 */
package mz.org.fgh.mentoring.core.tutor.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutor.model.TutorLocation;

/**
 * @author Stélio Moiane
 *
 */
@Repository(TutorLocationDAOImpl.NAME)
public class TutorLocationDAOImpl extends GenericDAOImpl<TutorLocation, Long> implements TutorLocationDAO {

	public static final String NAME = "mz.org.fgh.mentoring.core.tutor.dao.TutorLocationDAOImpl";

}
