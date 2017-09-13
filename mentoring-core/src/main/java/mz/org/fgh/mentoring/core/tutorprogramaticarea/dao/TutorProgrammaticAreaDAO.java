package mz.org.fgh.mentoring.core.tutorprogramaticarea.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorProgrammaticAreaDAO extends GenericDAO<TutorProgrammaticArea, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.tutortutored.dao.TutorProgrammaticAreaDAO";

	public class QUERY {
		public static final String findByTutorAndProgrammaticArea = "SELECT tpa FROM TutorProgrammaticArea tpa INNER JOIN tpa.tutor t INNER JOIN tpa.programmaticArea pa WHERE t.uuid = :tutorUuid AND pa.uuid = :programmaticAreaUuid AND tpa.lifeCycleStatus = :lifeCycleStatus";
	}

	public class QUERY_NAME {
		public static final String findByTutorAndProgrammaticArea = "TutorProgrammaticArea.findByTutorAndProgrammaticArea";
	}

	public TutorProgrammaticArea findByTutorAndProgrammaticArea(final Tutor tutor,
	        final ProgrammaticArea programmaticArea, LifeCycleStatus lifeCycleStatus);
}
