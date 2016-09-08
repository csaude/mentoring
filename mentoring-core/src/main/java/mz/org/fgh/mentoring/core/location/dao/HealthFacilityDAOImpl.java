/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mz.co.mozview.frameworks.core.dao.GenericDAOImpl;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Repository(HealthFacilityDAO.NAME)
public class HealthFacilityDAOImpl extends GenericDAOImpl<HealthFacility, Long> implements HealthFacilityDAO {

	@Override
	public List<HealthFacility> findByDistrict(final Long userContextId, final Long districtId) {
		return null;
	}

}
