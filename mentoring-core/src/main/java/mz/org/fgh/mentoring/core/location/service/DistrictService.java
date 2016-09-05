/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.District;

/**
 * @author Stélio Moiane
 *
 */
public interface DistrictService {

	String NAME = "mz.org.fgh.mentoring.core.location.service.DistrictService";

	District createDistrict(final UserContext userContext, final District district) throws BusinessException;
}
