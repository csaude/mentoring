/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.service;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
import mz.org.fgh.mentoring.core.indicator.dao.IndicatorDAO;
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
@Service(IndicatorQueryServiceImpl.NAME)
public class IndicatorQueryServiceImpl implements IndicatorQueryService {

	public static final String NAME = "mz.org.fgh.mentoring.core.indicator.service.IndicatorQueryServiceImpl";

	@Inject
	IndicatorDAO indicatorDAO;

	@Override
	public List<SampleIndicator> findSampleIndicatorsBySelectedFilter(final District district,
	        final HealthFacility healthFacility, final Form form, final LocalDate startDate, final LocalDate endDate)
	        throws BusinessException {

		return this.indicatorDAO.findSamplesBySelectedFilter(district, healthFacility, form, startDate, endDate,
		        LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<Indicator> findIndicatorsByHealthFacilityFormAndReferredMonth(final HealthFacility healthFacility,
	        final Form form, final LocalDate referredMonth) throws BusinessException {

		return this.indicatorDAO.findByHealthFacilityFormAndReferredMonth(healthFacility, form, referredMonth,
		        LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<DuplicatedIndicator> findDuplicatedIndicators() {
		return this.indicatorDAO.findDuplicated(LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<AnalysisTable> findAnalysisTableBySelectedFilter(final District district, final LocalDate startDate,
	        final LocalDate endDate) {
		return this.indicatorDAO.findAnalysisTableBySelectedFilter(district, startDate, endDate);
	}
}
