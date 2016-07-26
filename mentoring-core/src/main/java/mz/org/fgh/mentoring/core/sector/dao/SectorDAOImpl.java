/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.sector.dao;

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
import mz.org.fgh.mentoring.core.sector.model.Sector;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(SectorDAO.NAME)
public class SectorDAOImpl extends GenericDAOImpl<Sector, Long> implements SectorDAO {

	@Override
	public List<Sector> findBySelectedFilter(final UserContext userContext, String code, String name, String descripion,
			LifeCycleStatus lifeCycleStatus) {
		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Sector> createQuery = criteriaBuilder.createQuery(Sector.class);
		final Root<Sector> root = createQuery.from(Sector.class);

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
		}

		if (name != null) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
		}

		if (descripion != null) {
			predicates.add(criteriaBuilder.like(root.get("descripion"), "%" + descripion + "%"));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));
		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Sector> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

}
