/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;

/**
 * @author Stélio Moiane
 *
 */
public interface IndicatorDAO extends GenericDAO<Indicator, Long> {

	String NAME = "mz.org.fgh.mentoring.core.indicator.dao.IndicatorDAO";

}
