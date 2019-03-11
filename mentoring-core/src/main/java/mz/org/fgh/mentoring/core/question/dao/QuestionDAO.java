/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionCategory;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Stélio Moiane
 *
 */

public interface QuestionDAO extends GenericDAO<Question, Long> {

	String NAME = "mz.org.fgh.mentoring.core.question.dao.QuestionDAO";

	public static class QUERY {
		public static final String findByFormCode = "SELECT q FROM Question q INNER JOIN q.formQuestions fq WHERE fq.form.code = :code AND fq.lifeCycleStatus = :lifeCycleStatus";
		public static final String findByuuid = "SELECT q FROM Question q WHERE q.uuid = :uuid AND q.lifeCycleStatus = :lifeCycleStatus";

	}

	public static class QUERY_NAME {
		public static final String findByFormCode = "Question.findByFormCode";
		public static final String findByuuid = "Question.findByuuid";

	}

	List<Question> findBySelectedFilter(final String code, final String question, final QuestionType questionType,
			QuestionCategory questionCategory, final LifeCycleStatus lifeCycleStatus);

	List<Question> findByFormCode(final String code, final LifeCycleStatus lifeCycleStatus);

	Question findByuuid(final String uuid, final LifeCycleStatus lifeCycleStatus);
}
