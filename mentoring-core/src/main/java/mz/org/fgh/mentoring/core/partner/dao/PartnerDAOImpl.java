/**
 *
 */
package mz.org.fgh.mentoring.core.partner.dao;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
@Repository(PartnerDAO.NAME)
public class PartnerDAOImpl extends GenericDAOImpl<Partner, Long> implements PartnerDAO {

}
