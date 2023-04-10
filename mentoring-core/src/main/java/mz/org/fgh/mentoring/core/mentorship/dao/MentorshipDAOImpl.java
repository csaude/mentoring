/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.mentorship.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.mentorship.model.IterationType;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(MentorshipDAO.NAME)
public class MentorshipDAOImpl extends GenericDAOImpl<Mentorship, Long> implements MentorshipDAO {

	@Override
	public List<Mentorship> fetchBySelectedFilter(final String code, final String tutorName, final String tutoredName,
			final String formName, final String healthFacility, final IterationType iterationType,
			final Integer iterationNumber, final LifeCycleStatus lifeCycleStatus,
			final LocalDate performedStartDate, final LocalDate performedEndDate) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Mentorship> createQuery = criteriaBuilder.createQuery(Mentorship.class);
		final Root<Mentorship> root = createQuery.from(Mentorship.class);
		root.fetch("tutor").fetch("career");
		root.fetch("tutored").fetch("career");
		root.fetch("form").fetch("programmaticArea");
		root.fetch("healthFacility").fetch("district");
		root.fetch("session", JoinType.LEFT);
		root.fetch("cabinet", JoinType.LEFT);

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

		if(iterationType != null) {
			predicates.add(criteriaBuilder.equal(root.get("iterationType"), iterationType));
		}

		if(iterationNumber != null) {
			predicates.add(criteriaBuilder.equal(root.get("iterationNumber"), iterationNumber));
		}

		// Deal with performedDate params
		final String PERFORMED_START_DATE_PARAM = "performedStartDateParam";
		if(performedStartDate != null) {
			final ParameterExpression<LocalDate> param = criteriaBuilder.parameter(LocalDate.class, PERFORMED_START_DATE_PARAM);
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), param));
		}

		final String PERFORMED_END_DATE_PARAM = "performedEndDateParam";
		if(performedEndDate != null) {
			final ParameterExpression<LocalDate> param = criteriaBuilder.parameter(LocalDate.class, PERFORMED_END_DATE_PARAM);
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), param));
		}

		if(lifeCycleStatus != null) {
			predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));
		}

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));
		createQuery.orderBy(criteriaBuilder.asc(root.get("code")));

		final TypedQuery<Mentorship> query = this.getEntityManager().createQuery(createQuery);

		if(performedStartDate != null) {
			query.setParameter(PERFORMED_START_DATE_PARAM, performedStartDate);
		}

		if(performedEndDate != null) {
			query.setParameter(PERFORMED_END_DATE_PARAM, performedEndDate);
		}


		return query.getResultList();
	}
}
