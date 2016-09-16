/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Service(HealthFacilityQueryService.NAME)
public class HealthFacilityQueryServiceImpl extends AbstractService implements HealthFacilityQueryService {

	@Inject
	private HealthFacilityDAO healthFacilityDAO;

	@Override
	public List<HealthFacility> findHealthFacilityByDistrict(final UserContext userContext, final District district)
			throws BusinessException {
		return this.healthFacilityDAO.findByDistrict(district.getId(), LifeCycleStatus.ACTIVE);
	}
}
