/**
 *
 */
package mz.org.fgh.mentoring.core.question.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.question.model.QuestionsCategory;

/**
 * @author Stélio Moiane
 *
 */
@Repository(QuestionCategoryDAOImpl.NAME)
public class QuestionCategoryDAOImpl extends GenericDAOImpl<QuestionsCategory, Long> implements QuestionCategoryDAO {

	public static final String NAME = "mz.org.fgh.mentoring.core.question.dao.QuestionCategoryDAOImpl";

	@Override
	public List<QuestionsCategory> findAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(QuestionCategoryDAO.QUERY_NAME.findAll,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
