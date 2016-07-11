/*
 * MozView Technologies, Lda. 2010 - 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface MentorshipDAO extends GenericDAO<Mentorship, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO";
}
