/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityQueryService {

	String NAME = "mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService";

	List<HealthFacility> findHealthFacilityByDistrict(final UserContext userContext, final District district)
			throws BusinessException;
}
