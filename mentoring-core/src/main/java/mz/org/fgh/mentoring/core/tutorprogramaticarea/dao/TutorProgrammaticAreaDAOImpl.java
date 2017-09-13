package mz.org.fgh.mentoring.core.tutorprogramaticarea.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutorProgrammaticAreaDAO.NAME)
public class TutorProgrammaticAreaDAOImpl extends GenericDAOImpl<TutorProgrammaticArea, Long>
        implements TutorProgrammaticAreaDAO {

	@Override
	public TutorProgrammaticArea findByTutorAndProgrammaticArea(final Tutor tutor,
	        final ProgrammaticArea programmaticArea, final LifeCycleStatus lifeCycleStatus) {

		return this.findSingleByNamedQuery(TutorProgrammaticAreaDAO.QUERY_NAME.findByTutorAndProgrammaticArea,
		        new ParamBuilder().add("tutorUuid", tutor.getUuid())
		                .add("programmaticAreaUuid", programmaticArea.getUuid()).add("lifeCycleStatus", lifeCycleStatus)
		                .process());
	}
}
