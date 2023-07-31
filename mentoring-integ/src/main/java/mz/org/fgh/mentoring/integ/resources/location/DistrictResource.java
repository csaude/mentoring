/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.District;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
public interface DistrictResource {
	String NAME = "mz.org.fgh.mentoring.integ.resources.location.DistrictResource";

	@GET
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<District>> getDistrictsByProvince(@QueryParam("province") final Province province)
			throws BusinessException;
}
