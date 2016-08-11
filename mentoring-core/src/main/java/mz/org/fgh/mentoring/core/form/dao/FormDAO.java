/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormDAO extends GenericDAO<Form, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.form.dao.FormDAO";

	public static class QUERY {
		public static final String fetchByFormId = "SELECT f FROM Form f INNER JOIN FETCH f.formQuestions fq INNER JOIN FETCH fq.question WHERE f.id = :formId";
		public static final String findAll = "SELECT f FROM Form f WHERE f.lifeCycleStatus = :lifeCycleStatus";

	}

	public static class QUERY_NAME {
		public static final String fetchByFormId = "Form.fetchByFormId";
		public static final String findAll = "Form.findAll";

	}

	Form fetchByFormId(final Long formId);
	
	public List<Form> findBySelectedFilter(final String code, final String name, final ProgrammaticArea programaticArea,
			LifeCycleStatus lifeCycleStatus);
}
