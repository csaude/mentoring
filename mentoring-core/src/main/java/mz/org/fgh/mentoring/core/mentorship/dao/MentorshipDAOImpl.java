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
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(MentorshipDAO.NAME)
public class MentorshipDAOImpl extends GenericDAOImpl<Mentorship, Long> implements MentorshipDAO {

	@Override
	public List<Mentorship> findBySelectedFilter(final String code, final String tutorCode, final String tutoredCode,
			final LifeCycleStatus lifeCycleStatus) {

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

		if (tutorCode != null) {
			predicates.add(criteriaBuilder.like(root.get("tutorCode").get("code"), "%" + tutorCode + "%"));
		}

		if (tutoredCode != null) {
			predicates.add(criteriaBuilder.equal(root.get("tutoredCode").get("code"), tutoredCode));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Mentorship> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<Mentorship> countMentorshipByHealthFacility(HealthFacility healthFacility,
			LifeCycleStatus lifeCycleStatus) {

		return this.findByNamedQuery(MentorshipDAO.QUERY_NAME.countMentorshipByHealthFacility, new ParamBuilder()
				.add("healthFacility", healthFacility).add("lifeCycleStatus", lifeCycleStatus).process());

	}

}
