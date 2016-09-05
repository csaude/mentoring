/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.dao.HealthFacilityDAO;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
@Service(HeathFacilityService.NAME)
public class HeathFacilityServiceImpl extends AbstractService implements HeathFacilityService {

	@Inject
	private HealthFacilityDAO healthFacilityDAO;

	@Override
	public HealthFacility createHealthFacility(final UserContext userContext, final HealthFacility healthFacility)
			throws BusinessException {
		return this.healthFacilityDAO.create(userContext.getId(), healthFacility);
	}
}
