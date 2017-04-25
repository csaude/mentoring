package mz.org.fgh.mentoring.core.tutorprogramaticarea.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutorProgramaticAreaDao.NAME)
public class TutorProgramaticAreaDaoImpl extends GenericDAOImpl<TutorProgramaticArea, Long>
		implements TutorProgramaticAreaDao {
}
