package mz.org.fgh.mentoring.core.tutortutored.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.tutortutored.model.TutorTudored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutorTutoredService {

	String NAME = "mz.org.fgh.mentoring.core.tutortutored.service.TutorTutoredService";

	TutorTudored createTutorTutored(final UserContext userContext, final TutorTudored tutorTudored)
			throws BusinessException;

	TutorTudored updateTutorTutored(final UserContext userContext, final TutorTudored tutorTudored)
			throws BusinessException;

}
