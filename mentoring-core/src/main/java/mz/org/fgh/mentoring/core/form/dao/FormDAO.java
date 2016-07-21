/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.form.model.Form;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormDAO extends GenericDAO<Form, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.form.dao.FormDAO";
}
