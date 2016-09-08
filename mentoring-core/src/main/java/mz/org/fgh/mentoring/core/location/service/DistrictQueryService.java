/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
public interface DistrictQueryService {

	String NAME = "mz.org.fgh.mentoring.core.location.service.DistrictQueryService";

	List<District> findDistrictsByProvince(final UserContext userContext, final Province province)
			throws BusinessException;
}