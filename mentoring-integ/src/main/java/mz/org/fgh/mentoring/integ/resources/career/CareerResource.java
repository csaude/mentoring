/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.career;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.career.model.Career;
import mz.org.fgh.mentoring.core.career.model.CareerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CareerResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.career.CareerResource";

	@GET
	@Path("{careerType}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public JResponse<List<Career>> getCareersByCareerType(@PathParam("careerType") final CareerType careerType)
			throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public JResponse<List<Career>> findAllCarrers() throws BusinessException;
}
