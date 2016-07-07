/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutored.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface TutoredDAO extends GenericDAO<Tutored, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO";
}
