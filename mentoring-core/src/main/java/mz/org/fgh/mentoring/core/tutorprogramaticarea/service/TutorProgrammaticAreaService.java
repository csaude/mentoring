package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorProgrammaticAreaService {

	String NAME = "mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgramaticAreaService";

	TutorProgrammaticArea mapTutorToProgramaticArea(final UserContext userContext,
			final TutorProgrammaticArea tutorProgramaticArea) throws BusinessException;

	TutorProgrammaticArea updateTutorProgramaticArea(final UserContext userContext,
			final TutorProgrammaticArea tutorProgramaticArea) throws BusinessException;

}
