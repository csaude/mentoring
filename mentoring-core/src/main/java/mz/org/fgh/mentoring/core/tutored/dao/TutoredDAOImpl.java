/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.tutored.dao;

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
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutoredDAO.NAME)
public class TutoredDAOImpl extends GenericDAOImpl<Tutored, Long> implements TutoredDAO {

	@Override
	public List<Tutored> findBySelectedFilter(String code, String name, String surname, String phoneNumber, final String tutored,
			LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Tutored> createQuery = criteriaBuilder.createQuery(Tutored.class);
		final Root<Tutored> root = createQuery.from(Tutored.class);
		root.fetch("carrer");

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (tutored != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + tutored + "%"));
		}

		if (name != null) {
			predicates.add(criteriaBuilder.like(root.get("name"), "%" + name + "%"));
		}

		if (surname != null) {
			predicates.add(criteriaBuilder.like(root.get("surname"), "%" + surname + "%"));
		}

		if (phoneNumber != null) {
			predicates.add(criteriaBuilder.like(root.get("phoneNumber"), "%" + phoneNumber + "%"));
		}
		if (tutored != null) {
			predicates.add(criteriaBuilder.equal(root.get("carrer"), tutored));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));
		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Tutored> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

}
