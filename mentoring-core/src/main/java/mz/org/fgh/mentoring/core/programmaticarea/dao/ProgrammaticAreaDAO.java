/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface ProgrammaticAreaDAO extends GenericDAO<ProgrammaticArea, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.programmaticarea.dao.ProgrammaticAreaDAO";
}
