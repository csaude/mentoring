/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.tutorando.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.tutorando.model.Tutorando;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface TutorandoDAO extends GenericDAO<Tutorando, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO";
}
