/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.form.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormDAO extends GenericDAO<Form, Long> {

	public String NAME = "mz.org.fgh.mentoring.core.form.dao.FormDAO";

	public static class QUERY {
		public static final String fetchByFormId = "SELECT f FROM Form f INNER JOIN FETCH f.formQuestions fq INNER JOIN FETCH fq.question WHERE f.id = :formId";
		public static final String findAll = "SELECT f FROM Form f WHERE f.lifeCycleStatus = :lifeCycleStatus";
		public static final String findSampleIndicators = "SELECT f FROM Answer a INNER JOIN a.form f INNER JOIN a.question q INNER JOIN FETCH f.programmaticArea WHERE q.uuid IN (:questionUuids) AND f.lifeCycleStatus = :lifeCycleStatus GROUP BY f.uuid ORDER BY f.name";

	}

	public static class QUERY_NAME {
		public static final String fetchByFormId = "Form.fetchByFormId";
		public static final String findAll = "Form.findAll";
		public static final String findSampleIndicators = "Form.findSampleIndicators";
	}

	Form fetchByFormId(final Long formId);

	public List<Form> findBySelectedFilter(final String code, final String name, final String programmaticAreaCode,
	        final LifeCycleStatus lifeCycleStatus);

	List<Form> findSampleIndicators(List<String> questionUuids, LifeCycleStatus lifeCycleStatus);
}
