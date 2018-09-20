/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.formquestion.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.formquestion.model.FormQuestion;

/**
 * @author Eusebio Jose Maposse
 *
 */

public interface FormQuestionDAO extends GenericDAO<FormQuestion, Long> {

	String NAME = "mz.org.fgh.mentoring.core.formquestion.dao.FormQuestionDAO";

	class QUERY {
		public static final String findByFormId = "SELECT fq FROM FormQuestion fq INNER JOIN FETCH fq.form f INNER JOIN FETCH fq.question q INNER JOIN FETCH f.programmaticArea WHERE fq.form.id = :formId";
		public static final String findByFormIdAndQuestionId = "SELECT fq FROM FormQuestion fq WHERE fq.form.id = :formId AND fq.question.id =:questionId";
		public static final String fetchByTutor = "SELECT fq FROM FormQuestion fq INNER JOIN FETCH fq.form f INNER JOIN FETCH f.programmaticArea pa INNER JOIN pa.tutorProgrammaticAreas tpa INNER JOIN tpa.tutor t INNER JOIN FETCH fq.question q WHERE fq.lifeCycleStatus = :lifeCycleStatus AND tpa.lifeCycleStatus = :lifeCycleStatus AND t.uuid = :tutorUuid";
	}

	class QUERY_NAME {
		public static final String findByFormId = "FormQuestion.findByFormId";
		public static final String findByFormIdAndQuestionId = "FormQuestion.findByFormIdAndQuestionId";
		public static final String fetchByTutor = "FormQuestion.fetchByTutor";
	}

	List<FormQuestion> findByFormId(final Long formId);

	FormQuestion findByFormIdAndQuestionId(final Long formId, final Long questionId);

	List<FormQuestion> fetchByTutor(final LifeCycleStatus lifeCycleStatus, final String tutorUuid);

}
