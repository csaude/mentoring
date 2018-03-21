/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

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
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(MentorshipDAO.NAME)
public class MentorshipDAOImpl extends GenericDAOImpl<Mentorship, Long> implements MentorshipDAO {

	@Override
	public List<Mentorship> fetchBySelectedFilter(final String code, final String tutorName, final String tutoredName,
	        final String formName, final String healthFacility, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Mentorship> createQuery = criteriaBuilder.createQuery(Mentorship.class);
		final Root<Mentorship> root = createQuery.from(Mentorship.class);
		root.fetch("tutor").fetch("career");
		root.fetch("tutored").fetch("career");
		root.fetch("form").fetch("programmaticArea");
		root.fetch("healthFacility").fetch("district");

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
		}

		if (tutorName != null) {
			predicates.add(criteriaBuilder.like(root.get("tutor").get("name"), "%" + tutorName + "%"));
		}

		if (tutoredName != null) {
			predicates.add(criteriaBuilder.like(root.get("tutored").get("name"), "%" + tutoredName + "%"));
		}

		if (formName != null) {
			predicates.add(criteriaBuilder.like(root.get("form").get("name"), "%" + formName + "%"));
		}

		if (healthFacility != null) {
			predicates.add(
			        criteriaBuilder.like(root.get("healthFacility").get("healthFacility"), "%" + healthFacility + "%"));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		createQuery.orderBy(criteriaBuilder.asc(root.get("code")));

		final TypedQuery<Mentorship> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}
}
