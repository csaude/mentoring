/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.carrer;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.carrer.model.Carrer;
import mz.org.fgh.mentoring.core.carrer.model.CarrerType;

/**
 * @author Stélio Moiane
 *
 */
public interface CarrerResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.carrer.CarrerResource";

	@GET
	@Path("{carrerType}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public JResponse<List<Carrer>> getCarrersByCarrerType(@PathParam("carrerType") final CarrerType carrerType)
			throws BusinessException;
}
