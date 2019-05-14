/**
 *
 */
package mz.org.fgh.mentoring.core.question.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
public interface QuestionCategoryDAO extends GenericDAO<QuestionsCategory, Long> {

	class QUERY {
		public static final String findAll = "SELECT qc FROM QuestionsCategory qc where qc.lifeCycleStatus = :lifeCycleStatus";
	}

	class QUERY_NAME {
		public static final String findAll = "QuestionsCategory.findAll";
	}

	List<QuestionsCategory> findAll(LifeCycleStatus lifeCycleStatus);
}
