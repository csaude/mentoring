/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.answer.model.NumericAnswer;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.model.SampleIndicator;
import mz.org.fgh.mentoring.core.indicator.model.SampleQuestion;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Repository(IndicatorDAO.NAME)
public class IndicatorDAOImpl extends GenericDAOImpl<Indicator, Long> implements IndicatorDAO {

	@Override
	public List<SampleIndicator> findSamplesBySelectedFilter(final District district,
	        final HealthFacility healthFacility, final Form form, final LocalDate startDate, final LocalDate endDate,
	        final LifeCycleStatus lifeCycleStatus) {

		final CriteriaBuilder criteriaBuilder = this.getEntityManager().getCriteriaBuilder();
		final CriteriaQuery<SampleIndicator> createQuery = criteriaBuilder.createQuery(SampleIndicator.class);
		final Root<NumericAnswer> root = createQuery.from(NumericAnswer.class);
		root.join("form");
		root.join("indicator").join("healthFacility").join("district");
		root.join("question");

		createQuery.select(criteriaBuilder.construct(SampleIndicator.class,
		        root.get("indicator").get("healthFacility").get("district").get("district"),
		        root.get("indicator").get("healthFacility").get("healthFacility"), root.get("form").get("name"),
		        root.get("question").get("question"), root.get("indicator").get("referredMonth"),
		        root.get("numericValue")));

		final List<Predicate> predicates = new ArrayList<>();

		if (district != null) {
			predicates.add(criteriaBuilder.equal(
			        root.get("indicator").get("healthFacility").get("district").get("uuid"), district.getUuid()));
		}

		if (healthFacility != null) {
			predicates.add(criteriaBuilder.equal(root.get("indicator").get("healthFacility").get("uuid"),
			        healthFacility.getUuid()));
		}

		if (form != null) {
			predicates.add(criteriaBuilder.equal(root.get("form").get("uuid"), form.getUuid()));
		}

		if (startDate != null) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("indicator").get("referredMonth"), startDate));
		}

		if (endDate != null) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("indicator").get("referredMonth"), endDate));
		}

		predicates.add(criteriaBuilder.in(root.get("question").get("uuid"))
		        .value(Arrays.asList(SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES.getValue(),
		                SampleQuestion.NUMBER_OF_REJECTED_SAMPLES.getValue(),
		                SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES.getValue(),
		                SampleQuestion.NUMER_OF_RECEIVED_SAMPLES.getValue())));

		predicates.add(criteriaBuilder.equal(root.get("lifeCycleStatus"), lifeCycleStatus));

		createQuery.where(predicates.toArray(new Predicate[predicates.size()]));

		createQuery.groupBy(root.get("indicator").get("healthFacility").get("district").get("district"),
		        root.get("indicator").get("healthFacility").get("healthFacility"), root.get("form").get("name"),
		        root.get("question").get("question"), root.get("indicator").get("referredMonth"));

		createQuery.orderBy(criteriaBuilder.asc(root.get("indicator").get("referredMonth")),
		        criteriaBuilder.asc(root.get("indicator").get("healthFacility").get("district").get("district")),
		        criteriaBuilder.asc(root.get("indicator").get("healthFacility").get("healthFacility")),
		        criteriaBuilder.asc(root.get("form").get("name")),
		        criteriaBuilder.asc(root.get("question").get("question")));

		final TypedQuery<SampleIndicator> query = this.getEntityManager().createQuery(createQuery);

		return query.getResultList();
	}
}
