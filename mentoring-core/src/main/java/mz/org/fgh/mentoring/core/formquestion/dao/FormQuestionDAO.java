/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormQuestionDAO extends GenericDAO<FormQuestion, Long> {
	
	public String NAME = "mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO";
}
