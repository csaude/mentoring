/*
 * Friends in Global Health - FGH © 2016
 */
package mz.org.fgh.mentoring.integ.resources.mentorship;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.JResponse;

import mz.co.mozview.frameworks.core.exception.BusinessException;
import mz.org.fgh.mentoring.core.mentorship.model.Mentorship;
import mz.org.fgh.mentoring.core.session.model.PerformedSession;
import mz.org.fgh.mentoring.core.session.model.SubmitedSessions;

/**
 * @author Stélio Moiane
 *
 */
public interface MentorshipResource {

	String NAME = "mz.org.fgh.mentoring.integ.mentorship.MentorshipResource";

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public JResponse<Mentorship> createMentorshipProcess(final MentorshipBeanResource mentorshipBeanResource)
	        throws BusinessException;

	/**
	 *
	 * @param code
	 * @param tutor
	 * @param tutored
	 * @param form
	 * @param healthFacility
	 * @param iterationType
	 * @param iterationNumber
	 * @param performedStartDate
	 *            Optional date string of the form "YYYY-mm-dd" e.g "2018-12-31"
	 * @param performedEndDate
	 *            Optional date string of the form "YYYY-mm-dd" e.g "2018-12-31"
	 * @return
	 * @throws BusinessException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<Mentorship>> findBySelectedFilter(@QueryParam("code") final String code,
	        @QueryParam("tutor") final String tutor, @QueryParam("tutored") final String tutored,
	        @QueryParam("form") final String form, @QueryParam("healthFacility") final String healthFacility,
	        @QueryParam("iterationType") final String iterationType,
	        @QueryParam("iterationNumber") final Integer iterationNumber,
	        @QueryParam("lifeCycleStatus") final String lifeCycleStatus,
	        @QueryParam("performedStartDate") final String performedStartDate,
	        @QueryParam("performedEndDate") final String performedEndDate) throws BusinessException;

	@POST
	@Path("sync")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<MentorshipBeanResource> synchronizeMentorships(final MentorshipBeanResource mentorshipBeanResource)
	        throws BusinessException;

	@GET
	@Path("sessions")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<SubmitedSessions>> findSubmitedSessions() throws BusinessException;

	@GET
	@Path("performed-sessions")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessions(@QueryParam("districtUuid") String districtUuid,
	        @QueryParam("healthFacilityUuid") String healthFacilityUuid, @QueryParam("formUuid") String formUuid,
	        @QueryParam("programmaticAreaUuid") String programmaticAreaUuid, @QueryParam("tutorUuid") String tutorUuid,
	        @QueryParam("cabinetUuid") String cabinetUuid, @QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;
	
	@GET
	@Path("performed-sessions-list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsList(@QueryParam("districtUuid") String districtUuid,
	        @QueryParam("healthFacilityUuid") String healthFacilityUuid, @QueryParam("formUuid") String formUuid,
	        @QueryParam("programmaticAreaUuid") String programmaticAreaUuid, @QueryParam("tutorUuid") String tutorUuid,
	        @QueryParam("cabinetUuid") String cabinetUuid, @QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;

	@GET
	@Path("performed-sessions-by-tutor-and-form")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsByTutorAndForm(
	        @QueryParam("tutorUuid") String tutorUuid, @QueryParam("formUuid") String formUuid,
	        @QueryParam("startDate") String startDate, @QueryParam("endDate") String endDate) throws BusinessException;
	
	@GET
	@Path("performed-sessions-by-tutor")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsByTutor(
	        @QueryParam("tutorUuid") String tutorUuid,@QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Data of HTS Monitoring report
	 * @throws BusinessException
	 */
	@GET
	@Path("performed-sessions-hts")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsHTS(@QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @return Data of Narrative report
	 * @throws BusinessException
	 */
	@GET
	@Path("performed-sessions-narrative")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsNarrative(@QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;
	
	/**
	 * 
	 * @return Number of Performed Sessions on the Last 12 Months
	 * @throws BusinessException
	 */
	@GET
	@Path("performed-sessions-months")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsLast12Months() throws BusinessException;
	
	@GET
	@Path("performed-sessions-indicators")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsIndicators(@QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;
	
	@GET
	@Path("performed-sessions-indicators-list")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public JResponse<List<PerformedSession>> findPerformedSessionsIndicatorsList(@QueryParam("startDate") String startDate,
	        @QueryParam("endDate") String endDate) throws BusinessException;

	
}
