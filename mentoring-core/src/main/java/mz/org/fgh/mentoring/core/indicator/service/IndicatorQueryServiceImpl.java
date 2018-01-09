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
}
