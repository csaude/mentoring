/**
 *
 */
package mz.org.fgh.mentoring.core.partner.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.partner.model.Partner;


/**
 * @author Stélio Moiane
 *
 */
@Repository(PartnerDAO.NAME)
public class PartnerDAOImpl extends GenericDAOImpl<Partner, Long> implements PartnerDAO {
	
	@Override
	public List<Partner> findAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(PartnerDAO.QUERY_NAME.findAll,
		        new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}

}
