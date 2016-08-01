/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface ProgrammaticAreaDAO extends GenericDAO<ProgrammaticArea, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO";

	public List<ProgrammaticArea> findBySelectedFilter(final UserContext userContext, final String code,
			final String name, final LifeCycleStatus lifeCycleStatus);
}
