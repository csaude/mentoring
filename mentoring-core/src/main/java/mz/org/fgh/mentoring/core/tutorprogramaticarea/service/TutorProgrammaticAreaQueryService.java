package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorProgrammaticAreaQueryService {

	String NAME = "mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgrammaticAreaQueryService";

	TutorProgrammaticArea findTutorMapByTutorAndProgrammaticArea(final UserContext userContext, final Tutor tutor,
	        final ProgrammaticArea programmaticArea) throws BusinessException;

}
