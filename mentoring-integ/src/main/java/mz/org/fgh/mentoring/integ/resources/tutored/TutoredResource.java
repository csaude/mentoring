/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutored;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.tutored.model.Tutored;

/**
 * @author Eusebio Jose Maposse
 *
 */
public interface TutoredResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.tutored.TutoredResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutored> createTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException;

	@POST
	@Path("sync")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<TutoredBeanResource> syncronizeTutoreds(final TutoredBeanResource tutoredBeanResource)
			throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Tutored>> findTutoreds(@QueryParam("uuid") final String uuid,
			@QueryParam("code") final String code, @QueryParam("name") final String name,
			@QueryParam("surname") final String surname, @QueryParam("phoneNumber") final String phoneNumber,
			@QueryParam("tutored") final String tutored) throws BusinessException;

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutored> updateTutored(final TutoredBeanResource tutoredBeanResource) throws BusinessException;

	@GET
	@Path("{userUuid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Tutored>> findTutoredsByUser(@PathParam("userUuid") final String userUuid)
			throws BusinessException;
}
