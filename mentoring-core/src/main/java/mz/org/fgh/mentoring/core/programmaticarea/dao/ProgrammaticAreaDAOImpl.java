/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.programmaticarea.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
@Repository(ProgrammaticAreaDAO.NAME)
public class ProgrammaticAreaDAOImpl extends GenericDAOImpl<ProgrammaticArea, Long> implements ProgrammaticAreaDAO {

	@Override
	public List<ProgrammaticArea> findBySelectedFilter(final UserContext userContext, final String code,
			final String name, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<ProgrammaticArea> createQuery = criteriaBuilder.createQuery(ProgrammaticArea.class);
		final Root<ProgrammaticArea> root = createQuery.from(ProgrammaticArea.class);

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
		}

		if (name != null) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));
		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<ProgrammaticArea> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}
}
