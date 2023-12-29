/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.tutor;

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
import mz.co.mozview.frameworks.core.webservices.model.UserContext;
import mz.org.fgh.mentoring.core.career.model.CareerType;
import mz.org.fgh.mentoring.core.tutor.model.Tutor;

/**
 * @author Stélio Moiane
 *
 */
public interface TutorResource {

	String NAME = "mz.org.fgh.mentoring.integ.resources.tutor.TutorResource";

	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutor> createTutor(final TutorBeanResource tutorBeanResource) throws BusinessException;

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Tutor>> findTutors(@QueryParam("code") final String code,
			@QueryParam("name") final String name, @QueryParam("surname") final String surname,
			@QueryParam("careerType") final CareerType careerType, @QueryParam("phoneNumber") final String phoneNumber)
					throws BusinessException;
	@GET
	@Path("tutor-partner")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Tutor>> findTutors(@QueryParam("code") final String code,
			@QueryParam("name") final String name, @QueryParam("surname") final String surname,
			@QueryParam("careerType") final CareerType careerType, @QueryParam("phoneNumber") final String phoneNumber, @QueryParam("partnerUuid") final String partnerUuid)
					throws BusinessException;

	@PUT
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutor> updateTutor(final TutorBeanResource tutorBeanResource) throws BusinessException;

	@GET
	@Path("{uuid}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutor> fetchTutorByUuid(@PathParam("uuid") final String uuid) throws BusinessException;

	@PUT
	@Path("reset-password")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<Tutor> resetPassword(final UserContext userContext) throws BusinessException;

	@POST
	@Path("v2/tutor-locations")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public JResponse<List<TutorLocationDTO>> allocateTutorLocations(final TutorBeanResource tutorBeanResource) throws BusinessException;
	
	@GET
	@Path("v2/{partnerUuid}/tutors")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Tutor>> fetchTutorsByPartnerUuid(@PathParam("partnerUuid") final String partnerUuid)
					throws BusinessException;
}
