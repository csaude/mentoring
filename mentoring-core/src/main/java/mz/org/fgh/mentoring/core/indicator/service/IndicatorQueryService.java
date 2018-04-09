/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.service;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
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
public interface IndicatorQueryService {

	List<SampleIndicator> findSampleIndicatorsBySelectedFilter(District district, HealthFacility healthFacility,
	        Form form, LocalDate startDate, LocalDate endDate) throws BusinessException;

	List<Indicator> findIndicatorsByHealthFacilityFormAndReferredMonth(HealthFacility healthFacility, Form form,
	        LocalDate referredMonth) throws BusinessException;

	List<DuplicatedIndicator> findDuplicatedIndicators();

	List<AnalysisTable> findAnalysisTableBySelectedFilter(final District district, LocalDate startDate,
	        final LocalDate endDate);
}
