/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<SampleIndicator> findSamplesBySelectedFilter(final District district,
	        final HealthFacility healthFacility, final Form form, final LocalDate startDate, final LocalDate endDate,
	        final LifeCycleStatus lifeCycleStatus) {

		final Map<String, Integer> positions = new HashMap<>();

		final String DATE_PATTERN = "yyyy-MM-dd";
		final StringBuffer nativeQuery = new StringBuffer();
		nativeQuery.append("SELECT d.DISTRICT, hf.HEALTH_FACILITY, f.NAME, ").append("SUM(IF(q.UUID = '")
		        .append(SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES.getValue())
		        .append("', a.NUMERIC_VALUE, 0)) AS COLLECTED, ").append("SUM(IF(q.UUID = '")
		        .append(SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES.getValue())
		        .append("', a.NUMERIC_VALUE, 0)) AS TRANSPORTED, ").append("SUM(IF(q.UUID = '")
		        .append(SampleQuestion.NUMBER_OF_REJECTED_SAMPLES.getValue())
		        .append("', a.NUMERIC_VALUE, 0)) AS REJECTED, ").append("SUM(IF(q.UUID = '")
		        .append(SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES.getValue())
		        .append("', a.NUMERIC_VALUE, 0)) AS RECEIVED FROM ANSWERS a ")
		        .append("INNER JOIN INDICATORS i ON i.ID = a.INDICATOR_ID INNER JOIN FORMS f ON f.ID = i.FORM_ID ")
		        .append("INNER JOIN HEALTH_FACILITIES hf ON hf.ID = i.HEALTH_FACILITY_ID ")
		        .append("INNER JOIN DISTRICTS d ON d.ID = hf.DISTRICT_ID INNER JOIN QUESTIONS q ON q.ID = a.QUESTION_ID ")
		        .append("WHERE i.LIFE_CYCLE_STATUS = 'ACTIVE' AND ").append("q.uuid IN(").append("'")
		        .append(SampleQuestion.NUMBER_OF_COLLECTED_SAMPLES.getValue()).append("', ").append("'")
		        .append(SampleQuestion.NUMBER_OF_TRANSPORTED_SAMPLES.getValue()).append("', ").append("'")
		        .append(SampleQuestion.NUMBER_OF_REJECTED_SAMPLES.getValue()).append("', ").append("'")
		        .append(SampleQuestion.NUMBER_OF_RECEIVED_SAMPLES.getValue()).append("')");

		this.addConstraints(district, healthFacility, form, startDate, endDate, nativeQuery, positions);

		nativeQuery.append(" GROUP BY d.DISTRICT, hf.HEALTH_FACILITY, f.NAME");
		nativeQuery.append(" ORDER BY d.DISTRICT, hf.HEALTH_FACILITY, f.NAME");

		final Query query = this.getEntityManager().createNativeQuery(nativeQuery.toString(), SampleIndicator.NAME);

		this.setParameters(district, healthFacility, form, startDate, endDate, DATE_PATTERN, query, positions);

		return query.getResultList();
	}

	private void setParameters(final District district, final HealthFacility healthFacility, final Form form,
	        final LocalDate startDate, final LocalDate endDate, final String DATE_PATTEN, final Query query,
	        final Map<String, Integer> positions) {

		if (district != null) {
			query.setParameter(positions.get("district"), district.getUuid());
		}

		if (healthFacility != null) {
			query.setParameter(positions.get("healthFacility"), healthFacility.getUuid());
		}

		if (form != null) {
			query.setParameter(positions.get("form"), form.getUuid());
		}

		if (startDate != null) {
			query.setParameter(positions.get("startDate"), startDate.format(DateTimeFormatter.ofPattern(DATE_PATTEN)));
		}

		if (endDate != null) {
			query.setParameter(positions.get("endDate"), endDate.format(DateTimeFormatter.ofPattern(DATE_PATTEN)));
		}
	}

	private void addConstraints(final District district, final HealthFacility healthFacility, final Form form,
	        final LocalDate startDate, final LocalDate endDate, final StringBuffer nativeQuery,
	        final Map<String, Integer> positions) {

		int position = 0;

		if (district != null) {
			nativeQuery.append(" AND d.UUID = ?");
			positions.put("district", ++position);
		}

		if (healthFacility != null) {
			nativeQuery.append(" AND hf.UUID = ?");
			positions.put("healthFacility", ++position);
		}

		if (form != null) {
			nativeQuery.append(" AND f.UUID = ?");
			positions.put("form", ++position);
		}

		if (startDate != null) {
			nativeQuery.append(" AND i.REFERRED_MONTH >= ?");
			positions.put("startDate", ++position);
		}

		if (endDate != null) {
			nativeQuery.append(" AND i.REFERRED_MONTH <= ?");
			positions.put("endDate", ++position);
		}
	}

	@Override
	public List<Indicator> findByHealthFacilityFormAndReferredMonth(final HealthFacility healthFacility,
	        final Form form, final LocalDate referredMonth, final LifeCycleStatus lifeCycleStatus) {

		return this.findByNamedQuery(IndicatorDAO.QUERY_NAME.findByHealthFacilityFormAndReferredMonth,
		        new ParamBuilder().add("healthFacilityId", healthFacility.getId()).add("formId", form.getId())
		                .add("referredMonth", referredMonth).add("lifeCycleStatus", lifeCycleStatus).process());
	}

	@Override
	public List<DuplicatedIndicator> findDuplicated(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(IndicatorDAO.QUERY_NAME.findDuplicated,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process(), DuplicatedIndicator.class);
	}
}
