package mz.org.fgh.mentoring.core.tutorprogramaticarea.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutorprogramaticarea.model.TutorProgramaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorProgramaticAreaService {

	String NAME = "mz.org.fgh.mentoring.core.tutorprogramaticarea.service.TutorProgramaticAreaService";

	TutorProgramaticArea createTutorProgramaticArea(final UserContext userContext,
			final TutorProgramaticArea tutorProgramaticArea) throws BusinessException;

	TutorProgramaticArea updateTutorProgramaticArea(final UserContext userContext,
			final TutorProgramaticArea tutorProgramaticArea) throws BusinessException;

}
