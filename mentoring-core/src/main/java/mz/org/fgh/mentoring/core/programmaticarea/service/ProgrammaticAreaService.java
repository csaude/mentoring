/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface ProgrammaticAreaService {

	String NAME = "mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaService";

	ProgrammaticArea createProgrammaticArea(final UserContext userContext, final ProgrammaticArea programmaticArea) throws BusinessException;

	ProgrammaticArea updateProgrammaticArea(final UserContext userContext, final ProgrammaticArea programmaticArea) throws BusinessException;
}
