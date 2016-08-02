/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
public interface ProgrammaticAreaQueryService {

	String NAME = "mz.org.fgh.mentoring.core.programmaticarea.service.ProgrammaticAreaQueryService";

	List<ProgrammaticArea> findProgrammaticAreasBySelectedFilter(final UserContext userContext, final String code,
			final String name) throws BusinessException;

}
