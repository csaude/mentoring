/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Path;

import org.springframework.stereotype.Service;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;
import mz.org.fgh.mentoring.core.location.service.DistrictQueryService;
import mz.org.fgh.mentoring.integ.resources.AbstractResource;

/**
 * @author Stélio Moiane
 *
 */
@Service(DistrictResource.NAME)
@Path("districts")
public class DistrictResourceImpl extends AbstractResource implements DistrictResource {

	@Inject
	private DistrictQueryService districtQueryService;

	@Override
	public JResponse<List<District>> getDistrictsByProvince(final Province province) throws BusinessException {

		final List<District> districts = this.districtQueryService.findDistrictsByProvince(this.getUserContetx(),
				province);

		return JResponse.ok(districts).build();
	}
}
