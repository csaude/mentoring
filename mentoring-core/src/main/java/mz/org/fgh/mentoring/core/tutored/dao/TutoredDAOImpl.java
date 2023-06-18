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
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
@Repository(TutoredDAO.NAME)
public class TutoredDAOImpl extends GenericDAOImpl<Tutored, Long> implements TutoredDAO {

	@Override
	public List<Tutored> findBySelectedFilter(final String uuid, final String code, final String name,
			final String surname, final String phoneNumber, final String tutored,
			final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<Tutored> createQuery = criteriaBuilder.createQuery(Tutored.class);
		final Root<Tutored> root = createQuery.from(Tutored.class);
		root.fetch("career");

		createQuery.select(root);

		final List<Predicate> predicates = new ArrayList<>();

		if (uuid != null) {
			predicates.add(criteriaBuilder.like(root.get("uuid"), "%" + uuid + "%"));
		}

		if (code != null) {
			predicates.add(criteriaBuilder.like(root.get("code"), "%" + code + "%"));
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
			predicates.add(criteriaBuilder.equal(root.get("carrer").get("code"), tutored));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));
		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		final TypedQuery<Tutored> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<Tutored> findBySelectedFilterByTutor(Long tutorId,String code, String name, String surname, String phoneNumber, LifeCycleStatus lifeCycleStatus) {

		return this.findByNamedQuery(TutoredDAO.QUERY_NAME.findBySelectedFilterByTutor,
				new ParamBuilder().add("tutorId", tutorId).add("code", code).add("name", name)
						.add("surname", surname).add("phoneNumber", phoneNumber)
						.add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Tutored> fetchByUser(final String userUuid, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(TutoredDAO.QUERY_NAME.fetchByUser,
				new ParamBuilder().add("userUuid", userUuid).add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
