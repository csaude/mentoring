/**
 *
 */
package mz.org.fgh.mentoring.core.session.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.location.model.Cabinet;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.Session;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
@Repository(SessionDAO.NAME)
public class SessionDAOImpl extends GenericDAOImpl<Session, Long> implements SessionDAO {

	@Override
	public List<PerformedSession> findBySelectedFilter(final District district, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);
		final Root<Session> root = createQuery.from(Session.class);
		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("form").join("programmaticArea");
		mentorships.join("healthFacility").join("district");
		mentorships.join("tutor");
		final Join<Mentorship, Cabinet> cabinets = mentorships.join("cabinet", JoinType.LEFT);

		createQuery.select(criteriaBuilder.construct(PerformedSession.class, mentorships.get("form").get("name"),
		        criteriaBuilder.countDistinct(root.get("id"))));

		final List<Predicate> predicates = new ArrayList<>();

		if (district != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("healthFacility").get("district").get("uuid"),
			        district.getUuid()));
		}

		if (healthFacility != null) {
			predicates.add(
			        criteriaBuilder.equal(mentorships.get("healthFacility").get("uuid"), healthFacility.getUuid()));
		}

		if (programmaticArea != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("programmaticArea").get("uuid"),
			        programmaticArea.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (cabinet != null) {
			predicates.add(criteriaBuilder.equal(cabinets.get("uuid"), cabinet.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(mentorships.get("form").get("id"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("form").get("name")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<SubmitedSessions> findNumberOfSessionsPerDistrict(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.findNumberOfSessionsPerDistrict,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process(), SubmitedSessions.class);
	}

	@Override
	public List<PerformedSession> findByTutorAndForm(final Tutor tutor, final Form form, final LocalDate startDate,
	        final LocalDate endDate) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();

		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);

		final Root<Session> root = createQuery.from(Session.class);

		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("tutor");
		mentorships.join("form");
		mentorships.join("healthFacility").join("district");

		createQuery.select(criteriaBuilder.construct(PerformedSession.class,
		        mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"),
		        criteriaBuilder.countDistinct(root.get("id"))));

		final List<Predicate> predicates = new ArrayList<>();

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), LifeCycleStatus.ACTIVE));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("healthFacility").get("district").get("district")),
		        criteriaBuilder.asc(mentorships.get("healthFacility").get("healthFacility")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}

	@Override
	public List<Session> findWithDuplicatedUuids(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.findWithDuplicatedUuids,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<Session> fetchSessionsByUuid(final String sessionUuid, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(SessionDAO.QUERY_NAME.fetchSessionsByUuid,
		        new ParamBuilder().add("sessionUuid", sessionUuid).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<PerformedSession> findBySelectedFilterList(final District district, final HealthFacility healthFacility,
	        final ProgrammaticArea programmaticArea, final Form form, final Tutor tutor, final Cabinet cabinet,
	        final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<PerformedSession> createQuery = criteriaBuilder.createQuery(PerformedSession.class);
		final Root<Session> root = createQuery.from(Session.class);
		final Join<Session, Mentorship> mentorships = root.join("mentorships");

		mentorships.join("form").join("programmaticArea");
		mentorships.join("healthFacility").join("district");
		mentorships.join("tutor").join("career");
		final Join<Mentorship, Cabinet> cabinets = mentorships.join("cabinet", JoinType.LEFT);

		createQuery.select(criteriaBuilder.construct(PerformedSession.class, mentorships.get("form").get("name"),
		        root.get("createdAt"), root.get("performedDate"),
		        mentorships.get("healthFacility").get("district").get("district"),
		        mentorships.get("healthFacility").get("healthFacility"), cabinets.get("name"),
		        mentorships.get("tutor").get("name"), mentorships.get("tutor").get("surname"),
		        mentorships.get("tutor").get("career").get("position"), root.get("startDate"), root.get("endDate"),
		        root.get("status")));

		final List<Predicate> predicates = new ArrayList<>();

		if (district != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("healthFacility").get("district").get("uuid"),
			        district.getUuid()));
		}

		if (healthFacility != null) {
			predicates.add(
			        criteriaBuilder.equal(mentorships.get("healthFacility").get("uuid"), healthFacility.getUuid()));
		}

		if (programmaticArea != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("programmaticArea").get("uuid"),
			        programmaticArea.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("form").get("uuid"), form.getUuid()));
		}

		if (tutor != null) {
			predicates.add(criteriaBuilder.equal(mentorships.get("tutor").get("uuid"), tutor.getUuid()));
		}

		if (cabinet != null) {
			predicates.add(criteriaBuilder.equal(cabinets.get("uuid"), cabinet.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("performedDate"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("performedDate"), endDate));
		}

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(root.get("id"));

		createQuery.orderBy(criteriaBuilder.asc(mentorships.get("form").get("name")),
		        criteriaBuilder.asc(root.get("performedDate")),
		        criteriaBuilder.asc(mentorships.get("healthFacility").get("district").get("district")));

		final TypedQuery<PerformedSession> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}
}
