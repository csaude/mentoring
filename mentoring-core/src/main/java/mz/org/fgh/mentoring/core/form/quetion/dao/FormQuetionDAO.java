/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.quetion.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.form.quetion.model.FormQuetion;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormQuetionDAO extends GenericDAO<FormQuetion, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.form.quetion.dao.FormQuetionDAO";
}
