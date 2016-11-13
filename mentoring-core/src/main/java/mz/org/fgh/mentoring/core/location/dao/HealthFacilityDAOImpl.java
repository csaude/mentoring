/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.co.mozview.frameworks.core.dao.ParamBuilder;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Repository(HealthFacilityDAO.NAME)
public class HealthFacilityDAOImpl extends GenericDAOImpl<HealthFacility, Long> implements HealthFacilityDAO {

	@Override
	public List<HealthFacility> findByDistrict(final Long districtId, final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(HealthFacilityDAO.QUERY_NAME.findByDistrict,
				new ParamBuilder().add("districtId", districtId).add("lifeCycleStatus", lifeCycleStatus).process());
	}
	
	@Override
	public List<HealthFacility> fetchdAll(final LifeCycleStatus lifeCycleStatus) {
		return this.findByNamedQuery(HealthFacilityDAO.QUERY_NAME.fetchAll, new ParamBuilder().add("lifeCycleStatus", lifeCycleStatus).process());
	}
}
