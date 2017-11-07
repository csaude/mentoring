/**
 *
 */
package mz.org.fgh.mentoring.core.indicator.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.indicator.model.Indicator;

/**
 * @author Stélio Moiane
 *
 */
@Repository(IndicatorDAO.NAME)
public class IndicatorDAOImpl extends GenericDAOImpl<Indicator, Long> implements IndicatorDAO {

}
