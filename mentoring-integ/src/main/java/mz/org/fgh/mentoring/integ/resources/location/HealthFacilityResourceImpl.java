/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;
import mz.org.fgh.mentoring.core.location.service.HealthFacilityQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(HealthFacilityResource.NAME)
@Path("healthfacilities")
public class HealthFacilityResourceImpl extends AbstractResource implements HealthFacilityResource {

	@Inject
	private HealthFacilityQueryService healthFacilityQueryService;

	@Override
	public JResponse<List<HealthFacility>> findHealthFacilitiesByDistrictId(final Long districtId)
			throws BusinessException {

		final District district = new District();
		district.setId(districtId);

		final List<HealthFacility> healthFacilities = this.healthFacilityQueryService
				.findHealthFacilityByDistrict(this.getUserContetx(), district);

		return JResponse.ok(healthFacilities).build();
	}

	@Override
	public JResponse<List<HealthFacility>> fetchAllHealthFacilities() throws BusinessException {
		
		final List<HealthFacility> healthFacilities = this.healthFacilityQueryService.fetchAllHealthFacilities(this.getUserContetx());
		
		return JResponse.ok(healthFacilities).build(); 
	}

	@Override
	public JResponse<List<HealthFacility>> fetchAllHealthFacilitiesOfTutor(final String uuid) {
		final UserContext userContext = new UserContext();
		userContext.setUuid(uuid);
		List<HealthFacility> healthFacilities = this.healthFacilityQueryService.fetchAllHealthFacilitiesOfTutor(userContext);

		return JResponse.ok(healthFacilities).build();
	}
}
