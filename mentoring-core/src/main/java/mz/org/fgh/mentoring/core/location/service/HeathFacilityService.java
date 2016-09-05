/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HeathFacilityService {

	String NAME = "mz.org.fgh.mentoring.core.location.service.HeathFacilityService";

	HealthFacility createHealthFacility(final UserContext userContext, final HealthFacility healthFacility)
			throws BusinessException;

}
