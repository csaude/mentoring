/**
 *
 */
package mz.org.fgh.mentoring.core.partner.dao;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
public interface PartnerDAO extends GenericDAO<Partner, Long> {

	public static final String NAME = "mz.org.fgh.mentoring.core.partner.dao.PartnerDAO";

}
