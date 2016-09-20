/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface TutoredDAO extends GenericDAO<Tutored, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.tutorando.dao.TutorandoDAO";

	public List<Tutored> findBySelectedFilter(final String code, final String name, final String surname, String phoneNumber, final String tutoredCode,
			LifeCycleStatus lifeCycleStatus);
}
