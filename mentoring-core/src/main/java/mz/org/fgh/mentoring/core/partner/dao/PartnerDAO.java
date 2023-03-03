/**
 *
 */
package mz.org.fgh.mentoring.core.partner.dao;

import java.util.List;

import mz.co.mozview.frameworks.core.dao.GenericDAO;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.partner.model.Partner;

/**
 * @author Stélio Moiane
 *
 */
public interface PartnerDAO extends GenericDAO<Partner, Long> {

	public static final String NAME = "mz.org.fgh.mentoring.core.partner.dao.PartnerDAO";
	
	class QUERY {
		public static final String findAll = "SELECT p FROM Partner p WHERE p.lifeCycleStatus = :lifeCycleStatus";
		
	}

	class QUERY_NAME {
		public static final String findAll = "Partner.findAll";
	}

	
	List<Partner> findAll(final LifeCycleStatus lifeCycleStatus);
	
	

}
