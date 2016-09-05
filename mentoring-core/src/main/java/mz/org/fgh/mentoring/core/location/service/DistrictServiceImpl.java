/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.service.AbstractService;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.dao.DistrictDAO;
import mz.org.fgh.mentoring.core.location.model.District;

/**
 * @author Stélio Moiane
 *
 */
@Service(DistrictService.NAME)
public class DistrictServiceImpl extends AbstractService implements DistrictService {

	@Inject
	private DistrictDAO districDAO;

	@Override
	public District createDistrict(final UserContext userContext, final District district) throws BusinessException {
		return this.districDAO.create(userContext.getId(), district);
	}
}
