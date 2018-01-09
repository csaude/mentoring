/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import java.time.LocalDate;
import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.form.model.Form;
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

	List<SampleIndicator> findSamplesBySelectedFilter(District district, HealthFacility healthFacility, Form form,
	        LocalDate startDate, LocalDate endDate, final LifeCycleStatus lifeCycleStatus);
}
