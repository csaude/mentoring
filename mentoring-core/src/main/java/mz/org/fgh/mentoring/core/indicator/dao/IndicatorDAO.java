/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.model.AnalysisTable;
import mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;
import mz.org.fgh.mentoring.core.indicator.model.SampleIndicator;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface IndicatorDAO extends GenericDAO<Indicator, Long> {

	String NAME = "mz.org.fgh.mentoring.core.indicator.dao.IndicatorDAO";

	class QUERY {
		public static final String findByHealthFacilityFormAndReferredMonth = "SELECT i FROM Indicator i INNER JOIN FETCH i.answers a INNER JOIN FETCH a.question WHERE i.healthFacility.id = :healthFacilityId AND i.form.id = :formId AND i.referredMonth = :referredMonth AND i.lifeCycleStatus = :lifeCycleStatus ORDER BY i.id DESC";
		public static final String findDuplicated = "SELECT NEW mz.org.fgh.mentoring.core.indicator.model.DuplicatedIndicator(hf.id, f.id, i.referredMonth, COUNT(i.id)) FROM Indicator i INNER JOIN i.healthFacility hf INNER JOIN i.form f WHERE i.lifeCycleStatus = :lifeCycleStatus GROUP BY hf.uuid, f.uuid, i.referredMonth HAVING COUNT(i.id) > 1 ORDER BY hf.uuid, f.uuid, i.referredMonth";
	}

	class QUERY_NAME {
		public static final String findByHealthFacilityFormAndReferredMonth = "Indicator.findByHealthFacilityFormAndReferredMonth";
		public static final String findDuplicated = "DuplicatedIndicator.findDuplicated";
	}

	List<SampleIndicator> findSamplesBySelectedFilter(final District district, final HealthFacility healthFacility,
	        final Form form, final LocalDate startDate, final LocalDate endDate, final LifeCycleStatus lifeCycleStatus);

	List<Indicator> findByHealthFacilityFormAndReferredMonth(final HealthFacility healthFacility, final Form form,
	        final LocalDate referredMonth, final LifeCycleStatus lifeCycleStatus);

	List<DuplicatedIndicator> findDuplicated(final LifeCycleStatus lifeCycleStatus);

	List<AnalysisTable> findAnalysisTableBySelectedFilter(final District district, final LocalDate startDate,
	        final LocalDate endDate);
}
