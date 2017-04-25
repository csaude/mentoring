package mz.org.fgh.mentoring.core.tutortutored.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutortutored.model.TutorTudored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutorTutoredDao.NAME)
public class TutorTutoredDaoImpl extends GenericDAOImpl<TutorTudored, Long> implements TutorTutoredDao {

}
