/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.co.mozview.frameworks.core.util.LifeCycleStatus;
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.location.dao.DistrictDAO;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
@Service(DistrictQueryService.NAME)
public class DistrictQueryServiceImpl implements DistrictQueryService {

	@Inject
	private DistrictDAO districtDAO;

	@Override
	public List<District> findDistrictsByProvince(final UserContext userContext, final Province province)
			throws BusinessException {
		return this.districtDAO.findByProvince(province, LifeCycleStatus.ACTIVE);
	}
}
