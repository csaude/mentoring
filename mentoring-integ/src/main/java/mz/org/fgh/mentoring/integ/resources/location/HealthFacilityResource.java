/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.location;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.location.model.HealthFacility;

/**
 * @author Stélio Moiane
 *
 */
public interface HealthFacilityResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.location.HealthFacilityResource";

	@Path("{districtId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<HealthFacility>> findHealthFacilitiesByDistrictId(@PathParam("districtId") final Long districtId)
			throws BusinessException;

	@Path("/province/{province}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	JResponse<List<HealthFacility>> findHealthFacilitiesByProvince(@PathParam("province") final String province)
			throws BusinessException;


	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<HealthFacility>> fetchAllHealthFacilities()throws BusinessException;


	@Path("tutor/{uuid}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	JResponse<List<HealthFacility>> fetchAllHealthFacilitiesOfTutor(@PathParam("uuid") final String uuid)throws BusinessException;
}
