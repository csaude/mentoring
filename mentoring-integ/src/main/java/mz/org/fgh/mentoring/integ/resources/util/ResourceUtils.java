/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.util;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.org.fgh.mentoring.core.carrer.model.CarrerType;
import mz.org.fgh.mentoring.core.location.model.Province;

/**
 * @author Stélio Moiane
 *
 */
public interface ResourceUtils {

	String NAME = "mz.org.fgh.mentoring.integ.resources.util.ResourceUtils";

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/provinces")
	public JResponse<List<Province>> getProvinces();

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/carrertypes")
	public JResponse<List<CarrerType>> getCarrerTypes();
}
