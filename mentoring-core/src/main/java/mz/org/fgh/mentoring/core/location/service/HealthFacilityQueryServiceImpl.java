/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.core.location.service;

import java.util.List;

import javax.inject.Inject;

import mz.org.fgh.mentoring.core.tutor.dao.TutorDAO;
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

	@Inject
	private TutorDAO tutorDAO;

	@Override
	public List<HealthFacility> findHealthFacilityByDistrict(final UserContext userContext, final District district)
	        throws BusinessException {
		return this.healthFacilityDAO.findByDistrict(district.getId(), LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<HealthFacility> fetchAllHealthFacilities(final UserContext userContext) {
		return this.healthFacilityDAO.fetchdAll(LifeCycleStatus.ACTIVE);
	}

	@Override
	public List<HealthFacility> fetchAllHealthFacilitiesOfTutor(UserContext userContext) {
		try {
			return this.healthFacilityDAO.fetchByTutor(tutorDAO.findByUuid(userContext.getUuid()), LifeCycleStatus.ACTIVE);
		} catch (BusinessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public HealthFacility findHealthFacilityByUuid(final UserContext userContext, final String uuid) throws BusinessException {
		return this.healthFacilityDAO.findByUuid(uuid);
	}

	@Override
	public HealthFacility findHealthFacilityByDistrictAndName(final District district, final String healthFacility) {
		return this.healthFacilityDAO.findByDistrictAndName(district, healthFacility, LifeCycleStatus.ACTIVE);
	}
}
