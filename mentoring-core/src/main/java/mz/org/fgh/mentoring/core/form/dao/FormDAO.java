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

	public static class QUERY {
		public static final String fetchByFormId = "SELECT f FROM Form f INNER JOIN FETCH f.formQuestions fq INNER JOIN FETCH fq.question WHERE f.id = :formId";
	}

	public static class QUERY_NAME {
		public static final String fetchByFormId = "Form.fetchByFormId";
	}

	Form fetchByFormId(final Long formId);
}
