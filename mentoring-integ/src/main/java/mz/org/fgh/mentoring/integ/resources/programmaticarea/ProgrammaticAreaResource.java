/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.programmaticarea;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.programmaticarea.model.ProgrammaticArea;

/**
 * @author Stélio Moiane
 *
 */
public interface ProgrammaticAreaResource {

	public static final String NAME = "mz.org.fgh.mentoring.integ.resources.programmaticarea.ProgrammaticAreaResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<ProgrammaticArea> createProgrammaticArea(
			final ProgrammaticAreaBeanResource programmaticAreaBeanResource) throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<ProgrammaticArea>> findProgrammaticAreas(@QueryParam("code") final String code,
			@QueryParam("name") final String name, @QueryParam("description") final String surname)
			throws BusinessException;

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<ProgrammaticArea> updateProgrammaticArea(
			final ProgrammaticAreaBeanResource programmaticAreaBeanResource) throws BusinessException;
}
