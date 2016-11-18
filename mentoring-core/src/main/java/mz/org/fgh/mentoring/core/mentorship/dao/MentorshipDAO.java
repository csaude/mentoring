/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface MentorshipDAO extends GenericDAO<Mentorship, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.mentorship.dao.MentorshipDAO";
	public List<Mentorship> findBySelectedFilter(String code, final String tutor, final String tutored, LifeCycleStatus lifeCycleStatus);

}
