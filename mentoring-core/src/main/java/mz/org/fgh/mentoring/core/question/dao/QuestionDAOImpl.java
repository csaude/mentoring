/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.question.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.question.model.Question;
import mz.org.fgh.mentoring.core.question.model.QuestionType;

/**
 * @author Stélio Moiane
 *
 */
@Repository(QuestionDAO.NAME)
public class QuestionDAOImpl extends GenericDAOImpl<Question, Long> implements QuestionDAO {

	@Override
	public List<Question> findByFormCode(final String code, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(QuestionDAO.QUERY_NAME.findByFormCode,
		        new ParamBuilder().add("code", code).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Question> findBySelectedFilter(final String code, final String question,
	        final QuestionType questionType, final LifeCycleStatus lifeCycleStatus) {
		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Question> createQuery = criteriaBuilder.createQuery(Question.class);
		final Root<Question> root = createQuery.from(Question.class);
		root.fetch("questionsCategory", JoinType.LEFT);

		createQuery.select(root);

		final ArrayList<Predicate> predicates = new ArrayList<>();

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
		}

		if (question != null) {
			predicates.add(criteriaBuilder.like(root.get("question"), "%" + question + "%"));
		}

		if (questionType != null) {
			predicates.add(criteriaBuilder.equal(root.get("questionType"), questionType));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Question> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public Question findByuuid(final String uuid, final LifeCycleStatus lifeCycleStatus) {
		return this.findSingleByNamedQuery(QuestionDAO.QUERY_NAME.findByuuid,
		        new ParamBuilder().add("uuid", uuid).add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
